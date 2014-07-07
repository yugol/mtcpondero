package pondero.model.drivers.excel.templates;

import org.junit.Test;
import pondero.Globals;
import pondero.model.foundation.PSheet;
import pondero.model.foundation.basic.BasicModel;

public class BasicTemplateTest {

    @Test
    public void testGetModel() throws Exception {
        final BasicTemplate xlsx = new BasicTemplate(Globals.getDefaultWorkbookFile().getCanonicalPath());
        xlsx.open();
        final BasicModel model = xlsx.getModel();
        xlsx.close();
        for (final PSheet sheet : model) {
            System.out.println(sheet);
        }
    }

}
