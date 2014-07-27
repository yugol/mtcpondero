package pondero.data;

import java.io.File;

public class WorkbookFactory {

    public static Workbook openWorkbook(final File file) throws Exception {
        return new BasicWorkbook(file);
    }

}
