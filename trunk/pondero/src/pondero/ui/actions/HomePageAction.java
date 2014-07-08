package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.Globals;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.util.MsgUtil;
import pondero.util.WebUtil;

@SuppressWarnings("serial")
public class HomePageAction extends PonderoAction {

    public HomePageAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.home-page"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/world_link.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        try {
            WebUtil.browse(Globals.HOME_PAGE_ADDRESS);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
