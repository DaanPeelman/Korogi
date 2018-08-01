package com.korogi.rest.service.matcher;

import java.util.Arrays;

class DTOsMatcher<T> extends DTOMatcher<T[]> {
    public DTOsMatcher(T[] expected, Class<T[]> clazz) {
        super(expected, clazz);
    }

    @Override
    public boolean matches(Object item) {
        return Arrays.equals(expected, parseToObject(item));
    }
}