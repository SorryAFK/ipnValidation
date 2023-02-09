package com.shpp.pzobenko.todolist.services;

import com.shpp.pzobenko.todolist.exceptions.YouDontHaveRuleToSeeThisInformationException;
import com.shpp.pzobenko.todolist.repositorys.TheAimRepository;
import com.shpp.pzobenko.todolist.repositorys.UserRepository;
import com.shpp.pzobenko.todolist.usersinformation.StatusOfAim;
import com.shpp.pzobenko.todolist.usersinformation.TheAim;
import com.shpp.pzobenko.todolist.usersinformation.UserOfToDo;
import com.shpp.pzobenko.todolist.exceptions.NewStatusHaveWrongValuesException;
import com.shpp.pzobenko.todolist.usersinformation.Role;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserOfToDoServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private TheAimRepository aimRepository;

    private UserOfToDoService serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new UserOfToDoService(userRepository, aimRepository);
    }

    @Test
    void addNewAimTest() {
        String username = "SomeUsername";

        UserOfToDo userForTest = UserOfToDo.builder()
                .username(username)
                .password("00000000")
                .role(Role.USER)
                .build();

        TheAim aimToAdd = TheAim.builder()
                .nameOfAim("MyAimForTest")
                .description("JustForTest")
                .status(StatusOfAim.PLANNED)
                .timeOfTheEndOfAim(LocalDateTime.now().plusDays(69))
                .build();

        given(userRepository.findUserOfToDoByUsername(username))
                .willReturn(Optional.of(userForTest));

        aimToAdd.setUserOfToDo(userForTest);

        given(aimRepository.save(aimToAdd)).willReturn(aimToAdd);

        serviceToTest.addNewAim(aimToAdd, username);

        verify(aimRepository).save(aimToAdd);
        verify(userRepository).findUserOfToDoByUsername(username);
    }

    @Test
    void changeStatusOfAim() {
        String username = "SomeUsername";

        UserOfToDo userForTest = UserOfToDo.builder()
                .username(username)
                .password("00000000")
                .role(Role.USER)
                .build();

        String aimName = "MyAimForTest";

        TheAim aimToChangeStatus = TheAim.builder()
                .nameOfAim(aimName)
                .description("JustForTest")
                .status(StatusOfAim.PLANNED)
                .userOfToDo(userForTest)
                .timeOfTheEndOfAim(LocalDateTime.now().plusDays(69))
                .build();

        StatusOfAim newStatus = StatusOfAim.IN_PROCESS;

        given(aimRepository.findTheAimByNameOfAim(aimName))
                .willReturn(Optional.of(aimToChangeStatus));

        given(aimRepository.save(aimToChangeStatus)).willReturn(aimToChangeStatus);

        TheAim aimWithChangedStatus = serviceToTest.saveNewStatusOrThrowException(aimName, newStatus);

        verify(aimRepository).save(aimToChangeStatus);

        aimToChangeStatus.setStatus(newStatus);

        assertEquals(aimToChangeStatus, aimWithChangedStatus);
    }

    @Test
    void changeStatusFromFirstToLastWaitingForException() {
        String username = "SomeUsername";

        UserOfToDo userForTest = UserOfToDo.builder()
                .username(username)
                .password("00000000")
                .role(Role.USER)
                .build();

        String aimName = "MyAimForTest";
        StatusOfAim status = StatusOfAim.PLANNED;

        TheAim aimToChangeStatus = TheAim.builder()
                .nameOfAim(aimName)
                .description("JustForTest")
                .status(status)
                .userOfToDo(userForTest)
                .timeOfTheEndOfAim(LocalDateTime.now().plusDays(69))
                .build();

        StatusOfAim newStatus = StatusOfAim.DONE;

        given(aimRepository.findTheAimByNameOfAim(aimName))
                .willReturn(Optional.of(aimToChangeStatus));

        assertThatThrownBy(() -> serviceToTest.saveNewStatusOrThrowException(aimName, newStatus))
                .isInstanceOf(NewStatusHaveWrongValuesException.class)
                .hasMessageContaining("StatusCannotBeApplied");
    }

    @Test
    void testStatusChanging() {
        assertTrue(serviceToTest.canChangeStatusToNew(StatusOfAim.PLANNED,StatusOfAim.IN_PROCESS));
        assertTrue(serviceToTest.canChangeStatusToNew(StatusOfAim.PLANNED,StatusOfAim.CANCELED));
        assertTrue(serviceToTest.canChangeStatusToNew(StatusOfAim.IN_PROCESS,StatusOfAim.DONE));
        assertTrue(serviceToTest.canChangeStatusToNew(StatusOfAim.IN_PROCESS, StatusOfAim.CANCELED));

        assertFalse(serviceToTest.canChangeStatusToNew(StatusOfAim.DONE,StatusOfAim.IN_PROCESS));
        assertFalse(serviceToTest.canChangeStatusToNew(StatusOfAim.DONE,StatusOfAim.CANCELED));
        assertFalse(serviceToTest.canChangeStatusToNew(StatusOfAim.PLANNED, StatusOfAim.DONE));
        assertFalse(serviceToTest.canChangeStatusToNew(StatusOfAim.IN_PROCESS,StatusOfAim.PLANNED));

    }

    @Test
    void changeStatusFromFinalToAnotherWaitingForException() {
        String username = "SomeUsername";

        UserOfToDo userForTest = UserOfToDo.builder()
                .username(username)
                .password("00000000")
                .role(Role.USER)
                .build();

        String aimName = "MyAimForTest";
        StatusOfAim status = StatusOfAim.CANCELED;

        TheAim aimToChangeStatus = TheAim.builder()
                .nameOfAim(aimName)
                .description("JustForTest")
                .status(status)
                .userOfToDo(userForTest)
                .timeOfTheEndOfAim(LocalDateTime.now().plusDays(69))
                .build();

        StatusOfAim newStatus = StatusOfAim.DONE;

        given(aimRepository.findTheAimByNameOfAim(aimName))
                .willReturn(Optional.of(aimToChangeStatus));

        assertThatThrownBy(() -> serviceToTest.saveNewStatusOrThrowException(aimName, newStatus))
                .isInstanceOf(NewStatusHaveWrongValuesException.class)
                .hasMessageContaining("StatusCannotBeApplied");
    }

    @Test
    void getAllUsersInThisAppButOnlyAdminRuleWillThrowExceptionIfRoleUser() {
        String username = "SomeUsername";

        UserOfToDo userForTest = UserOfToDo.builder()
                .username(username)
                .password("00000000")
                .role(Role.USER)
                .build();

        given(userRepository.findUserOfToDoByUsername(username))
                .willReturn(Optional.of(userForTest));

        assertThatThrownBy(() -> serviceToTest.getAllUsersInThisAppButOnlyAdminRule(username))
                .isInstanceOf(YouDontHaveRuleToSeeThisInformationException.class)
                .hasMessageContaining("CannotSeeThisInformation");
    }
}