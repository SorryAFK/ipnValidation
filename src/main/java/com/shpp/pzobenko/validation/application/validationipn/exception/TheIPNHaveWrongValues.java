package com.shpp.pzobenko.validation.application.validationipn.exception;

public class TheIPNHaveWrongValues extends RuntimeException{
    public TheIPNHaveWrongValues(Long ipn) {
        super("The IPN have wrong values on it:" + ipn);
    }
}