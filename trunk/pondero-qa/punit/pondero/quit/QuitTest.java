package pondero.quit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pondero.Globals;
import pondero.TestUtil;
import pondero.ui.Pondero;

public class QuitTest {

    private static final File TEST_XLSX = new File("./pondero-qa/punit/pondeo/quit/quit.xlsx");

    private Pondero           app;

    @Before
    public void setUp() throws Exception {
        if (TEST_XLSX.exists()) {
            TEST_XLSX.delete();
        }
        assertFalse(TEST_XLSX.exists());
        Globals.setLastWorkbookFile(TEST_XLSX);
        app = TestUtil.getApp();
        assertTrue(app.getMainFrame().isVisible());
    }

    @After
    public void setTearDown() throws Exception {
        if (TEST_XLSX.exists()) {
            TEST_XLSX.delete();
        }
    }

    @Test
    public void testQuitCancel() throws Exception {
        final JMenuItem quitItem = (JMenuItem) TestUtil.getChild(app.getMainFrame(), Pondero.MENU_ITEM_QUIT_NAME);
        assertNotNull(quitItem);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                quitItem.doClick();
            }

        });

        final JButton btnCancel = (JButton) TestUtil.getChild(app.getMainFrame(), JButton.class, 2);
        assertNotNull(btnCancel);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                btnCancel.doClick();
            }

        });

        Thread.sleep(200);
        assertTrue(app.getMainFrame().isVisible());
        assertNull(TestUtil.getChild(app.getMainFrame(), JButton.class, 2));
        assertFalse(TEST_XLSX.exists());
    }

    @Test
    public void testQuitNoSave() throws Exception {
        final JMenuItem quitItem = (JMenuItem) TestUtil.getChild(app.getMainFrame(), Pondero.MENU_ITEM_QUIT_NAME);
        assertNotNull(quitItem);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                quitItem.doClick();
            }

        });

        final JButton btnNo = (JButton) TestUtil.getChild(app.getMainFrame(), JButton.class, 1);
        assertNotNull(btnNo);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                btnNo.doClick();
            }

        });

        Thread.sleep(200);
        assertFalse(app.getMainFrame().isVisible());
        assertFalse(TEST_XLSX.exists());
    }

    @Test
    public void testQuitSave() throws Exception {
        final JMenuItem quitItem = (JMenuItem) TestUtil.getChild(app.getMainFrame(), Pondero.MENU_ITEM_QUIT_NAME);
        assertNotNull(quitItem);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                quitItem.doClick();
            }

        });

        final JButton btnYes = (JButton) TestUtil.getChild(app.getMainFrame(), JButton.class, 0);
        assertNotNull(btnYes);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                btnYes.doClick();
            }

        });

        Thread.sleep(200);
        assertFalse(app.getMainFrame().isVisible());
        assertTrue(TEST_XLSX.exists());
    }

}
