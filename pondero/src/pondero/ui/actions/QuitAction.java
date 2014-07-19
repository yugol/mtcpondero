package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import pondero.Context;
import pondero.L10n;
import pondero.data.Workbook;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;

@SuppressWarnings("serial")
public class QuitAction extends PonderableAction {

    public QuitAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.quit"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/cancel.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        closeApplication(false);
    }

    protected void closeApplication(final boolean restart) {
        try {
            final Workbook wb = getCurrentWorkbook();
            if (wb != null) {
                if (wb.isDirty()) {
                    final int decision = JOptionPane.showConfirmDialog(getMainFrame(),
                            L10n.getString("msg.save-workbook", wb.getName()),
                            L10n.getString("lbl.pondero"),
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (decision == JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                    else if (decision == JOptionPane.YES_OPTION) {
                        action("saving workbook: ", wb.getName());
                        wb.save();
                    }
                }
                wb.close();
            }

            FileUtils.deleteQuietly(Context.getFolderResultsTemp());
            getMainFrame().setVisible(false);
            if (restart) {
                action("restarting application");
                final File ponderoBat = new File(Context.getFolderHome(), "pondero.bat");
                Runtime.getRuntime().exec(ponderoBat.getCanonicalPath());
            } else {
                action("closing application");
            }
            if (Context.isRunningFromIde()) {
                getMainFrame().dispose();
            } else {
                System.exit(0);
            }
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }
    }

}
