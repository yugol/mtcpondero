package pondero.model.foundation;

import pondero.data.foundation.PModel;

public class TestModel extends PModel {

    public TestModel(final String name) {
        super(name);
    }

    public TestSheet getSheet() throws Exception {
        TestSheet sheet = (TestSheet) getSheet(TestSheet.NAME);
        if (sheet == null) {
            sheet = (TestSheet) addSheet(new TestSheet(this));
        }
        return sheet;
    }

}
