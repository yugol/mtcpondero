package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.data.model.basic.Participant;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.participants.ParticipantSelectionDialog;

@SuppressWarnings("serial")
public class SelectParticipantAction extends PonderableAction {

    public SelectParticipantAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("html.choose-participant"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/find.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            action("opening participant selection dialog");
            if (evt != null) { throw new UnsupportedOperationException(); }
            final ParticipantSelectionDialog dlg = new ParticipantSelectionDialog(getMainFrame(), getCurrentWorkbook());
            dlg.setLocationRelativeTo(getMainFrame());
            dlg.setModal(true);
            dlg.setVisible(true);
            if (dlg.getCloseOperation() == JOptionPane.YES_OPTION) {
                final Participant selection = dlg.getSelection();
                if (selection != null) {
                    action("selecting participant ", selection.getId());
                    getApp().setCurrentParticipant(selection);
                }
            }
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }
    }

}
