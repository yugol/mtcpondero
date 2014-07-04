package pondero;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MessageUtil {

    public static void showExceptionMessage(Component parent, Throwable t) {
        JOptionPane.showInternalMessageDialog(parent, t.getMessage(), L10n.getString("lbl.pondero"), JOptionPane.ERROR_MESSAGE);
    }

    public static void showValidationMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, L10n.getString("lbl.pondero"), JOptionPane.WARNING_MESSAGE);
    }

}
