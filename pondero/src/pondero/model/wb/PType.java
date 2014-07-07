package pondero.model.wb;

import java.math.BigDecimal;
import java.util.Calendar;

public enum PType {

    ANY (Object.class),
    FIXED (BigDecimal.class),
    FLOAT (Double.class),
    STRING (String.class),
    DATE (Calendar.class),
    TIME (Calendar.class),
    TIMESTAMP (Calendar.class),

    ;

    public final Class<?> jType;

    private PType(final Class<?> jType) {
        this.jType = jType;
    }

}
