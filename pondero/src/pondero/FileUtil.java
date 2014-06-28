package pondero;

import static pondero.Logger.error;
import java.io.File;

public class FileUtil {

    public static void deleteTempFiles() {
        for (File file : Globals.getFolderResultsTemp().listFiles()) {
            try {
                file.deleteOnExit();
            } catch (Exception e) {
                error(e);
            }
        }
    }

}
