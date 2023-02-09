package com.shpp.pzobenko.todolist.exceptions;

public class StatusAlreadyFinalException extends RuntimeException {
    public StatusAlreadyFinalException() {
        super("StatusAlreadyFinal");
    }
}
