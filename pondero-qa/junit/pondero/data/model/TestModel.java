package pondero.data.model;

public class TestModel extends PModel {

    public TestModel(final String name) {
        super(name);
    }

    public TestSheet getSheet() throws Exception {
        TestSheet sheet = (TestSheet) getSheet(TestSheet.NAME);
        if (sheet == null) {
            sheet = (TestSheet) addSheet(new TestSheet(this, true));
        }
        return sheet;
    }

    public TestSheet getUnlockedSheet() throws Exception {
        TestSheet sheet = (TestSheet) getSheet(TestSheet.NAME);
        if (sheet == null) {
            sheet = (TestSheet) addSheet(new TestSheet(this, false));
        }
        return sheet;
    }

}
