package com.shpp.pzobenko.todolist.exceptions;

public class YouDontHaveRuleToSeeThisInformationException extends RuntimeException {
    public YouDontHaveRuleToSeeThisInformationException() {
        super("CannotSeeThisInformation");
    }
}
