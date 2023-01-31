package com.shpp.pzobenko.validation.application.exception;

public class TheCitizenOfUkraineNotFoundException extends RuntimeException{
    public TheCitizenOfUkraineNotFoundException(Long ipn) {
        super("The people with ipn \"" + ipn + "\" not found.");
    }
}
