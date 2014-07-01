package pondero.model.excel;

import java.io.File;
import java.util.List;
import org.junit.Test;
import pondero.model.Workbook;
import pondero.model.entities.Participant;
import pondero.model.excel.ExcelWorkbook;

public class WorkbookTest {

    @Test
    public void testRead() throws Exception {
        final Workbook wb = new ExcelWorkbook("implicit.xlsx");
        @SuppressWarnings("unchecked")
        final List<Participant> participants = (List<Participant>) wb.getAll(Participant.class);
        System.out.println(participants);
    }

    // @Test
    public void testWrite() throws Exception {
        final String wbName = "test-workbook.xlsx";

        final File file = new File(wbName);
        if (file.exists()) {
            file.delete();
        }

        final Workbook wb = new ExcelWorkbook(wbName);
        for (int i = 0; i < 1000; ++i) {
            wb.add(new Participant());
        }
        wb.save();
    }

}
