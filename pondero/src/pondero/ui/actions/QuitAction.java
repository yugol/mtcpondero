package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.Globals;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class QuitAction extends PonderoAction {

    public QuitAction(final Pondero app) {
        super(app);
        putValue(NAME, Messages.getString("lbl.quit")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/cancel.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        if (getApp().getCurrentWorkbook() != null) {
            if (getApp().getCurrentWorkbook().isDirty()) {
                if (JOptionPane.showConfirmDialog(
                        getApp().getFrame(),
                        Messages.getString("msg.save-workbook"), //$NON-NLS-1$
                        Messages.getString("lbl.pondero"), //$NON-NLS-1$
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        getApp().getCurrentWorkbook().save();
                    } catch (final IOException e) {
                        error(e);
                    }
                }
            }
            try {
                getApp().getCurrentWorkbook().close();
            } catch (IOException e) {
                error(e);
            }
        }
        for (File file : Globals.getFolderResultsTemp().listFiles()) {
            try {
                file.deleteOnExit();
            } catch (Exception e) {
                error(e);
            }
        }
        System.exit(0);
    }

}
