package com.shpp.pzobenko.todolist.advices;

import com.shpp.pzobenko.todolist.exceptions.YouDontHaveRuleToSeeThisInformationException;
import com.shpp.pzobenko.todolist.exceptions.NewStatusHaveWrongValuesException;
import com.shpp.pzobenko.todolist.exceptions.TheAimOnFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {
    private final MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(TheAimOnFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String theAimOnFoundExceptionHandler
            (TheAimOnFoundException theAimOnFoundException,
             Locale locale) {
        return messageSource.getMessage(theAimOnFoundException.getMessage(), null, locale);
    }

    @ResponseBody
    @ExceptionHandler(NewStatusHaveWrongValuesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String newStatusHaveWrongValuesHandler
            (NewStatusHaveWrongValuesException newStatusHaveWrongValuesException,
             Locale locale) {
        return messageSource.getMessage(newStatusHaveWrongValuesException.getMessage(), null, locale);
    }

    @ResponseBody
    @ExceptionHandler(YouDontHaveRuleToSeeThisInformationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String theAimOnFoundExceptionHandler
            (YouDontHaveRuleToSeeThisInformationException youDontHaveRuleToSeeThisInformationException,
             Locale locale) {
        return messageSource.getMessage(youDontHaveRuleToSeeThisInformationException.getMessage(), null, locale);
    }
}