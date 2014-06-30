package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.FileUtil;
import pondero.model.Workbook;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class QuitAction extends PonderoAction {

    public QuitAction(final Pondero app) {
        super(app);
        putValue(NAME, Messages.getString("lbl.quit"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/cancel.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            Workbook wb = getApp().getCurrentWorkbook();
            if (wb != null) {
                if (wb.isDirty()) {
                    if (JOptionPane.showConfirmDialog(
                            getApp().getFrame(),
                            Messages.getString("msg.save-workbook", wb.getName()),
                            Messages.getString("lbl.pondero"),
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
            JOptionPane.showInternalMessageDialog(
                    getApp().getFrame(),
                    e.getMessage(),
                    Messages.getString("lbl.quit"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
