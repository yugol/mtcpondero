package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.data.foundation.basic.Participant;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.participants.ParticipantSelectionDialog;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class ChooseParticipantAction extends PonderoAction {

    public ChooseParticipantAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("html.choose-participant"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/find.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final ParticipantSelectionDialog dlg = new ParticipantSelectionDialog(getMainFrame(), getCurrentWorkbook());
            dlg.setLocationRelativeTo(getMainFrame());
            dlg.setModal(true);
            dlg.setVisible(true);
            if (dlg.getCloseOperation() == JOptionPane.YES_OPTION) {
                final Participant selection = dlg.getSelection();
                if (selection != null) {
                    getApp().setCurrentParticipant(selection);
                }
            }
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
