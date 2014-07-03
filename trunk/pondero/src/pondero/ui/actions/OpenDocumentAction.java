package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pondero.Globals;
import pondero.L10n;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.model.excel.ExcelWorkbookFilter;
import pondero.ui.Ponderable;
import pondero.ui.PonderoOld;

@SuppressWarnings("serial")
public class OpenDocumentAction extends PonderoAction {

    public OpenDocumentAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.open..."));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/folder_page.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            Workbook wb = getApp().getCurrentWorkbook();
            if (wb != null) {
                if (wb.isDirty()) {
                    if (JOptionPane.showConfirmDialog(
                            getApp().getFrame(),
                            L10n.getString("msg.save-workbook", wb.getName()),
                            L10n.getString("lbl.pondero"),
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        wb.save();
                    }
                }
                wb.close();
            }
            final JFileChooser dialog = new JFileChooser(".");
            dialog.setDialogTitle(L10n.getString("lbl.open-workbook"));
            dialog.setCurrentDirectory(Globals.getFolderResults());
            dialog.setFileFilter(new ExcelWorkbookFilter());
            if (JFileChooser.APPROVE_OPTION == dialog.showOpenDialog(getApp().getFrame())) {
                getApp().setWorkbook(WorkbookFactory.openWorkbook(dialog.getSelectedFile()));

            }
        } catch (final Exception e) {
            error(e);
            JOptionPane.showInternalMessageDialog(
                    getApp().getFrame(),
                    e.getMessage(),
                    L10n.getString("lbl.open..."),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
