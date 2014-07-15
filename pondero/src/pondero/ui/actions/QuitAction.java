package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.Context;
import pondero.L10n;
import pondero.data.Workbook;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.util.FileUtil;

@SuppressWarnings("serial")
public class QuitAction extends PonderableAction {

    public QuitAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.quit"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/cancel.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
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
            action("quitting application");
            FileUtil.deleteTempFiles();
            getMainFrame().setVisible(false);
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
