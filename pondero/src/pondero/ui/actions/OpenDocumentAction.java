package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import pondero.Globals;
import pondero.model.excel.ExcelWorkbookFilter;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class OpenDocumentAction extends AbstractAction {

    private final Pondero app;

    public OpenDocumentAction(final Pondero app) {
        this.app = app;
        putValue(NAME, Messages.getString("lbl.open...")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/folder_page.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        final JFileChooser dialog = new JFileChooser("."); //$NON-NLS-1$
        dialog.setCurrentDirectory(Globals.getFolderResults());
        dialog.setFileFilter(new ExcelWorkbookFilter());
        if (JFileChooser.APPROVE_OPTION == dialog.showOpenDialog(app.getFrame())) {
            try {
                app.openWorkbook(dialog.getSelectedFile());
                // TODO create backup copy
            } catch (final Exception e) {
                error(e);
            }
        }
    }
}
