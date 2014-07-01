package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class SaveDocumentAction extends PonderoAction {

    public SaveDocumentAction(final Pondero app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.save")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/disk.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            getApp().getCurrentWorkbook().save();
        } catch (final IOException e) {
            error(e);
            JOptionPane.showInternalMessageDialog(
                    getApp().getFrame(),
                    e.getMessage(),
                    L10n.getString("lbl.open-externally"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
