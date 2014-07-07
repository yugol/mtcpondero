package pondero.model.excel;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import pondero.L10n;
import pondero.model.drivers.excel.ExcelDriver;

public class ExcelWorkbookFilter extends FileFilter {

    public static final String EXT = ExcelDriver.DEFAULT_FILE_EXTENSION;

    @Override
    public boolean accept(final File pathname) {
        if (pathname.isDirectory()) { return true; }
        return pathname.getName().endsWith(EXT);
    }

    @Override
    public String getDescription() {
        return L10n.getString("lbl.pondero-data-file") + " (*" + EXT + ")";
    }

}
