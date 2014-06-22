package pondero.ui.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import pondero.model.Workbook;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class SaveDocumentAction extends AbstractAction {

    private final Pondero app;

    public SaveDocumentAction(final Pondero app) {
        this.app = app;
        putValue(NAME, Messages.getString("lbl.save")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/disk.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Workbook workbook = app.getCurrentWorkbook();
        if (workbook != null) {
            try {
                workbook.save();
            } catch (final IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
