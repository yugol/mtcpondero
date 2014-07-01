package pondero;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.UIManager;

public class L10n {

    private static final String         BUNDLE_NAME     = "pondero.ui.messages";                        //$NON-NLS-1$
    private static final Locale         LOCALE          = Globals.getLocale();
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, LOCALE);
    private static final MessageFormat  FORMATTER       = new MessageFormat("");
    static {
        FORMATTER.setLocale(LOCALE);
        localizeJOptionPaneButtons();
    }

    public static String getString(final String key) {
        return getString(key, new Object[0]);
    }

    public static String getString(final String key, final Object... args) {
        try {
            String output = RESOURCE_BUNDLE.getString(key);
            if (args != null && args.length > 0) {
                FORMATTER.applyPattern(output);
                output = FORMATTER.format(args);
            }
            return output;
        } catch (final MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    private static void localizeJOptionPaneButtons() {
        UIManager.put("OptionPane.yesButtonText", getString("lbl.yes"));
        UIManager.put("OptionPane.noButtonText", getString("lbl.no"));
        UIManager.put("OptionPane.cancelButtonText", getString("lbl.cancel"));
    }

    private L10n() {
    }

}
