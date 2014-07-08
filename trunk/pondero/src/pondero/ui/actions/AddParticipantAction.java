package pondero.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class AddParticipantAction extends ModifyParticipantAction {

    public AddParticipantAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("html.add-participant"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/page_add.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        final String participantId = getApp().getCurrentWorkbook().getNextPariciantId();
        showParticipantDialog(participantId);
    }

}
