package com.shpp.pzobenko.todolist.services;

import com.shpp.pzobenko.todolist.exceptions.YouDontHaveRuleToSeeThisInformationException;
import com.shpp.pzobenko.todolist.repositorys.UserRepository;
import com.shpp.pzobenko.todolist.exceptions.NewStatusHaveWrongValuesException;
import com.shpp.pzobenko.todolist.exceptions.TheAimOnFoundException;
import com.shpp.pzobenko.todolist.repositorys.TheAimRepository;
import com.shpp.pzobenko.todolist.usersinformation.Role;
import com.shpp.pzobenko.todolist.usersinformation.StatusOfAim;
import com.shpp.pzobenko.todolist.usersinformation.TheAim;
import com.shpp.pzobenko.todolist.usersinformation.UserOfToDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserOfToDoService {
    private final UserRepository userRepository;
    private final TheAimRepository aimRepository;

    public List<TheAim> getAllAims() {
        return aimRepository.findAll();
    }

    public TheAim getAimByName(String aimName) {
        log.info("user trying to get aim with name {}", aimName);
        return aimRepository.findById(aimName)
                .orElseThrow(TheAimOnFoundException::new);
    }

    public UserOfToDo getUserOfToDo(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found the user"));
    }

    public TheAim addNewAim(TheAim newAim, String username) {
        log.info("User {} adding new aim", username);
        newAim.setUserOfToDo(getUserOfToDo(username));
        return aimRepository.save(newAim);
    }

    public TheAim changeStatusOfAim(String aimName, StatusOfAim newStatus, String username) {

        log.info("User with username \"{}\", switching status of his aim \"{}\" to \"{}\""
                , username, aimName, newStatus);

        TheAim aim = aimRepository.findById(aimName)
                .orElseThrow(TheAimOnFoundException::new);

        StatusOfAim statusWhichWasBefore = aim.getStatus();
        log.info("find ok {}", aim.getNameOfAim());
        if (statusWhichWasBefore == StatusOfAim.PLANED &&
                (newStatus == StatusOfAim.IN_PROCESS || newStatus == StatusOfAim.CANCELED)) {
            log.info("Status successfully switched from {} to {}", statusWhichWasBefore, newStatus);
            aim.setStatus(newStatus);
        }
        else if (statusWhichWasBefore == StatusOfAim.IN_PROCESS &&
                (newStatus == StatusOfAim.DONE || newStatus == StatusOfAim.CANCELED)) {
            log.info("Status successfully switched from {} to {}", statusWhichWasBefore, newStatus);
            aim.setStatus(newStatus);
        } else {
            throw new NewStatusHaveWrongValuesException();
        }

        log.info("The end of the method to change status.");
        return aimRepository.save(aim);
    }

    public TheAim deleteAim(String aimName) {
        TheAim aimToDelete = getAimByName(aimName);

        aimRepository.delete(aimToDelete);
        return aimToDelete;
    }

    public List<UserOfToDo> getAllUsersInThisAppButOnlyAdminRule(String nameOfUser) {
        if (getUserOfToDo(nameOfUser).getRole() == Role.ADMIN) {
            return userRepository.findAll();
        } else
            throw new YouDontHaveRuleToSeeThisInformationException();
    }
}