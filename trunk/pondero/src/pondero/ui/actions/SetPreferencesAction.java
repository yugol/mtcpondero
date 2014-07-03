package pondero.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.PonderoOld;

@SuppressWarnings("serial")
public class SetPreferencesAction extends PonderoAction {

    public SetPreferencesAction(Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.preferences"));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/wrench.png")));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

}
