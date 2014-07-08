package pondero.util;

import static pondero.Logger.error;
import java.math.BigDecimal;

public class NumberUtil {

    public static boolean isOdd(final int i) {
        return (i & 1) == 1;
    }

    public static BigDecimal toDecimal(final Object value) {
        try {
            if (value instanceof BigDecimal) { return (BigDecimal) value; }
            if (value instanceof String) {
                String str = (String) value;
                str = str.trim().replace(',', '.');
                return new BigDecimal(str);
            }
            if (value instanceof Double || value instanceof Float) {
                final BigDecimal bd = BigDecimal.valueOf((double) value);
                try {
                    return bd.setScale(0);
                } catch (final ArithmeticException e) {
                    return bd;
                }
            }
            if (value instanceof Number) { return BigDecimal.valueOf(((Number) value).longValue()); }
        } catch (final Exception e) {
            error(e);
        }
        throw new UnsupportedOperationException("toFixed for " + value.getClass().getName());
    }

}
