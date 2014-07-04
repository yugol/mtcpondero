package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.Globals;
import pondero.L10n;
import pondero.MessageUtil;
import pondero.WebUtil;
import pondero.ui.Ponderable;
import pondero.ui.PonderoOld;

@SuppressWarnings("serial")
public class HomePageAction extends PonderoAction {

    public HomePageAction(Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.home-page"));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/world_link.png")));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        try {
            WebUtil.browse(Globals.HOME_PAGE_ADDRESS);
        } catch (Exception e) {
            error(e);
            MessageUtil.showExceptionMessage(getFrame(), e);
        }
    }

}
