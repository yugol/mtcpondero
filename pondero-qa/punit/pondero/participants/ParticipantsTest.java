package pondero.participants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pondero.Globals;
import pondero.PonderoTest;
import pondero.ui.Pondero;
import pondero.ui.participants.ParticipantManagementDialog;

public class ParticipantsTest extends PonderoTest {

    private static final File TEST_XLSX = new File("./punit/pondero/participants/participants.xlsx");

    private Pondero           app;

    @Before
    public void setUp() throws Exception {
        System.out.println(TEST_XLSX.getCanonicalPath());
        if (TEST_XLSX.exists()) {
            TEST_XLSX.delete();
        }
        assertFalse(TEST_XLSX.exists());
        Globals.setLastWorkbookFile(TEST_XLSX);
        app = getAppWithoutParticipants();
        assertTrue(app.getMainFrame().isVisible());
    }

    @After
    public void setTearDown() throws Exception {
        if (TEST_XLSX.exists()) {
            TEST_XLSX.delete();
        }
    }

    @Test
    public void testAdd() throws Exception {
        final JEditorPane report = (JEditorPane) getChild(app.getMainFrame(), Pondero.REPORT_PARTICIPANT_NAME);
        assertNotNull(report);
        assertFalse(report.getText().contains("<h1>"));
        final JButton btnSelect = (JButton) getChild(app.getMainFrame(), Pondero.BUTTON_SELECT_NAME);
        assertNotNull(btnSelect);
        assertFalse(btnSelect.isEnabled());
        final JButton btnAdd = (JButton) getChild(app.getMainFrame(), Pondero.BUTTON_ADD_NAME);
        assertNotNull(btnAdd);
        assertTrue(btnAdd.isEnabled());
        final JButton btnModify = (JButton) getChild(app.getMainFrame(), Pondero.BUTTON_MODIFY_NAME);
        assertNotNull(btnModify);
        assertFalse(btnModify.isEnabled());
        final JButton btnNext = (JButton) getChild(app.getMainFrame(), Pondero.BUTTON_NEXT_NAME);
        assertNotNull(btnNext);
        assertFalse(btnModify.isEnabled());

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                btnAdd.doClick();
            }

        });

        JDialog dlg = (JDialog) getWindow(app.getMainFrame(), ParticipantManagementDialog.DIALOG_NAME);
        assertNotNull(dlg);

        final JButton btnCancel = (JButton) getChild(dlg, ParticipantManagementDialog.BUTTON_CANCEL_NAME);
        assertNotNull(btnCancel);
        btnCancel.doClick();

        assertFalse(report.getText().contains("<h1>"));
        assertFalse(btnSelect.isEnabled());
        assertTrue(btnAdd.isEnabled());
        assertFalse(btnModify.isEnabled());
        assertFalse(btnModify.isEnabled());

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                btnAdd.doClick();
            }

        });

        dlg = (JDialog) getWindow(app.getMainFrame(), ParticipantManagementDialog.DIALOG_NAME);
        assertNotNull(dlg);

        final JTextField idField = (JTextField) getChild(dlg, ParticipantManagementDialog.FIELD_ID_NAME);
        assertNotNull(idField);
        assertFalse(idField.isEnabled());
        assertEquals("101", idField.getText());
        final JTextField surnameField = (JTextField) getChild(dlg, ParticipantManagementDialog.FIELD_SURNAME_NAME);
        assertNotNull(surnameField);
        assertTrue(surnameField.isEnabled());
        final JTextField nameField = (JTextField) getChild(dlg, ParticipantManagementDialog.FIELD_NAME_NAME);
        assertNotNull(nameField);
        assertTrue(nameField.isEnabled());
        final JButton btnSave = (JButton) getChild(dlg, ParticipantManagementDialog.BUTTON_SAVE_NAME);
        assertNotNull(btnSave);
        assertFalse(btnSave.isEnabled());

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                surnameField.setText("NUME");
                nameField.setText("PRENUME");
                btnSave.setEnabled(true);
                btnSave.doClick();
            }

        });

        Thread.sleep(500);
        assertFalse(dlg.isVisible());
        assertTrue(report.getText().contains("NUME"));
    }

}
