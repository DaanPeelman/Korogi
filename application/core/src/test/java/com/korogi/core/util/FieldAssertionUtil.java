package com.korogi.core.util;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * @author Daan Peelman
 */
public class FieldAssertionUtil {
    private final Object objectWithExpectedValues;
    private final Object objectWithValuesToAssert;
    private final Map<String, CustomFieldAssertion> customFieldAssertions;

    public FieldAssertionUtil(
            Object objectWithExpectedValues,
            Object objectWithValuesToAssert
    ) {
        this.objectWithExpectedValues = objectWithExpectedValues;
        this.objectWithValuesToAssert = objectWithValuesToAssert;
        this.customFieldAssertions = new HashMap<>();
    }

    public FieldAssertionUtil ignoreField(String fieldName) {
        this.customFieldAssertions.put(fieldName, CustomFieldAssertion.ignore());

        return this;
    }

    public FieldAssertionUtil expectFieldValue(String fieldName, Object expectedValue) {
        this.customFieldAssertions.put(fieldName, CustomFieldAssertion.equal(expectedValue));

        return this;
    }

    public void assertAllFieldValuesAreEqual() throws IllegalAccessException {
        Map<String, Field> fieldsWithExpectedValues = stream(objectWithExpectedValues.getClass().getDeclaredFields())
                .map(this::setAccessibleAndReturn)
                .collect(Collectors.toMap(
                        Field::getName,
                        Function.identity()
                ));

        List<Field> fieldsToAssert = stream(objectWithValuesToAssert.getClass().getDeclaredFields())
                .map(this::setAccessibleAndReturn)
                .filter(this::isFieldToBeAsserted)
                .collect(toList());

        for (Field fieldToAssert : fieldsToAssert) {
            Object expectedValue = this.customFieldAssertions.containsKey(fieldToAssert.getName()) ?
                    this.customFieldAssertions.get(fieldToAssert.getName()).getExpectedValue() : fieldsWithExpectedValues.get(fieldToAssert.getName()).get(objectWithExpectedValues);
            Object actualValue = fieldToAssert.get(objectWithValuesToAssert);

            assertThat(actualValue)
                    .as("Expecting the value in field '%s' to equal '%s' but was '%s'", fieldToAssert.getName(), expectedValue, actualValue)
                    .isEqualTo(expectedValue);
        }
    }

    private boolean isFieldToBeAsserted(Field field) {
        return ! this.customFieldAssertions.containsKey(field.getName()) || this.customFieldAssertions.get(field.getName()).shouldNotBeIgnored();
    }

    private Field setAccessibleAndReturn(Field field) {
        field.setAccessible(true);

        return field;
    }

    private static class CustomFieldAssertion {
        public static CustomFieldAssertion equal(Object expectedValue) {
            return new CustomFieldAssertion(expectedValue);
        }

        public static CustomFieldAssertion ignore() {
            return new CustomFieldAssertion(true);
        }

        @Getter
        private Object expectedValue;
        private boolean ignore;

        private CustomFieldAssertion(Object expectedValue) {
            this.expectedValue = expectedValue;
        }

        private CustomFieldAssertion(boolean ignore) {
            this.ignore = ignore;
        }

        private boolean shouldBeIgnored() {
            return ignore;
        }

        private boolean shouldNotBeIgnored() {
            return ! shouldBeIgnored();
        }
    }
}