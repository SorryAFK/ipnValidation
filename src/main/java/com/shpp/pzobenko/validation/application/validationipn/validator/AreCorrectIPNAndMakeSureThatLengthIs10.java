package com.shpp.pzobenko.validation.application.validationipn.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AreCorrectIPNImplementation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AreCorrectIPNAndMakeSureThatLengthIs10 {
    String message() default "Your IPN isn't correct.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
