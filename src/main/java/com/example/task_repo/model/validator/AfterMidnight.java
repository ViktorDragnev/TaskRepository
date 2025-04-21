package com.example.task_repo.model.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AfterMidnightValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterMidnight {
    String message() default "Часът трябва да е по-голям от 00:00";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
