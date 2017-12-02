package com.korogi.core.validation;

import static com.korogi.core.validation.ValidationException.newValidationException;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class Validatable {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public void validate() {
        Set<ConstraintViolation<Validatable>> constraintViolations = validator.validate(this);

        if(! constraintViolations.isEmpty()) {
            throw newValidationException(constraintViolations);
        }
    }
}