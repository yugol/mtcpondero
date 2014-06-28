package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pondero.Globals;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.model.excel.ExcelWorkbookFilter;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class OpenDocumentAction extends PonderoAction {

    public OpenDocumentAction(final Pondero app) {
        super(app);
        putValue(NAME, Messages.getString("lbl.open..."));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/folder_page.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            boolean canOpen = true;
            Workbook wb = getApp().getCurrentWorkbook();
            if (wb != null) {
                if (wb.isDirty()) {
                    if (JOptionPane.showConfirmDialog(
                            getApp().getFrame(),
                            Messages.getString("msg.save-workbook", wb.getName()),
                            Messages.getString("lbl.pondero"),
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        wb.save();
                    } else {
                        canOpen = false;
                    }
                }
                if (canOpen) {
                    wb.close();
                }
            }
            if (canOpen) {
                final JFileChooser dialog = new JFileChooser(".");
                dialog.setCurrentDirectory(Globals.getFolderResults());
                dialog.setFileFilter(new ExcelWorkbookFilter());
                if (JFileChooser.APPROVE_OPTION == dialog.showOpenDialog(getApp().getFrame())) {
                    getApp().openWorkbook(WorkbookFactory.openWorkbook(dialog.getSelectedFile()));

                }
            }
        } catch (final Exception e) {
            error(e);
            JOptionPane.showInternalMessageDialog(
                    getApp().getFrame(),
                    e.getMessage(),
                    Messages.getString("lbl.open..."),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
