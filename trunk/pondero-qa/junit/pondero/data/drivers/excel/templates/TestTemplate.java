package pondero.data.drivers.excel.templates;

import java.io.File;
import pondero.data.drivers.excel.ExcelDriver;
import pondero.data.foundation.TestModel;
import pondero.data.foundation.TestSheet;
import pondero.data.model.PModel;

public class TestTemplate extends ExcelDriver {

    public TestTemplate(final File dataFile) throws Exception {
        super(dataFile);
    }

    @Override
    public TestModel fetchModel() throws Exception {
        final TestModel model = new TestModel(new File(getConnectionString()).getName());
        readSheet(model.getSheet(), getSheet(TestSheet.NAME));
        model.setDirty(false);
        return model;
    }

    @Override
    public void pushModel(final PModel model) throws Exception {
        final TestModel testModel = (TestModel) model;
        commitSheet(testModel.getSheet());
        saveWorkbook();
    }

}
