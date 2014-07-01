package pondero.model.excel;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import pondero.L10n;

public class ExcelWorkbookFilter extends FileFilter {

    public static final String EXT = ".xlsx";

    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory()) { return true; }
        return pathname.getName().endsWith(EXT);
    }

    @Override
    public String getDescription() {
        return L10n.getString("lbl.pondero-data-file") + " (*" + EXT + ")";
    }

}
