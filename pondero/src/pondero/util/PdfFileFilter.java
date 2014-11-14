package pondero.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import pondero.L10n;

public class PdfFileFilter extends FileFilter {

    public static final String DEFAULT_EXTENSION = ".pdf";

    @Override
    public boolean accept(final File pathname) {
        if (pathname.isDirectory()) { return true; }
        return pathname.getName().endsWith(DEFAULT_EXTENSION);
    }

    @Override
    public String getDescription() {
        return L10n.getString("lbl.pondero-report-file") + " (*" + DEFAULT_EXTENSION + ")";
    }

}
