package pondero.engine.staples;

import static pondero.Logger.error;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import pondero.Globals;

public class DateUtil {

    public static final SimpleDateFormat  ISO_DATE_FORMATTER     = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat  ISO_TIME_FORMATTER     = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat COMPACT_DATE_FORMATTER = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat COMPACT_TIME_FORMATTER = new SimpleDateFormat("HHmmss");
    private static final DateFormat       DATE_UI_FORMATTER      = DateFormat.getDateInstance(DateFormat.LONG, Globals.getLocale());

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Calendar parseIsoDate(String dob) {
        try {
            if (StringUtil.isNullOrBlank(dob)) { return null; }
            Date date = ISO_DATE_FORMATTER.parse(dob);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            return cal;
        } catch (Exception e) {
            error(e);
            return null;
        }
    }

    public static String toCompactDate(long time) {
        return COMPACT_DATE_FORMATTER.format(new Date(time));
    }

    public static String toCompactTime(long time) {
        return COMPACT_TIME_FORMATTER.format(new Date(time));
    }

    public static String toIsoDate(Calendar cal) {
        return toIsoDate(cal.getTimeInMillis());
    }

    public static String toIsoDate(long time) {
        return ISO_DATE_FORMATTER.format(new Date(time));
    }

    public static String toIsoTime(long time) {
        return ISO_TIME_FORMATTER.format(new Date(time));
    }

    public static String toUiDate(Calendar cal) {
        return DATE_UI_FORMATTER.format(cal.getTime());
    }

}
