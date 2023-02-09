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
        return aimRepository.findTheAimByNameOfAim(aimName)
                .orElseThrow(TheAimOnFoundException::new);
    }

    public UserOfToDo getUserOfToDo(String username) {
        return userRepository.findUserOfToDoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("{NotFoundTheUser}"));
    }

    public TheAim addNewAim(TheAim newAim, String username) {
        log.info("User {} adding new aim", username);
        newAim.setUserOfToDo(getUserOfToDo(username));
        return aimRepository.save(newAim);
    }

    public TheAim saveNewStatusOrThrowException(String nameOfAim, StatusOfAim newStatus) {
        TheAim aimToChangeStatus = getAimByName(nameOfAim);
        StatusOfAim oldStatus =  aimToChangeStatus.getStatus();
        if(canChangeStatusToNew(oldStatus, newStatus)) {
            aimToChangeStatus.setStatus(newStatus);
        } else {
            throw new NewStatusHaveWrongValuesException();
        }
        return aimRepository.save(aimToChangeStatus);
    }

    public boolean canChangeStatusToNew(StatusOfAim oldStatus, StatusOfAim newStatus) {
        if (oldStatus.canChangeOnNextStatus()) {
            return oldStatus.getNextStatus().equals(newStatus) || newStatus.equals(StatusOfAim.CANCELED);
        }
        return false;
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