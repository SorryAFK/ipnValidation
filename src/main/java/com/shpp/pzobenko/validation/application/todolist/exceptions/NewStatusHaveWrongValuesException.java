package com.shpp.pzobenko.validation.application.todolist.exceptions;

public class NewStatusHaveWrongValuesException extends RuntimeException {
    public NewStatusHaveWrongValuesException() {
        super("StatusCannotApply");
    }
}
