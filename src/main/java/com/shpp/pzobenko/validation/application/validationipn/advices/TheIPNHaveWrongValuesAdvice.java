package com.shpp.pzobenko.validation.application.validationipn.advices;

import com.shpp.pzobenko.validation.application.validationipn.exceptions.TheIPNHaveWrongValues;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TheIPNHaveWrongValuesAdvice {
    @ResponseBody
    @ExceptionHandler(TheIPNHaveWrongValues.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String theIPNHaveWrongValuesHandler
            (TheIPNHaveWrongValues theIPNHaveWrongValues) {
        return theIPNHaveWrongValues.getMessage();
    }
}
