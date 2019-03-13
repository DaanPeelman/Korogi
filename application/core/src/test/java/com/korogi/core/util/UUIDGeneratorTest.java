package com.korogi.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author Daan Peelman
 */
class UUIDGeneratorTest {
    private UUIDGenerator generator = new UUIDGenerator();

    @Test
    void generateUUIDString() throws Exception {
        assertThat(generator.generateUUIDString()).isNotEmpty();
    }
}