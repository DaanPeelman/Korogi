package com.korogi.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Daan Peelman
 */
public class InjectorUtil {
    public static void injectIntoStaticField(Class<?> clazz, String fieldToInjectIn, Object objectToInject) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldToInjectIn);
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, objectToInject);
    }
}