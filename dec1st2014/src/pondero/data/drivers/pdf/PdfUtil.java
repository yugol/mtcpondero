package pondero.data.drivers.pdf;

public class PdfUtil {

    public static float mmToUnits(final float mm) {
        return mm * MM_TO_UNITS;
    }

    public static final String ro(String input) {
        input = input.replace('\u0103', '\u00e3');
        input = input.replace('â', '\u00e2');
        input = input.replace('î', '\u00ee');
        input = input.replace('\u0219', '\u00ba');
        input = input.replace('\u021B', '\u00fe');
        input = input.replace('\u0102', '\u00c3');
        input = input.replace('Â', '\u00c2');
        input = input.replace('Î', '\u00ce');
        input = input.replace('\u0218', '\u00aa');
        input = input.replace('\u021A', '\u00de');
        return input;
    }

    private static final int   DEFAULT_USER_SPACE_UNIT_DPI = 72;
    private static final float MM_TO_UNITS                 = 1 / (10 * 2.54f) * DEFAULT_USER_SPACE_UNIT_DPI;

    public static final float  A4_WIDTH                    = 595.27563f;
    public static final float  A4_HEIGHT                   = 841.8898f;

}
