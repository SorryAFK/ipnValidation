package com.shpp.pzobenko.validation.application.advice;

import com.shpp.pzobenko.validation.application.exception.TheCitizenOfUkraineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TheCitizenOfUkraineNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(TheCitizenOfUkraineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String theCitizenOfUkraineNotFoundHandler
            (TheCitizenOfUkraineNotFoundException citizenOfUkraineNotFoundException) {
        return citizenOfUkraineNotFoundException.getMessage();
    }
}
