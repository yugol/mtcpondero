package pondero.ui.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class QuitAction extends AbstractAction {

    private final Pondero app;

    public QuitAction(final Pondero app) {
        this.app = app;
        putValue(NAME, Messages.getString("lbl.quit")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/cancel.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (app.getCurrentWorkbook() != null) {
            if (app.getCurrentWorkbook().isDirty()) {
                if (JOptionPane.showConfirmDialog(
                        app.getFrame(),
                        Messages.getString("msg.save-workbook"), //$NON-NLS-1$
                        Messages.getString("lbl.pondero"), //$NON-NLS-1$
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        app.getCurrentWorkbook().save();
                    } catch (final IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        System.exit(0);
    }

}
