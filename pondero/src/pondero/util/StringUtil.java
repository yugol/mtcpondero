package pondero.util;

import pondero.model.wb.PType;

public class StringUtil {

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

    public static boolean isNullOrBlank(final String str) {
        if (str == null) { return true; }
        return str.trim().isEmpty();
    }

    public static String normalizeForSearch(final String foo) {
        if (isNullOrBlank(foo)) { return ""; }
        return replaceDiacritics(foo.toLowerCase());
    }

    public static String replaceDiacritics(String foo) {
        foo = foo.replace("\u0103", "a");
        foo = foo.replace("â", "a");
        foo = foo.replace("î", "i");
        foo = foo.replace("\u0219", "s");
        foo = foo.replace("\u021B", "t");
        return foo;
    }

    public static String toString(final Object value) {
        return String.valueOf(value);
    }

    public static String toString(final Object value, final PType type) {
        switch (type) {
            case STRING:
                return (String) value;
            case DATE:
                return DateUtil.toIsoDate(value);
            case TIME:
                return DateUtil.toIsoTime(value);
            case TIMESTAMP:
                return DateUtil.toIsoTimestamp(value);
            case FIXED:
            case FLOAT:
            case BOOLEAN:
            case ANY:
                return toString(value);
            default:
                throw new UnsupportedOperationException("toString for PType " + type);
        }
    }

}
