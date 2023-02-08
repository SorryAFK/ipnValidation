package com.shpp.pzobenko.todolist.exceptions;

public class NewStatusHaveWrongValuesException extends RuntimeException {
    public NewStatusHaveWrongValuesException() {
        super("StatusCannotApply");
    }
}
