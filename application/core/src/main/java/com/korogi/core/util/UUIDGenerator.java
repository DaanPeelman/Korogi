package com.korogi.core.util;

import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 * @author Daan Peelman
 */
@Component
public class UUIDGenerator {
    public String generateUUIDString() {
        return UUID.randomUUID().toString();
    }
}