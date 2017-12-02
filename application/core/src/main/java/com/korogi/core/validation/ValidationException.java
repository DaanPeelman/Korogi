package com.korogi.core.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;

public class ValidationException extends RuntimeException {
    private Set<ConstraintViolation<Validatable>> constraintViolations;

    public static ValidationException newValidationException(Set<ConstraintViolation<Validatable>> constraintViolations) {
        return new ValidationException(generateValidationMessages(constraintViolations), constraintViolations);
    }

    private static String generateValidationMessages(Set<ConstraintViolation<Validatable>> constraintViolations) {
        StringBuilder stringBuilder = new StringBuilder();

        constraintViolations
                .forEach(constraintViolation -> stringBuilder.append(constraintViolation.getPropertyPath().toString()).append(" ").append(constraintViolation.getMessage()).append("\n"));

        return stringBuilder.toString();
    }

    private ValidationException(String message, Set<ConstraintViolation<Validatable>> constraintViolations) {
        super(message);
        this.constraintViolations = constraintViolations;
    }

    public Set<ConstraintViolation<Validatable>> getConstraintViolations() {
        return constraintViolations;
    }
}