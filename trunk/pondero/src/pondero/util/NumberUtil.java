package pondero.util;

import static pondero.Logger.error;
import java.math.BigDecimal;

public class NumberUtil {

    public static BigDecimal toFixed(final Object value) {
        try {
            if (value instanceof BigDecimal) { return (BigDecimal) value; }
            if (value instanceof String) {
                String str = (String) value;
                str = str.trim().replace(',', '.');
                return new BigDecimal(str);
            }
            if (value instanceof Float) { return BigDecimal.valueOf((Float) value); }
            if (value instanceof Double) { return BigDecimal.valueOf((Double) value); }
            if (value instanceof Number) { return BigDecimal.valueOf(((Number) value).longValue()); }
        } catch (final Exception e) {
            error(e);
        }
        throw new UnsupportedOperationException("toFixed for " + value.getClass().getName());
    }

    public static Double toFloat(final Object value) {
        if (value instanceof Double) { return (Double) value; }
        throw new UnsupportedOperationException("toFloat for " + value.getClass().getName());
    }

    public static Integer toInteger(final Object value) {
        if (value != null) { return ((Number) value).intValue(); }
        return null;
    }
}
