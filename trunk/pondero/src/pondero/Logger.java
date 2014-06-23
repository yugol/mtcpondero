package pondero;

import pondero.engine.staples.DateUtil;

public class Logger {

    private static final int CRITICAL  = 10;
    private static final int ERROR     = 20;
    private static final int WARNING   = 30;
    private static final int INFO      = 40;
    private static final int DEBUG     = 50;
    private static final int TRACE     = 60;

    private static final int MAX_LEVEL = TRACE;

    public static void critical(Object... obj) {
        log(CRITICAL, obj);
    }

    public static void debug(Object... obj) {
        log(DEBUG, obj);
    }

    public static void error(Object... obj) {
        log(ERROR, obj);
    }

    public static void info(Object... obj) {
        log(INFO, obj);
    }

    public static void trace(Object... obj) {
        log(TRACE, obj);
    }

    public static void warning(Object... obj) {
        log(WARNING, obj);
    }

    private static void log(int level, Object... obj) {
        if (level <= MAX_LEVEL) {
            if (obj == null) {
                println(level, "null");
            } else if (obj instanceof Object[]) {
                StringBuilder msg = new StringBuilder();
                for (Object item : obj) {
                    msg.append(item == null ? "null" : String.valueOf(item));
                }
                println(level, msg.toString());
            } else {
                println(level, String.valueOf(obj));
            }
        }
    }

    private static void println(int level, String msg) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[4];
        StringBuilder logEntry = new StringBuilder();
        logEntry.append(DateUtil.toIsoTime(System.currentTimeMillis()));
        logEntry.append(" (");
        logEntry.append(element.getFileName());
        logEntry.append(":");
        logEntry.append(element.getLineNumber());
        logEntry.append(")");
        logEntry.append(" ");
        logEntry.append(msg);
        switch (level) {
            case ERROR:
            case CRITICAL:
                System.err.println(logEntry.toString());
                break;
            default:
                System.out.println(logEntry.toString());
        }
    }

}
