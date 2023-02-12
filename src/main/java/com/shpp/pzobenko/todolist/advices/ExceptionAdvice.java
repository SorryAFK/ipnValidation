package com.shpp.pzobenko.todolist.advices;

import com.shpp.pzobenko.todolist.exceptions.StatusAlreadyFinalException;
import com.shpp.pzobenko.todolist.exceptions.YouDontHaveRuleToSeeThisInformationException;
import com.shpp.pzobenko.todolist.exceptions.NewStatusHaveWrongValuesException;
import com.shpp.pzobenko.todolist.exceptions.TheAimOnFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ResponseBody
    @ExceptionHandler(StatusAlreadyFinalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String statusAlreadyFinalExceptionHandler
            (StatusAlreadyFinalException statusAlreadyFinalException,
             Locale locale) {
        return messageSource.getMessage(statusAlreadyFinalException.getMessage(), null, locale);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}