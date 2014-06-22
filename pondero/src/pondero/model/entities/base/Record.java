package pondero.model.entities.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Record {

    public static String camel2Constant(final String name) {
        final StringBuilder constant = new StringBuilder();
        for (int i = 0; i < name.length(); ++i) {
            final char ch = name.charAt(i);
            if (Character.isUpperCase(ch)) {
                constant.append("_");
            }
            constant.append(Character.toUpperCase(ch));
        }
        return constant.toString();
    }

    public static String constant2Pascal(final String constant) {
        final StringBuilder pascal = new StringBuilder();
        final String[] chunks = constant.toLowerCase().split("_");
        for (final String chunk : chunks) {
            pascal.append(Character.toUpperCase(chunk.charAt(0)));
            if (chunk.length() > 1) {
                pascal.append(chunk.substring(1));
            }
        }
        return pascal.toString();
    }

    public static String getGetterName(final String cellName) {
        return "get" + constant2Pascal(cellName) + "String";
    }

    public static String getSetterName(final String cellName) {
        return "set" + constant2Pascal(cellName);
    }

    public List<String> getColumnNames() {
        final List<String> columns = new ArrayList<String>();
        for (final Field field : getClass().getDeclaredFields()) {
            if (field.getAnnotation(Column.class) != null) {
                columns.add(camel2Constant(field.getName()));
            }
        }
        return columns;
    }

    public String getSheetName() {
        final Sheet sheet = this.getClass().getAnnotation(Sheet.class);
        return sheet.name();
    }

}
