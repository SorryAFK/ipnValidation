package com.shpp.pzobenko.validation.application.validationipn.advices;

import com.shpp.pzobenko.validation.application.validationipn.exceptions.TheCitizenAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TheCitizenAlreadyExistAdvice {

    @ResponseBody
    @ExceptionHandler(TheCitizenAlreadyExistException.class)
    @ResponseStatus(HttpStatus.IM_USED)
    String theCitizenAlreadyExistHandler
            (TheCitizenAlreadyExistException theCitizenAlreadyExistException) {
        return theCitizenAlreadyExistException.getMessage();
    }
}
