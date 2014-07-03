package pondero.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Pondero;
import pondero.ui.PonderoOld;

@SuppressWarnings("serial")
public class AddParticipantAction extends PonderoAction {

    public AddParticipantAction(Pondero app) {
        super(app);
        putValue(NAME, L10n.getString("html.add-participant"));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/page_add.png")));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

}
