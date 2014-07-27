package pondero;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.UIManager;

public final class L10n {

    private static final String         BUNDLE_NAME     = "pondero.res.messages";                       //$NON-NLS-1$
    private static final Locale         LOCALE          = Context.getLocale();
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, LOCALE);
    private static final MessageFormat  FORMATTER       = new MessageFormat("");

    static {
        FORMATTER.setLocale(LOCALE);
        localizeJOptionPaneButtons();
        localizeJFileChoose();
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

    private static void localizeJFileChoose() {
        UIManager.put("FileChooser.cancelButtonText", getString("FileChooser.cancelButtonText"));
        UIManager.put("FileChooser.cancelButtonToolTipText", getString("FileChooser.cancelButtonToolTipText"));
        UIManager.put("FileChooser.detailsViewButtonToolTipText", getString("FileChooser.detailsViewButtonToolTipText"));
        UIManager.put("FileChooser.fileNameLabelText", getString("FileChooser.fileNameLabelText"));
        UIManager.put("FileChooser.filesOfTypeLabelText", getString("FileChooser.filesOfTypeLabelText"));
        UIManager.put("FileChooser.helpButtonText", getString("FileChooser.helpButtonText"));
        UIManager.put("FileChooser.helpButtonToolTipText", getString("FileChooser.helpButtonToolTipText"));
        UIManager.put("FileChooser.homeFolderToolTipText", getString("FileChooser.homeFolderToolTipText"));
        UIManager.put("FileChooser.listViewButtonToolTipText", getString("FileChooser.listViewButtonToolTipText"));
        UIManager.put("FileChooser.lookInLabelText", getString("FileChooser.lookInLabelText"));
        UIManager.put("FileChooser.newFolderToolTipText", getString("FileChooser.newFolderToolTipText"));
        UIManager.put("FileChooser.openButtonText", getString("FileChooser.openButtonText"));
        UIManager.put("FileChooser.openButtonToolTipText", getString("FileChooser.openButtonToolTipText"));
        UIManager.put("FileChooser.saveButtonText", getString("FileChooser.saveButtonText"));
        UIManager.put("FileChooser.saveButtonToolTipText", getString("FileChooser.saveButtonToolTipText"));
        UIManager.put("FileChooser.saveInLabelText", getString("FileChooser.saveInLabelText"));
        UIManager.put("FileChooser.upFolderToolTipText", getString("FileChooser.upFolderToolTipText"));
        UIManager.put("FileChooser.updateButtonText", getString("FileChooser.updateButtonText"));
        UIManager.put("FileChooser.updateButtonToolTipText", getString("FileChooser.updateButtonToolTipText"));
        UIManager.put("FileChooser.acceptAllFileFilterText", getString("FileChooser.acceptAllFileFilterText"));
    }

    private static void localizeJOptionPaneButtons() {
        UIManager.put("OptionPane.yesButtonText", getString("lbl.yes"));
        UIManager.put("OptionPane.noButtonText", getString("lbl.no"));
        UIManager.put("OptionPane.cancelButtonText", getString("lbl.cancel"));
    }

    private L10n() {
    }

}
