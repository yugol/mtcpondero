package pondero.util;

import java.awt.Component;
import javax.swing.JOptionPane;
import pondero.L10n;

public final class MsgUtil {

    public static void showExceptionMessage(final Component parent, final Throwable t) {
        JOptionPane.showMessageDialog(parent, t.getMessage(), L10n.getString("lbl.pondero"), JOptionPane.ERROR_MESSAGE);
    }

    public static void showValidationMessage(final Component parent, final String message) {
        JOptionPane.showMessageDialog(parent, message, L10n.getString("lbl.pondero"), JOptionPane.WARNING_MESSAGE);
    }

    private MsgUtil() {
    }

}
