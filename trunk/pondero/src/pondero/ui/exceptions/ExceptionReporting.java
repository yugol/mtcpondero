package pondero.ui.exceptions;

import static pondero.Logger.error;
import java.awt.Component;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import pondero.L10n;

public final class ExceptionReporting {

    public static void showExceptionMessage(final Window parent, final Throwable t) {
        error(t);
        final ExceptionDialog dialog = new ExceptionDialog(parent);
        dialog.setException(t);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        if (parent != null) {
            dialog.setLocationRelativeTo(parent);
        }
        dialog.setVisible(true);
    }

    public static void showValidationMessage(final Component parent, final String message) {
        JOptionPane.showMessageDialog(parent, message, L10n.getString("lbl.pondero"), JOptionPane.WARNING_MESSAGE);
    }

    private ExceptionReporting() {
    }

}
