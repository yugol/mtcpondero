package pondero.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import pondero.model.Workbook;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class StartDocumentAction extends AbstractAction {

    private final Pondero app;

    public StartDocumentAction(final Pondero app) {
        this.app = app;
        putValue(NAME, Messages.getString("lbl.open-externally")); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Workbook wb = app.getCurrentWorkbook();
        try {
            wb.view();
        } catch (Exception e1) {
            JOptionPane.showInternalMessageDialog(app.getFrame(), "View error");
        }
    }

}
