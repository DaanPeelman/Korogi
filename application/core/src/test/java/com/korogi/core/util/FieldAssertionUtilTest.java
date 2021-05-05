package com.korogi.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import lombok.Builder;
import lombok.ToString;
import org.junit.jupiter.api.Test;

/**
 * @author Daan Peelman
 */
class FieldAssertionUtilTest {

    /**
     * When asserting Object1's and Object2's fields when:
     * <ul>
     *     <li>field1: is same value in Object1 as in Object2</li>
     *     <li>field2: is same value in Object1 as in Object2</li>
     *     <li>field3: is not known in Object1</li>
     * </ul>
     * <p>
     * It <b>should not</b> throw an AssertionFailedError because:
     * <ul>
     *     <li>field1 in Object1 has the same value as in Object2</li>
     *     <li>field2 in Object1 has the same value as in Object2</li>
     *     <li>field3 does not exist in Object1, so it gets ignored</li>
     * </ul>
     */
    @Test
    void assertAllFieldValuesAreEqual_noCustomFilters_allValuesFilledIn() throws Exception {
        FieldAssertionUtilTestObject1 objectToAssert = FieldAssertionUtilTestObject1.newFieldAssertionUtilTestObject1()
                                                                                    .field1("value 1")
                                                                                    .field2("value 2")
                                                                                    .build();

        FieldAssertionUtilTestObject2 objectWithExpectedValues = FieldAssertionUtilTestObject2.newFieldAssertionUtilTestObject2()
                                                                                              .field1("value 1")
                                                                                              .field2("value 2")
                                                                                              .field3("value 3")
                                                                                              .build();

        new FieldAssertionUtil(objectWithExpectedValues, objectToAssert)
            .assertAllFieldValuesAreEqual();
    }

    /**
     * When asserting Object1's and Object2's fields when:
     * <ul>
     *     <li>field1: is same value in Object1 as in Object2</li>
     *     <li>field2: is 'null' in Object1 and in Object2</li>
     *     <li>field3: is not known in Object1</li>
     * </ul>
     * <p>
     * It <b>should not</b> throw an AssertionFailedError because:
     * <ul>
     *     <li>field1 in Object1 has the same value as in Object2</li>
     *     <li>field2 in Object1 is 'null' and field2 in Object2 is also 'null'</li>
     *     <li>field3 does not exist in Object1, so it gets ignored</li>
     * </ul>
     */
    @Test
    void assertAllFieldValuesAreEqual_noCustomFilters_withNullValue() throws Exception {
        FieldAssertionUtilTestObject1 objectToAssert = FieldAssertionUtilTestObject1.newFieldAssertionUtilTestObject1()
                                                                                    .field1("value 1")
                                                                                    .field2(null)
                                                                                    .build();

        FieldAssertionUtilTestObject2 objectWithExpectedValues = FieldAssertionUtilTestObject2.newFieldAssertionUtilTestObject2()
                                                                                              .field1("value 1")
                                                                                              .field2(null)
                                                                                              .field3("value 3")
                                                                                              .build();

        new FieldAssertionUtil(objectWithExpectedValues, objectToAssert)
            .assertAllFieldValuesAreEqual();
    }

    /**
     * When asserting Object1's and Object2's fields when:
     * <ul>
     *     <li>field1: is a different value in Object1 than in Object2</li>
     *     <li>field2: is same value in Object1 as in Object2</li>
     *     <li>field3: is not known in Object1</li>
     * </ul>
     * <p>
     * It <b>should</b> throw an AssertionFailedError because:
     * <ul>
     *     <li>field1 in Object1 has a different value than Object2</li>
     *     <li>field2 in Object1 has the same value as in Object2</li>
     *     <li>field3 does not exist in Object1, so it gets ignored</li>
     * </ul>
     */
    @Test
    void assertAllFieldValuesAreEqual_noCustomFilters_differentValuesInField1() throws Exception {
        FieldAssertionUtilTestObject1 objectToAssert = FieldAssertionUtilTestObject1.newFieldAssertionUtilTestObject1()
                                                                                    .field1("value 1")
                                                                                    .field2("value 2")
                                                                                    .build();

        FieldAssertionUtilTestObject2 objectWithExpectedValues = FieldAssertionUtilTestObject2.newFieldAssertionUtilTestObject2()
                                                                                              .field1(
                                                                                                  "different value 1")
                                                                                              .field2("value 2")
                                                                                              .field3("value 3")
                                                                                              .build();

        try {
            new FieldAssertionUtil(objectWithExpectedValues, objectToAssert)
                .assertAllFieldValuesAreEqual();
            fail("Expected AssertionFailedError to have been thrown but it wasn't");
        } catch (AssertionError e) {
            assertThat(e.getMessage()).contains("\nExpecting:");
        }
    }

