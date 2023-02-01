package com.shpp.pzobenko.validation.application.validationipn.exception;

public class TheCitizenAlreadyExistException extends RuntimeException {
    public TheCitizenAlreadyExistException(Long ipn) {
        super("The citizen with IPN: " + ipn + " already exist.");
    }
}
