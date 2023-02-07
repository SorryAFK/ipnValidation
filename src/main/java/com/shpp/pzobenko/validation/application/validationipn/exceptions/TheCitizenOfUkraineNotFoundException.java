package com.shpp.pzobenko.validation.application.validationipn.exceptions;

public class TheCitizenOfUkraineNotFoundException extends RuntimeException{
    public TheCitizenOfUkraineNotFoundException(Long ipn) {
        super("The people with ipn \"" + ipn + "\" not found.");
    }
}