    /**
     * When asserting Object1's and Object2's fields when:
     * <ul>
     *     <li>field1: is same value in Object1 as in Object2</li>
     *     <li>field2: is a different value in Object1 than in Object2</li>
     *     <li>field3: is not known in Object1</li>
     * </ul>
     * <p>
     * It <b>should</b> throw an AssertionFailedError because:
     * <ul>
     *     <li>field1 in Object1 has the same value as in Object2</li>
     *     <li>field2 in Object1 has a different value than Object2</li>
     *     <li>field3 does not exist in Object1, so it gets ignored</li>
     * </ul>
     */
    @Test
    void assertAllFieldValuesAreEqual_noCustomFilters_differentValuesInField2() throws Exception {
        FieldAssertionUtilTestObject1 objectToAssert = FieldAssertionUtilTestObject1.newFieldAssertionUtilTestObject1()
                                                                                    .field1("value 1")
                                                                                    .field2("value 2")
                                                                                    .build();

        FieldAssertionUtilTestObject2 objectWithExpectedValues = FieldAssertionUtilTestObject2.newFieldAssertionUtilTestObject2()
                                                                                              .field1("value 1")
                                                                                              .field2(
                                                                                                  "different value 2")
                                                                                              .field3("value 3")
                                                                                              .build();

        try {
            new FieldAssertionUtil(objectWithExpectedValues, objectToAssert)
                .assertAllFieldValuesAreEqual();
            fail("Expected AssertionFailedError to have been thrown but it wasn't");
        } catch (AssertionError e) {
            assertThat(e.getMessage()).contains("\nExpecting:");
        }
    }

    /**
     * When asserting Object1's and Object2's fields when:
     * <ul>
     *     <li>field1: is same value in Object1 as in Object2</li>
     *     <li>field2: is same value in Object1 as in Object2</li>
     *     <li>field3: is not known in Object1</li>
     * </ul>
     * <p>
     * and field1 is ignored.
     * <p>
     * It <b>should not</b> throw an AssertionFailedError because:
     * <ul>
     *     <li>field1 is in the ignore condition, so it does not get checked</li>
     *     <li>field2 in Object1 has the same value as in Object2</li>
     *     <li>field3 does not exist in Object1, so it gets ignored</li>
     * </ul>
     */
    @Test
    void assertAllFieldValuesAreEqual_ignoring_allValuesEqual() throws Exception {
        FieldAssertionUtilTestObject1 objectToAssert = FieldAssertionUtilTestObject1.newFieldAssertionUtilTestObject1()
                                                                                    .field1("value 1")
                                                                                    .field2("value 2")
                                                                                    .build();

        FieldAssertionUtilTestObject2 objectWithExpectedValues = FieldAssertionUtilTestObject2.newFieldAssertionUtilTestObject2()
                                                                                              .field1("value 1")
                                                                                              .field2("value 2")
                                                                                              .field3("value 3")
                                                                                              .build();

        new FieldAssertionUtil(objectWithExpectedValues, objectToAssert)
            .ignoreField("field1")
            .assertAllFieldValuesAreEqual();
    }

    /**
     * When asserting Object1's and Object2's fields when:
     * <ul>
     *     <li>field1: is a different value in Object1 than in Object2</li>
     *     <li>field2: is same value in Object1 as in Object2</li>
     *     <li>field3: is not known in Object1</li>
     * </ul>
     * <p>
     * and field1 is ignored.
     * <p>
     * It <b>should not</b> throw an AssertionFailedError because:
     * <ul>
     *     <li>field1 is in the ignore condition, so it does not get checked</li>
     *     <li>field2 in Object1 has the same value as in Object2</li>
     *     <li>field3 does not exist in Object1, so it gets ignored</li>
     * </ul>
     */
    @Test
    void assertAllFieldValuesAreEqual_ignoring_differentValuesInIgnoredField() throws Exception {
        FieldAssertionUtilTestObject1 objectToAssert = FieldAssertionUtilTestObject1.newFieldAssertionUtilTestObject1()
                                                                                    .field1("value 1")
                                                                                    .field2("value 2")
                                                                                    .build();

        FieldAssertionUtilTestObject2 objectWithExpectedValues = FieldAssertionUtilTestObject2.newFieldAssertionUtilTestObject2()
                                                                                              .field1(
                                                                                                  "different value 1")
                                                                                              .field2("value 2")
                                                                                              .field3("value 3")
                                                                                              .build();

        new FieldAssertionUtil(objectWithExpectedValues, objectToAssert)
            .ignoreField("field1")
            .assertAllFieldValuesAreEqual();
    }

