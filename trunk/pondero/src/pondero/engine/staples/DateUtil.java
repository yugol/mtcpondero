package pondero.engine.staples;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import pondero.Globals;

public class DateUtil {

    public static final SimpleDateFormat DATE_FORMATTER    = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat TIME_FORMATTER    = new SimpleDateFormat("hh:mm:ss");
    private static final DateFormat      DATE_UI_FORMATTER = DateFormat.getDateInstance(DateFormat.LONG, Globals.getLocale());

    public static Calendar parseIsoDate(String dob) {
        try {
            if (StringUtil.isNullOrBlank(dob)) { return null; }
            Date date = DATE_FORMATTER.parse(dob);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            return cal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toIsoDate(Calendar cal) {
        return toIsoDate(cal.getTimeInMillis());
    }

    public static String toIsoDate(long time) {
        return DATE_FORMATTER.format(new Date(time));
    }

    public static String toIsoTime(long time) {
        return TIME_FORMATTER.format(new Date(time));
    }

    public static String toUiDate(Calendar cal) {
        return DATE_UI_FORMATTER.format(cal.getTime());
    }

}
