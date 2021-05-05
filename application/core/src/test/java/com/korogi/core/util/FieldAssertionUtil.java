package com.korogi.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
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

    public FieldAssertionUtil expectFieldValue(
        String fieldName,
        Object expectedValue
    ) {
        this.customFieldAssertions.put(fieldName, CustomFieldAssertion.equal(expectedValue));

        return this;
    }

    public void assertAllFieldValuesAreEqual() throws IllegalAccessException {
        assertThat(objectWithValuesToAssert)
            .usingRecursiveComparison()
            .ignoringOverriddenEqualsForTypes(
                objectWithValuesToAssert.getClass(),
                objectWithValuesToAssert.getClass()
            )
            .ignoringFields(customFieldAssertions.keySet().toArray(new String[0]))
            .isEqualTo(objectWithExpectedValues);

        for (String fieldToAssert : customFieldAssertions.keySet()) {
            CustomFieldAssertion customFieldAssertion = customFieldAssertions.get(fieldToAssert);

            if (customFieldAssertion.shouldNotBeIgnored()) {
                assertThat(objectWithValuesToAssert)
                    .extracting(fieldToAssert)
                    .isEqualTo(customFieldAssertion.expectedValue);
            }
        }
    }

    private static class CustomFieldAssertion {
        @Getter
        private Object expectedValue;
        private boolean ignore;

        private CustomFieldAssertion(Object expectedValue) {
            this.expectedValue = expectedValue;
        }

        private CustomFieldAssertion(boolean ignore) {
            this.ignore = ignore;
        }

        public static CustomFieldAssertion equal(Object expectedValue) {
            return new CustomFieldAssertion(expectedValue);
        }

        public static CustomFieldAssertion ignore() {
            return new CustomFieldAssertion(true);
        }

        private boolean shouldBeIgnored() {
            return ignore;
        }

        private boolean shouldNotBeIgnored() {
            return ! shouldBeIgnored();
        }
    }
}