package pondero.engine.staples;

import java.math.BigDecimal;

public class NumberUtil {

    public static BigDecimal toFixed(final Object value) {
        if (value instanceof BigDecimal) { return (BigDecimal) value; }
        throw new UnsupportedOperationException("toFixed for " + value.getClass().getName());
    }

    public static Double toFloat(final Object value) {
        if (value instanceof Double) { return (Double) value; }
        throw new UnsupportedOperationException("toFloat for " + value.getClass().getName());
    }

}
