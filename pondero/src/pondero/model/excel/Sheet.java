package pondero.model.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Sheet {

    String name();

}
