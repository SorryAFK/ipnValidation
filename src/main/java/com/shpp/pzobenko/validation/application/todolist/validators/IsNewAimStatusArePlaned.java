package com.shpp.pzobenko.validation.application.todolist.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckStatus.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsNewAimStatusArePlaned {
    String message() default "Status not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
