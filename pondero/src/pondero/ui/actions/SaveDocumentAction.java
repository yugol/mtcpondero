package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class SaveDocumentAction extends PonderoAction {

    public SaveDocumentAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.save"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/disk.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            getCurrentWorkbook().save();
        } catch (final IOException e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
