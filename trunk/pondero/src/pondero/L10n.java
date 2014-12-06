package pondero;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.UIManager;

public class L10n {

    public static String formatNumber(final Number number) {
        return String.valueOf(number);
    }

    public static String getString(final String key) {
        return getString(key, DUMMY_ARGS);
    }

    public static String getString(final String key, final Object... args) {
        if (RESOURCE_BUNDLE == null) {
            RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
        }
        return getString(RESOURCE_BUNDLE, key, args);
    }

    public static void init(final Locale locale) {
        RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        FORMATTER.setLocale(locale);
        localizeJOptionPaneButtons();
        localizeJFileChoose();
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

    protected static String getBundleName(final Class<?> clazz) {
        return clazz.getPackage().getName() + ".messages";
    }

    protected static String getString(final ResourceBundle bundle, final String key, final Object... args) {
        try {
            String output = bundle.getString(key);
            if (args != null && args.length > 0) {
                FORMATTER.applyPattern(output);
                output = FORMATTER.format(args);
            }
            return output;
        } catch (final MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    private static final String        BUNDLE_NAME = "pondero.res.messages";
    private static final MessageFormat FORMATTER   = new MessageFormat("");

    private static ResourceBundle      RESOURCE_BUNDLE;

    protected static final Object[]    DUMMY_ARGS  = new Object[0];

}
