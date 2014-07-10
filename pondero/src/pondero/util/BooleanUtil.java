package pondero.util;

public final class BooleanUtil {

    private BooleanUtil() {
    }

    public static Boolean toBoolean(final Object value) {
        if (value != null) {
            if (value instanceof Boolean) { return (Boolean) value; }
            if (value instanceof String) { return Boolean.valueOf((String) value); }
        }
        throw new IllegalArgumentException("Cannot cast " + value + " to booelan");
    }

}
