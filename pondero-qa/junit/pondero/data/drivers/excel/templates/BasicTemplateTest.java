package pondero.data.drivers.excel.templates;

import org.junit.Test;
import pondero.Context;
import pondero.data.drivers.excel.templates.BasicTemplate;
import pondero.data.model.PSheet;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.Participants;

public class BasicTemplateTest {

    // @Test
    public void testGetModel() throws Exception {
        final BasicTemplate xlsx = new BasicTemplate(Context.getDefaultWorkbookFile());
        xlsx.open();
        final BasicModel model = xlsx.fetchModel();
        xlsx.close();
        for (final PSheet sheet : model) {
            System.out.println(sheet);
        }
    }

    @Test
    public void testPutModel() throws Exception {
        final BasicTemplate xlsx = new BasicTemplate(Context.getFolderResults().getCanonicalPath() + "/tesx.xlsx");
        xlsx.open();
        final BasicModel model = new BasicModel("abc");
        final Participants ps = model.getParticipants();
        for (int i = 1; i <= 100; ++i) {
            final Participant p = ps.addRow();
            p.randomize();
            p.setId(i);
        }
        for (final PSheet sheet : model) {
            System.out.println(sheet);
        }
        xlsx.pushModel(model);
        xlsx.close();
    }

}
