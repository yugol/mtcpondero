package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.MessageUtil;
import pondero.ui.Ponderable;
import pondero.ui.PonderoOld;

@SuppressWarnings("serial")
public class SaveDocumentAction extends PonderoAction {

    public SaveDocumentAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.save"));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/disk.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            getApp().getCurrentWorkbook().save();
        } catch (final IOException e) {
            error(e);
            MessageUtil.showExceptionMessage(getFrame(), e);
        }
    }

}
