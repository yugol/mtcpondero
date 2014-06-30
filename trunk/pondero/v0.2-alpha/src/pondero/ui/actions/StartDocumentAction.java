package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class StartDocumentAction extends PonderoAction {

    public StartDocumentAction(final Pondero app) {
        super(app);
        putValue(NAME, Messages.getString("lbl.open-externally"));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            getApp().getCurrentWorkbook().view();
        } catch (Exception e) {
            error(e);
            JOptionPane.showInternalMessageDialog(
                    getApp().getFrame(),
                    e.getMessage(),
                    Messages.getString("lbl.open-externally"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
