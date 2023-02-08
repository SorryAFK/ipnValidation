package com.shpp.pzobenko.todolist.validators;

import com.shpp.pzobenko.todolist.usersinformation.StatusOfAim;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckStatus implements ConstraintValidator<IsNewAimStatusArePlaned, StatusOfAim> {
    @Override
    public void initialize(IsNewAimStatusArePlaned constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(StatusOfAim statusOfAim, ConstraintValidatorContext constraintValidatorContext) {
        return statusOfAim == StatusOfAim.PLANED;
    }
}
