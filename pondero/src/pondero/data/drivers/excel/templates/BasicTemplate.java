package pondero.data.drivers.excel.templates;

import java.io.File;
import org.apache.poi.ss.usermodel.Sheet;
import pondero.data.drivers.excel.ExcelDriver;
import pondero.data.foundation.PModel;
import pondero.data.foundation.PSheet;
import pondero.data.foundation.basic.BasicModel;
import pondero.data.foundation.basic.Participants;

public class BasicTemplate extends ExcelDriver {

    public BasicTemplate(final File dataFile) throws Exception {
        super(dataFile);
    }

    public BasicTemplate(final String connectionString) {
        super(connectionString);
    }

    @Override
    public BasicModel fetchModel() throws Exception {
        final BasicModel model = new BasicModel(new File(getConnectionString()).getName());
        for (int sheetIdx = 0; sheetIdx < getWorkbook().getNumberOfSheets(); ++sheetIdx) {
            final Sheet sheet = getWorkbook().getSheetAt(sheetIdx);
            final String sheetName = sheet.getSheetName();
            if (Participants.NAME.equals(sheetName)) {
                readSheet(model.getParticipants(), sheet);
            } else {
                readSheet(model.getRecords(sheetName), sheet);
            }
        }
        return model;
    }

    @Override
    public void pushModel(final PModel model) throws Exception {
        for (final PSheet pSheet : model) {
            commitSheet(pSheet);
        }
        saveWorkbook();
    }

}
