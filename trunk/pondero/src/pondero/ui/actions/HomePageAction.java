package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.Context;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.util.WebUtil;

@SuppressWarnings("serial")
public class HomePageAction extends PonderableAction {

    public HomePageAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.home-page"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/house.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        try {
            action("opening home page");
            WebUtil.browse(Context.HOME_PAGE_ADDRESS);
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }
    }

}
