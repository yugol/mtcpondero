package pondero.engine.staples;

public class StringUtil {

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

}
