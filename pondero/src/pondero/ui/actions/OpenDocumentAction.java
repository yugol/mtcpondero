package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pondero.Globals;
import pondero.model.WorkbookFactory;
import pondero.model.excel.ExcelWorkbookFilter;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class OpenDocumentAction extends PonderoAction {

    public OpenDocumentAction(final Pondero app) {
        super(app);
        putValue(NAME, Messages.getString("lbl.open...")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/folder_page.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        final JFileChooser dialog = new JFileChooser("."); //$NON-NLS-1$
        dialog.setCurrentDirectory(Globals.getFolderResults());
        dialog.setFileFilter(new ExcelWorkbookFilter());
        if (JFileChooser.APPROVE_OPTION == dialog.showOpenDialog(getApp().getFrame())) {
            try {
                getApp().openWorkbook(WorkbookFactory.openWorkbook(dialog.getSelectedFile()));
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

}
