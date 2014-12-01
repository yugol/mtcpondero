package pondero.util;

import java.math.BigDecimal;
import pondero.ui.exceptions.ExceptionReporting;

public class NumberUtil {

    public static boolean isOdd(final int i) {
        return (i & 1) == 1;
    }

    public static BigDecimal toDecimal(final Object value) {
        if (value != null) {
            try {
                if (value instanceof BigDecimal) { return (BigDecimal) value; }
                if (value instanceof String) {
                    String str = (String) value;
                    str = str.trim().replace(',', '.');
                    return new BigDecimal(str);
                }
                if (value instanceof Double || value instanceof Float) {
                    final BigDecimal bd = BigDecimal.valueOf(((Number) value).doubleValue());
                    try {
                        return bd.setScale(0);
                    } catch (final ArithmeticException e) {
                        return bd;
                    }
                }
                if (value instanceof Number) { return BigDecimal.valueOf(((Number) value).longValue()); }
            } catch (final Exception e) {
                ExceptionReporting.showExceptionMessage(null, e);
            }
            throw new UnsupportedOperationException("toDecimal for " + value.getClass().getName());
        }
        return null;
    }

}
