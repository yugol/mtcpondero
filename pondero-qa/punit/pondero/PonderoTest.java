package pondero;

import static org.junit.Assert.assertNotNull;
import javax.swing.JButton;
import javax.swing.JTextField;
import org.junit.BeforeClass;
import org.junit.Test;
import pondero.ui.Pondero;

public class PonderoTest {

    protected static Pondero app;

    @BeforeClass
    public static void setUpClass() throws Exception {
        app = Pondero.start(new String[] {});
    }

    @Test
    public void test() throws InterruptedException {
        final JTextField participantCount = (JTextField) TestUtil.getChild(app.getMainFrame(), JTextField.class, 0);
        assertNotNull(participantCount);
        participantCount.setText("5");
        final JButton btnOk = (JButton) TestUtil.getChild(app.getMainFrame(), JButton.class, 0);
        assertNotNull(btnOk);
        btnOk.doClick();
        System.out.println("done");
    }

}
