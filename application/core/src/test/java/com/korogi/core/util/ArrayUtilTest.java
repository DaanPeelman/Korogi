package com.korogi.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ArrayUtilTest {

    @Test
    void concatenate_twoArrays() throws Exception {
        String[] arrayA = new String[] { "a", "b" };
        String[] arrayB = new String[] { "c", "d"};

        String[] expectedArray = new String[] { "a", "b", "c", "d"};

        assertThat(ArrayUtil.concatenate(arrayA, arrayB)).isEqualTo(expectedArray);
    }

    @Test
    void concatenate_arrayAndVarArgs() throws Exception {
        String[] arrayA = new String[] { "a", "b" };

        String[] expectedArray = new String[] { "a", "b", "c", "d" };

        assertThat(ArrayUtil.concatenate(arrayA, "c", "d")).isEqualTo(expectedArray);
    }

    @Test
    void concatenate_onlyOneArray() throws Exception {
        String[] arrayA = new String[] { "a", "b" };

        assertThat(ArrayUtil.concatenate(arrayA)).isEqualTo(arrayA);
    }
}