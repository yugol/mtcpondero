package pondero.util;

public final class BooleanUtil {

    private BooleanUtil() {
    }

    public static Boolean toBoolean(final Object value) {
        if (value != null) {
            if (value instanceof Boolean) { return (Boolean) value; }
            if (value instanceof Number) { return ((Number) value).intValue() != 0; }
            if (value instanceof String) {
                final String str = ((String) value).trim();
                if (StringUtil.isNullOrBlank(str)) { return null; }
                return Boolean.valueOf((String) value);
            }
        }
        return null;
    }

}
