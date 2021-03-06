package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;

@SuppressWarnings("serial")
public class AddParticipantAction extends ModifyParticipantAction {

    public AddParticipantAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("html.add-participant"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/page_add.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        action("adding participant");
        try {
            final String participantId = getCurrentWorkbook().getNextPariciantId();
            showParticipantDialog(participantId);
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }

    }

}
