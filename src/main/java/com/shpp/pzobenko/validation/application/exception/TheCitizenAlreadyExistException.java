package com.shpp.pzobenko.validation.application.exception;

public class TheCitizenAlreadyExistException extends RuntimeException {
    public TheCitizenAlreadyExistException(Long ipn) {
        super("The citizen with IPN: " + ipn + " already exist.");
    }
}
