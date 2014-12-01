package pondero.data.drivers.excel.templates.test;

import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.data.analysis.PMatrix;
import pondero.data.drivers.excel.templates.AbstractTemplate;
import pondero.data.drivers.excel.templates.WbLocation;

public class TestTemplate extends AbstractTemplate {

    public static final String  BASE_NAME            = "test-data";

    private static final String RESPONSES_SHEET_NAME = "R\u0102SPUNSURI";
    private static final String TIMS_SHEET_NAME      = "TIMPI";

    public TestTemplate() {
        super(new XSSFWorkbook());
    }

    public void addResponses(final PMatrix matrix) {
        final WbLocation loc = new WbLocation(getSheet(RESPONSES_SHEET_NAME), 0, 0);
        renderTable(loc, matrix);
    }

    public void addTimes(final PMatrix matrix) {
        final WbLocation loc = new WbLocation(getSheet(TIMS_SHEET_NAME), 0, 0);
        renderTable(loc, matrix);
    }

    @Override
    public void save(final File reportFile) throws Exception {
        // getSheet(RESPONSES_SHEET_NAME).autoSizeColumn(0);
        getSheet(RESPONSES_SHEET_NAME).autoSizeColumn(1);
        getSheet(RESPONSES_SHEET_NAME).autoSizeColumn(2);
        // getSheet(TIMS_SHEET_NAME).autoSizeColumn(0);
        getSheet(TIMS_SHEET_NAME).autoSizeColumn(1);
        getSheet(TIMS_SHEET_NAME).autoSizeColumn(2);
        super.save(reportFile);
    }

}
