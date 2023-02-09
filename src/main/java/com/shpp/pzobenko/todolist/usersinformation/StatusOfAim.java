package com.shpp.pzobenko.todolist.usersinformation;

import com.shpp.pzobenko.todolist.exceptions.StatusAlreadyFinalException;

public enum StatusOfAim {
    CANCELED(true),
    DONE(true),
    IN_PROCESS(false, DONE),
    PLANNED(false, IN_PROCESS);


    final boolean isStatusFinal;
    StatusOfAim nextStatus;

    StatusOfAim(boolean isStatusFinal, StatusOfAim nextStatus) {
        this.isStatusFinal = isStatusFinal;
        this.nextStatus = nextStatus;
    }

    StatusOfAim(boolean isStatusFinal) {
        this.isStatusFinal = isStatusFinal;
    }

    public boolean canChangeOnNextStatus() {
        return !isStatusFinal;
    }

    public StatusOfAim getNextStatus() {
         if (isStatusFinal) {
             throw new StatusAlreadyFinalException();
         }
             return nextStatus;
    }
}
