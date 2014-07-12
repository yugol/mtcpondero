package pondero.ui.actions;

import static pondero.Logger.action;
import static pondero.Logger.error;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pondero.Context;
import pondero.L10n;
import pondero.data.Workbook;
import pondero.data.WorkbookFactory;
import pondero.data.drivers.excel.ExcelFileFilter;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.Participants;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class OpenDocumentAction extends PonderableAction {

    public static final int MAX_PARTICIPANT_COUNT = 30;

    public OpenDocumentAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.open..."));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/folder_page.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {

            // ask user whether to save or not the changes in the register
            final Workbook wb = getCurrentWorkbook();
            if (wb != null) {
                if (wb.isDirty()) {
                    if (JOptionPane.showConfirmDialog(
                            getMainFrame(),
                            L10n.getString("msg.save-workbook", wb.getName()),
                            L10n.getString("lbl.pondero"),
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        action("saving workbook: ", wb.getName());
                        wb.save();
                    }
                }
                wb.close();
            }

            // open a new register
            final JFileChooser dialog = new JFileChooser(".");
            dialog.setDialogTitle(L10n.getString("lbl.open-workbook"));
            dialog.setCurrentDirectory(Context.getFolderResults());
            dialog.setFileFilter(new ExcelFileFilter());
            if (JFileChooser.APPROVE_OPTION == dialog.showOpenDialog(getMainFrame())) {
                final Workbook workbook = WorkbookFactory.openWorkbook(dialog.getSelectedFile());
                openWorkbook(workbook);
            }

        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

    public void openWorkbook(final Workbook workbook) throws Exception {
        action("opening workbook: ", workbook.getName());
        if (workbook.getParticipantCount() <= 0) {
            final String decision = JOptionPane.showInputDialog(getMainFrame(), L10n.getString("msg.how-many-random-participants", MAX_PARTICIPANT_COUNT), "0");
            try {
                int count = Integer.parseInt(decision);
                if (count < 0) {
                    count = 0;
                }
                if (count > MAX_PARTICIPANT_COUNT) {
                    count = MAX_PARTICIPANT_COUNT;
                }
                if (count > 0) {
                    getMainFrame().setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    for (int i = 1; i <= count; ++i) {
                        final Participant p = workbook.addParticipant();
                        p.randomize();
                        p.setId(Participants.HASH + i);
                    }
                }
                action("adding ", count, " random participants");
            } catch (final NumberFormatException e) {
            } finally {
                getMainFrame().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
        getApp().setCurrentWorkbook(workbook);
    }

}
