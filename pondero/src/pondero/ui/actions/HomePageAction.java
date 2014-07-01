package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.WebUtil;
import pondero.Globals;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class HomePageAction extends PonderoAction {

    public HomePageAction(Pondero app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.home-page"));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        try {
            WebUtil.browse(Globals.HOME_PAGE_ADDRESS);
        } catch (Exception e) {
            error(e);
            JOptionPane.showInternalMessageDialog(
                    getApp().getFrame(),
                    e.getMessage(),
                    L10n.getString("lbl.home-page"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
