package com.korogi.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @author Daan Peelman
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class UUIDGeneratorTest {
    private UUIDGenerator generator = new UUIDGenerator();

    @Test
    public void generateUUIDString() throws Exception {
        assertThat(generator.generateUUIDString()).isNotEmpty();
    }
}