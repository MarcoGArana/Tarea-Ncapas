package com.ldar01.demoemployees.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EndBeforeStartDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EndBeforeStartDate {
    String message() default "La fecha de fin no puede ser anterior a la de inicio";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
