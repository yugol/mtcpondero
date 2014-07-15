package pondero.util;

import java.io.File;
import pondero.Context;
import pondero.ui.exceptions.ExceptionReporting;

public final class FileUtil {

    public static void deleteTempFiles() {
        for (final File file : Context.getFolderResultsTemp().listFiles()) {
            try {
                file.deleteOnExit();
            } catch (final Exception e) {
                ExceptionReporting.showExceptionMessage(null, e);
            }
        }
    }

    private FileUtil() {
    }

}
