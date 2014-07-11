package pondero.data.model;

import java.math.BigDecimal;
import java.util.Random;

public enum PType {

    BOOLEAN,
    DATE,
    DECIMAL,
    FORMULA,
    INT,
    STRING,
    TIME,
    TIMESTAMP,

    ;

    public Object next() {
        final Random r = new Random();
        switch (this) {
            case BOOLEAN:
                return r.nextBoolean();
            case DECIMAL:
            case INT:
                return BigDecimal.valueOf(r.nextInt());
            case STRING:
                final StringBuilder sb = new StringBuilder();
                for (int i = 0; i < r.nextInt(10); ++i) {
                    sb.append((char) (r.nextInt(95) + 32));
                }
                return sb.toString();
            case DATE:
            case TIME:
            case TIMESTAMP:
                return r.nextLong();
            default:
                return null;
        }
    }

}
