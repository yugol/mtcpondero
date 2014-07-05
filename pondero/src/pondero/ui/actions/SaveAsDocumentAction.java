package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import pondero.Globals;
import pondero.L10n;
import pondero.MsgUtil;
import pondero.model.Workbook;
import pondero.model.excel.ExcelWorkbookFilter;
import pondero.ui.Ponderable;

@SuppressWarnings("serial")
public class SaveAsDocumentAction extends PonderoAction {

    public SaveAsDocumentAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.save-as..."));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        final JFileChooser dialog = new JFileChooser(".");
        dialog.setCurrentDirectory(Globals.getFolderResults());
        dialog.setFileFilter(new ExcelWorkbookFilter());
        if (JFileChooser.APPROVE_OPTION == dialog.showSaveDialog(getApp().getFrame())) {
            try {
                Workbook wb = getApp().getCurrentWorkbook();
                wb.saveAs(dialog.getSelectedFile());
                getApp().setCurrentWorkbook(wb);
            } catch (final Exception e) {
                error(e);
                MsgUtil.showExceptionMessage(getFrame(), e);
            }
        }
    }

}
