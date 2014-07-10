package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pondero.Context;
import pondero.L10n;
import pondero.data.Workbook;
import pondero.data.WorkbookFactory;
import pondero.data.drivers.excel.ExcelFileFilter;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class OpenDocumentAction extends PonderoAction {

    public OpenDocumentAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.open..."));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/folder_page.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final Workbook wb = getCurrentWorkbook();
            if (wb != null) {
                if (wb.isDirty()) {
                    if (JOptionPane.showConfirmDialog(
                            getMainFrame(),
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
            dialog.setCurrentDirectory(Context.getFolderResults());
            dialog.setFileFilter(new ExcelFileFilter());
            if (JFileChooser.APPROVE_OPTION == dialog.showOpenDialog(getMainFrame())) {
                getApp().setCurrentWorkbook(WorkbookFactory.openWorkbook(dialog.getSelectedFile()));

            }
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
