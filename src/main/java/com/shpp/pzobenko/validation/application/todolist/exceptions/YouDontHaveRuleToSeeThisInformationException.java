package com.shpp.pzobenko.validation.application.todolist.exceptions;

public class YouDontHaveRuleToSeeThisInformationException extends RuntimeException {
    public YouDontHaveRuleToSeeThisInformationException() {
        super("CannotSeeThisInformation");
    }
}
