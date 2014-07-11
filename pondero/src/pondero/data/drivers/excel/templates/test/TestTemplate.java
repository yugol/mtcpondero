package pondero.data.drivers.excel.templates.test;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.data.drivers.excel.templates.AbstractTemplate;

public class TestTemplate extends AbstractTemplate {

    public static final String BASE_NAME = "test-data";

    public TestTemplate() {
        super(new XSSFWorkbook());
    }

}
