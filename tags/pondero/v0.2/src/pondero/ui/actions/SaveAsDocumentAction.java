package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pondero.Globals;
import pondero.model.Workbook;
import pondero.model.excel.ExcelWorkbookFilter;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class SaveAsDocumentAction extends PonderoAction {

    public SaveAsDocumentAction(final Pondero app) {
        super(app);
        putValue(NAME, Messages.getString("lbl.save-as..."));
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
                getApp().openWorkbook(wb);
            } catch (final Exception e) {
                error(e);
                JOptionPane.showInternalMessageDialog(
                        getApp().getFrame(),
                        e.getMessage(),
                        Messages.getString("lbl.save-as..."),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
