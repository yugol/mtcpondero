package pondero.model.drivers.excel.templates;

import java.io.File;
import pondero.model.drivers.excel.ExcelDriver;
import pondero.model.foundation.PModel;
import pondero.model.foundation.TestModel;

public class TestTemplate extends ExcelDriver {

    public TestTemplate(final File dataFile) throws Exception {
        super(dataFile);
    }

    @Override
    public PModel fetchModel() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void pushModel(final PModel model) throws Exception {
        final TestModel testModel = (TestModel) model;
        commitSheet(testModel.getSheet());
        saveWorkbook();
    }

}
