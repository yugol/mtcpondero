package pondero.util;

import java.math.BigDecimal;

public class NumberUtil {

    public static BigDecimal toFixed(final Object value) {
        if (value instanceof Integer) { return BigDecimal.valueOf((Integer) value); }
        throw new UnsupportedOperationException("toFixed for " + value.getClass().getName());
    }

    public static Double toFloat(final Object value) {
        if (value instanceof Double) { return (Double) value; }
        throw new UnsupportedOperationException("toFloat for " + value.getClass().getName());
    }

}
