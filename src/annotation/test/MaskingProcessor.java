package annotation.test;

import java.lang.reflect.Field;

public class MaskingProcessor {

    public static void maskSensitiveFields(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Masking.class)) {
                field.setAccessible(true); // private 필드 접근 허용
                Object value = field.get(obj);

                if (value instanceof String) { // 문자열에 대해서만 처리
                    String maskedValue = maskValue((String) value);
                    field.set(obj, maskedValue);
                }
            }
        }
    }

    private static String maskValue(String value) {
        if (value == null || value.length() <= 2) return value; // 너무 짧은 경우 그대로 반환
        int maskLength = value.length() - 2;
        return value.charAt(0) + "*".repeat(maskLength) + value.substring(value.length() - 1);
    }
}

