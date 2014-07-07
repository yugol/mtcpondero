package pondero.model.wb;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public enum PType {

    ANY (Object.class),
    FIXED (BigDecimal.class),
    FLOAT (Double.class),
    STRING (String.class),
    DATE (Date.class),
    TIME (Time.class),
    TIMESTAMP (Timestamp.class),

    ;

    public final Class<?> jType;

    private PType(final Class<?> jType) {
        this.jType = jType;
    }

}
