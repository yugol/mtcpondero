package pondero.ui.preferences;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import pondero.Context;
import pondero.L10n;
import pondero.Logger;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.util.SwingUtil;

@SuppressWarnings("serial")
public class PreferencesDialog extends JDialog {

    /**
     * Launch the application.
     *
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        Context.init(null);
        try {
            final PreferencesDialog dialog = new PreferencesDialog(null);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static final String DIALOG_NAME  = "preferencesDialog";

    private final JPanel       contentPanel = new JPanel();
    private JComboBox<Locale>  cbLanguage;
    private JComboBox<String>  cbTheme;
    private JComboBox<Double>  cbScale;

    /**
     * Create the dialog.
     */
    public PreferencesDialog(final Frame owner) {
        super(owner);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(final WindowEvent e) {
                Logger.action("closing preferences dialog");
            }

        });
        setName(DIALOG_NAME);
        setType(Type.UTILITY);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, (int) (320 * Context.getUiFontScaleFactor()), (int) (240 * Context.getUiFontScaleFactor()));
        setTitle(L10n.getString("lbl.preferences"));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
            contentPanel.add(tabbedPane, BorderLayout.CENTER);
            {
                final JPanel pnlGeneral = new JPanel();
                pnlGeneral.setBorder(new EmptyBorder(15, 10, 10, 10));
                tabbedPane.addTab(L10n.getString("lbl.preferences-general"), null, pnlGeneral, null); //$NON-NLS-1$
                final GridBagLayout gbl_pnlGeneral = new GridBagLayout();
                gbl_pnlGeneral.columnWidths = new int[] { 0, 0, 0, 0 };
                gbl_pnlGeneral.rowHeights = new int[] { 0, 0, 0, 0 };
                gbl_pnlGeneral.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
                gbl_pnlGeneral.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
                pnlGeneral.setLayout(gbl_pnlGeneral);
                {
                    final JLabel lblLanguage = new JLabel(L10n.getString("lbl.language")); //$NON-NLS-1$
                    final GridBagConstraints gbc_lblLanguage = new GridBagConstraints();
                    gbc_lblLanguage.anchor = GridBagConstraints.WEST;
                    gbc_lblLanguage.insets = new Insets(0, 0, 5, 5);
                    gbc_lblLanguage.gridx = 0;
                    gbc_lblLanguage.gridy = 0;
                    pnlGeneral.add(lblLanguage, gbc_lblLanguage);
                }
                {
                    final JLabel label = new JLabel(":"); //$NON-NLS-1$
                    final GridBagConstraints gbc_label = new GridBagConstraints();
                    gbc_label.anchor = GridBagConstraints.EAST;
                    gbc_label.insets = new Insets(0, 0, 5, 5);
                    gbc_label.gridx = 1;
                    gbc_label.gridy = 0;
                    pnlGeneral.add(label, gbc_label);
                }
                {
                    cbLanguage = new JComboBox<Locale>();
                    final GridBagConstraints gbc_cbLanguage = new GridBagConstraints();
                    gbc_cbLanguage.insets = new Insets(0, 0, 5, 5);
                    gbc_cbLanguage.fill = GridBagConstraints.HORIZONTAL;
                    gbc_cbLanguage.gridx = 2;
                    gbc_cbLanguage.gridy = 0;
                    pnlGeneral.add(cbLanguage, gbc_cbLanguage);
                }
                {
                    final JLabel lblTheme = new JLabel(L10n.getString("lbl.theme")); //$NON-NLS-1$
                    final GridBagConstraints gbc_lblTheme = new GridBagConstraints();
                    gbc_lblTheme.anchor = GridBagConstraints.WEST;
                    gbc_lblTheme.insets = new Insets(0, 0, 5, 5);
                    gbc_lblTheme.gridx = 0;
                    gbc_lblTheme.gridy = 1;
                    pnlGeneral.add(lblTheme, gbc_lblTheme);
                }
                {
                    final JLabel label = new JLabel(":"); //$NON-NLS-1$
                    final GridBagConstraints gbc_label = new GridBagConstraints();
                    gbc_label.anchor = GridBagConstraints.EAST;
                    gbc_label.insets = new Insets(0, 0, 5, 5);
                    gbc_label.gridx = 1;
                    gbc_label.gridy = 1;
                    pnlGeneral.add(label, gbc_label);
                }
                {
                    cbTheme = new JComboBox<String>();
                    final GridBagConstraints gbc_cbTheme = new GridBagConstraints();
                    gbc_cbTheme.insets = new Insets(0, 0, 5, 5);
                    gbc_cbTheme.fill = GridBagConstraints.HORIZONTAL;
                    gbc_cbTheme.gridx = 2;
                    gbc_cbTheme.gridy = 1;
                    pnlGeneral.add(cbTheme, gbc_cbTheme);
                }
                {
                    final JLabel lblScale = new JLabel(L10n.getString("lbl.scale")); //$NON-NLS-1$
                    final GridBagConstraints gbc_lblScale = new GridBagConstraints();
                    gbc_lblScale.anchor = GridBagConstraints.WEST;
                    gbc_lblScale.insets = new Insets(0, 0, 5, 5);
                    gbc_lblScale.gridx = 0;
                    gbc_lblScale.gridy = 2;
                    pnlGeneral.add(lblScale, gbc_lblScale);
                }
                {
                    final JLabel lblNewLabel = new JLabel(":"); //$NON-NLS-1$
                    final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
                    gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
                    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
                    gbc_lblNewLabel.gridx = 1;
                    gbc_lblNewLabel.gridy = 2;
                    pnlGeneral.add(lblNewLabel, gbc_lblNewLabel);
                }
                {
                    cbScale = new JComboBox<Double>();
                    final GridBagConstraints gbc_cbScale = new GridBagConstraints();
                    gbc_cbScale.insets = new Insets(0, 0, 5, 5);
                    gbc_cbScale.fill = GridBagConstraints.HORIZONTAL;
                    gbc_cbScale.gridx = 2;
                    gbc_cbScale.gridy = 2;
                    pnlGeneral.add(cbScale, gbc_cbScale);
                }
            }
        }
        {
            final JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new EmptyBorder(0, 10, 8, 10));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                final JButton okButton = new JButton(L10n.getString("lbl.save"));
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        if (savePreferences()) {
                            Logger.action("saving preferences");
                            JOptionPane.showMessageDialog(PreferencesDialog.this,
                                    L10n.getString("msg.preferences-restart"),
                                    L10n.getString("lbl.pondero"),
                                    JOptionPane.INFORMATION_MESSAGE);
                            PreferencesDialog.this.dispose();
                        }
                    }

                });
                buttonPane.add(okButton);
            }
            {
                final JButton cancelButton = new JButton(L10n.getString("lbl.cancel"));
                cancelButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent arg0) {
                        PreferencesDialog.this.dispose();
                    }

                });
                buttonPane.add(cancelButton);
            }
        }
        initCombos();
    }

    private void initCombos() {
        final DefaultComboBoxModel<Locale> localeModel = new DefaultComboBoxModel<Locale>();
        localeModel.addElement(new Locale("en"));
        localeModel.addElement(new Locale("ro"));
        cbLanguage.setModel(localeModel);
        cbLanguage.setSelectedItem(Context.getLocale());

        final DefaultComboBoxModel<String> themeModel = new DefaultComboBoxModel<String>();
        themeModel.addElement("");
        for (final String theme : SwingUtil.getAvailableLafs()) {
            themeModel.addElement(theme);
        }
        cbTheme.setModel(themeModel);
        cbTheme.setSelectedItem(Context.getThemeString());

        final DefaultComboBoxModel<Double> scaleModel = new DefaultComboBoxModel<Double>();
        scaleModel.addElement(new Double(1.00));
        scaleModel.addElement(new Double(1.25));
        scaleModel.addElement(new Double(1.50));
        scaleModel.addElement(new Double(1.75));
        scaleModel.addElement(new Double(2.00));
        cbScale.setModel(scaleModel);
        cbScale.setSelectedItem(Context.getUiFontScaleFactor());
    }

    private boolean savePreferences() {
        try {
            Context.setLocale((Locale) cbLanguage.getSelectedItem());
            Context.setUiThemeString((String) cbTheme.getSelectedItem());
            Context.setUiFontScaleFactor((Double) cbScale.getSelectedItem());
            Context.savePreferences();
            return true;
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(this, e);
            return false;
        }
    }

}
