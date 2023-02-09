package com.shpp.pzobenko.todolist.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckStatus.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsNewAimStatusArePlaned {
    String message() default "{StatusNotValid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
