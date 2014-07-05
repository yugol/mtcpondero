package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.FileUtil;
import pondero.L10n;
import pondero.MsgUtil;
import pondero.model.Workbook;
import pondero.ui.Ponderable;
import pondero.ui.PonderoOld;

@SuppressWarnings("serial")
public class QuitAction extends PonderoAction {

    public QuitAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.quit"));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/cancel.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            Workbook wb = getApp().getCurrentWorkbook();
            if (wb != null) {
                if (wb.isDirty()) {
                    if (JOptionPane.showConfirmDialog(
                            getApp().getFrame(),
                            L10n.getString("msg.save-workbook", wb.getWorkbookName()),
                            L10n.getString("lbl.pondero"),
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
