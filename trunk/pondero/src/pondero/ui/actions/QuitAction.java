package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.model.Workbook;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.util.FileUtil;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class QuitAction extends PonderoAction {

    public QuitAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.quit"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/cancel.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final Workbook wb = getApp().getCurrentWorkbook();
            if (wb != null) {
                if (wb.isDirty()) {
                    final int decision = JOptionPane.showConfirmDialog(getFrame(),
                            L10n.getString("msg.save-workbook", wb.getName()),
                            L10n.getString("lbl.pondero"),
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (decision == JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                    else if (decision == JOptionPane.YES_OPTION) {
                        wb.save();
                    }
                }
                wb.close();
            }
            FileUtil.deleteTempFiles();
            System.exit(0);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getFrame(), e);
        }
    }

}
