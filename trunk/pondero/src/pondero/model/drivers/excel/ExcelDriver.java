package pondero.model.drivers.excel;

import static pondero.Logger.info;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.model.drivers.Driver;

public abstract class ExcelDriver extends Driver {

    public static final String DEFAULT_FILE_EXTENSION = ".xlsx";

    private File               dataFile;
    private XSSFWorkbook       workbook;

    public ExcelDriver(final String connectionString) {
        super(connectionString);
    }

    @Override
    public void close() throws Exception {
        info("closing data file: ", getConnectionString());
        workbook = null;
        dataFile = null;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    @Override
    public void open() throws Exception {
        dataFile = new File(getConnectionString());
        if (dataFile.exists()) {
            info("opening existing data file: ", getConnectionString());
            try (InputStream wbIn = new FileInputStream(dataFile)) {
                workbook = new XSSFWorkbook(wbIn);
            }
        } else {
            String filePath = dataFile.getCanonicalPath();
            if (!filePath.endsWith(DEFAULT_FILE_EXTENSION)) {
                filePath += DEFAULT_FILE_EXTENSION;
                dataFile = new File(filePath);
            }
            info("opening existing data file: ", getConnectionString());
            workbook = new XSSFWorkbook();
        }
        setConnectionString(dataFile.getCanonicalPath());
    }

    @Override
    public void save() throws Exception {
        info("saving data file: ", getConnectionString());
        try (FileOutputStream fileOut = new FileOutputStream(dataFile)) {
            workbook.write(fileOut);
        }
    }

    protected Sheet getSheet(final String name) {
        Sheet sheet = workbook.getSheet(name);
        if (sheet == null) {
            sheet = workbook.createSheet(name);
        }
        return sheet;
    }

}
