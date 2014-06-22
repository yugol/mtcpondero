package pondero.model;

import java.io.File;
import pondero.model.excel.ExcelWorkbook;

public class WorkbookFactory {

    public static Workbook openWorkbook(final File file) throws Exception {
        return new ExcelWorkbook(file);
    }

}
