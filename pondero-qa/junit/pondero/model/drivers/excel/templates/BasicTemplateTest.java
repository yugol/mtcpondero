package pondero.model.drivers.excel.templates;

import org.junit.Test;
import pondero.Globals;
import pondero.model.foundation.PSheet;
import pondero.model.foundation.basic.BasicModel;
import pondero.model.foundation.basic.Participant;
import pondero.model.foundation.basic.Participants;

public class BasicTemplateTest {

    // @Test
    public void testGetModel() throws Exception {
        final BasicTemplate xlsx = new BasicTemplate(Globals.getDefaultWorkbookFile().getCanonicalPath());
        xlsx.open();
        final BasicModel model = xlsx.fetchModel();
        xlsx.close();
        for (final PSheet sheet : model) {
            System.out.println(sheet);
        }
    }

    @Test
    public void testPutModel() throws Exception {
        final BasicTemplate xlsx = new BasicTemplate(Globals.getFolderResults().getCanonicalPath() + "/tesx.xlsx");
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
        xlsx.commitModel(model);
        xlsx.close();
    }

}
