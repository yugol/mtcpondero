package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import pondero.Context;
import pondero.L10n;
import pondero.data.Workbook;
import pondero.data.drivers.excel.ExcelFileFilter;
import pondero.ui.Ponderable;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class SaveAsDocumentAction extends PonderoAction {

    public SaveAsDocumentAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.save-as..."));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        final JFileChooser dialog = new JFileChooser(".");
        dialog.setDialogTitle(L10n.getString("lbl.save-as-workbook"));
        dialog.setCurrentDirectory(Context.getFolderResults());
        dialog.setFileFilter(new ExcelFileFilter());
        if (JFileChooser.APPROVE_OPTION == dialog.showSaveDialog(getMainFrame())) {
            try {
                final Workbook wb = getCurrentWorkbook();
                wb.saveAs(dialog.getSelectedFile());
                getApp().setCurrentWorkbook(wb);
            } catch (final Exception e) {
                error(e);
                MsgUtil.showExceptionMessage(getMainFrame(), e);
            }
        }
    }

}
