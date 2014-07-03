package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Pondero;
import pondero.ui.PonderoOld;
import pondero.ui.participants.ParticipantsManagementDialog;

@SuppressWarnings("serial")
public class ModifyParticipantAction extends PonderoAction {

    public ModifyParticipantAction(final Pondero app) {
        super(app);
        putValue(NAME, L10n.getString("html.modify-participant"));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/page_edit.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final ParticipantsManagementDialog dlg = new ParticipantsManagementDialog(getApp().getCurrentWorkbook());
            dlg.setModal(true);
            dlg.setVisible(true);
        } catch (final Exception e) {
            error(e);
        }
    }

}