    /**
     * When asserting Object1's and Object2's fields when:
     * <ul>
     *     <li>field1: is same value in Object1 as in Object2</li>
     *     <li>field2: is a different value in Object1 than in Object2</li>
     *     <li>field3: is not known in Object1</li>
     * </ul>
     * <p>
     * and field1 is ignored.
     * <p>
     * It <b>should</b> throw an AssertionFailedError because:
     * <ul>
     *     <li>field1 is in the ignore condition, so it does not get checked</li>
     *     <li>field2 in Object1 has a different value than Object2</li>
     *     <li>field3 does not exist in Object1, so it gets ignored</li>
     * </ul>
     */
    @Test
    void assertAllFieldValuesAreEqual_ignoring_differentValuesInOtherFieldThanIgnoredField() throws Exception {
        FieldAssertionUtilTestObject1 objectToAssert = FieldAssertionUtilTestObject1.newFieldAssertionUtilTestObject1()
                                                                                    .field1("value 1")
                                                                                    .field2("value 2")
                                                                                    .build();

        FieldAssertionUtilTestObject2 objectWithExpectedValues = FieldAssertionUtilTestObject2.newFieldAssertionUtilTestObject2()
                                                                                              .field1("value 1")
                                                                                              .field2(
                                                                                                  "different value 2")
                                                                                              .field3("value 3")
                                                                                              .build();

        try {
            new FieldAssertionUtil(objectWithExpectedValues, objectToAssert)
                .ignoreField("field1")
                .assertAllFieldValuesAreEqual();
            fail("Expected AssertionFailedError to have been thrown but it wasn't");
        } catch (AssertionError e) {
            assertThat(e.getMessage()).contains("\nExpecting:");
        }
    }

    /**
     * When asserting Object1's and Object2's fields when:
     * <ul>
     *     <li>field1: is same value in Object1 as in Object2</li>
     *     <li>field2: is different value in Object1 than in Object2</li>
     *     <li>field3: is not known in Object1</li>
     * </ul>
     * <p>
     * and field2 is set to expect Object1's field2 value.
     * <p>
     * It <b>should not</b> throw an AssertionFailedError because:
     * <ul>
     *     <li>field1 in Object1 has the same value as in Object2</li>
     *     <li>field2 in Object1 has a different value than Object2, but is set to expect Object1's field2 value</li>
     *     <li>field3 does not exist in Object1, so it gets ignored</li>
     * </ul>
     */
    @Test
    void assertAllFieldValuesAreEqual_expectFieldValue_sameValueInObject1AsSpecifiedInExpectFieldValueFilter() throws Exception {
        FieldAssertionUtilTestObject1 objectToAssert = FieldAssertionUtilTestObject1.newFieldAssertionUtilTestObject1()
                                                                                    .field1("value 1")
                                                                                    .field2("value 2")
                                                                                    .build();

        FieldAssertionUtilTestObject2 objectWithExpectedValues = FieldAssertionUtilTestObject2.newFieldAssertionUtilTestObject2()
                                                                                              .field1("value 1")
                                                                                              .field2(
                                                                                                  "different value 2")
                                                                                              .field3("value 3")
                                                                                              .build();

        new FieldAssertionUtil(objectWithExpectedValues, objectToAssert)
            .expectFieldValue("field2", "value 2")
            .assertAllFieldValuesAreEqual();
    }

    /**
     * When asserting Object1's and Object2's fields when:
     * <ul>
     *     <li>field1: is same value in Object1 as in Object2</li>
     *     <li>field2: is different value in Object1 than in Object2</li>
     *     <li>field3: is not known in Object1</li>
     * </ul>
     * <p>
     * and field2 is set to expect another value than Object1's field2's actual value.
     * <p>
     * It <b>should</b> throw an AssertionFailedError because:
     * <ul>
     *     <li>field1 in Object1 has the same value as in Object2</li>
     *     <li>field2 in Object1 has a different value than Object2 and it is a different value than the one set to expect</li>
     *     <li>field3 does not exist in Object1, so it gets ignored</li>
     * </ul>
     */
    @Test
    void assertAllFieldValuesAreEqual_expectFieldValue_differentValueInObject1ThanSpecifiedInExpectFieldValueFilter() throws Exception {
        FieldAssertionUtilTestObject1 objectToAssert = FieldAssertionUtilTestObject1.newFieldAssertionUtilTestObject1()
                                                                                    .field1("value 1")
                                                                                    .field2("value 2")
                                                                                    .build();

        FieldAssertionUtilTestObject2 objectWithExpectedValues = FieldAssertionUtilTestObject2.newFieldAssertionUtilTestObject2()
                                                                                              .field1("value 1")
                                                                                              .field2(
                                                                                                  "different value 2")
                                                                                              .field3("value 3")
                                                                                              .build();

        try {
            new FieldAssertionUtil(objectWithExpectedValues, objectToAssert)
                .expectFieldValue("field2", "another different value 2")
                .assertAllFieldValuesAreEqual();
            fail("Expected AssertionFailedError to have been thrown but it wasn't");
        } catch (AssertionError e) {
            assertThat(e.getMessage()).contains("\nExpecting:");
        }
    }

    @Builder(builderMethodName = "newFieldAssertionUtilTestObject1")
    @ToString
    private static class FieldAssertionUtilTestObject1 {
        private Object field1;
        private Object field2;
    }

    @Builder(builderMethodName = "newFieldAssertionUtilTestObject2")
    @ToString
    private static class FieldAssertionUtilTestObject2 {
        private Object field1;
        private Object field2;
        private Object field3;
    }
}