package pondero.util;

import static pondero.Logger.error;
import java.io.File;
import pondero.Globals;

public final class FileUtil {

    public static void deleteTempFiles() {
        for (final File file : Globals.getFolderResultsTemp().listFiles()) {
            try {
                file.deleteOnExit();
            } catch (final Exception e) {
                error(e);
            }
        }
    }

    private FileUtil() {
    }

}
