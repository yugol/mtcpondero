package pondero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import pondero.engine.staples.DateUtil;

public class Logger {

    public static final int    NONE            = 0;
    public static final int    CRITICAL        = 10;
    public static final int    ERROR           = 20;
    public static final int    WARNING         = 30;
    public static final int    INFO            = 40;
    public static final int    DEBUG           = 50;
    public static final int    TRACE           = 60;

    public static int          maxConsoleLevel = INFO;
    public static int          maxFileLevel    = NONE;

    private static final File  logFile         = new File(Globals.getFolderLog(), buildLogFileName());
    private static PrintStream logFileOut;

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

    private static String buildLogFileName() {
        long timestamp = System.currentTimeMillis();
        return "pondero-" + DateUtil.toIsoDate(timestamp) + "-" + DateUtil.toDotTime(timestamp) + ".log";
    }

    private static int getMaxLoggableLevel() {
        return Math.max(maxConsoleLevel, maxFileLevel);
    }

    private static PrintStream log() {
        if (logFileOut == null) {
            try {
                logFileOut = new PrintStream(logFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return logFileOut;
    }

    private static void log(int level, Object... obj) {
        if (level <= getMaxLoggableLevel()) {
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
        if (maxConsoleLevel > NONE) {
            switch (level) {
                case ERROR:
                case CRITICAL:
                    System.err.println(logEntry.toString());
                    break;
                default:
                    System.out.println(logEntry.toString());
            }
        }
        if (maxFileLevel > NONE) {
            log().println(logEntry.toString());
            log().flush();
        }
    }

}
