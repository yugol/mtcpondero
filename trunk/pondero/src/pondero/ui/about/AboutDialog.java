package pondero.ui.about;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import pondero.Context;
import pondero.L10n;
import pondero.util.OsUtil;
import pondero.util.WebUtil;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

    /**
     * Launch the application.
     *
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        Context.initForTesting();
        try {
            final AboutDialog dialog = new AboutDialog(null);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMailLink() {
        return "<html><a href='mailto:" + Context.CONTACT_MAIL_ADDRESS + "'>" + Context.CONTACT_MAIL_ADDRESS + "</a></html>";
    }

    private final JPanel contentPanel = new JPanel();

    /**
     * Create the dialog.
     */
    public AboutDialog(final Frame owner) {
        super(owner);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(AboutDialog.class.getResource("/pondero/res/pondero-48x48.png")));
        setTitle(L10n.getString("lbl.about")); //$NON-NLS-1$
        setResizable(false);
        setBounds(100, 100, (int) (450 * Context.getUiFontScaleFactor()), (int) (400 * Context.getUiFontScaleFactor()));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            final JPanel pnlTop = new JPanel();
            pnlTop.setBorder(new EmptyBorder(0, 0, 20, 0));
            contentPanel.add(pnlTop, BorderLayout.NORTH);
            final GridBagLayout gbl_pnlTop = new GridBagLayout();
            gbl_pnlTop.columnWidths = new int[] { 0, 0, 0, 0 };
            gbl_pnlTop.rowHeights = new int[] { 0, 0 };
            gbl_pnlTop.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
            gbl_pnlTop.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
            pnlTop.setLayout(gbl_pnlTop);
            {
                final JLabel lblIcon = new JLabel();
                lblIcon.setIcon(new ImageIcon(AboutDialog.class.getResource("/pondero/res/pondero-48x48.png")));
                final GridBagConstraints gbc_lblIcon = new GridBagConstraints();
                gbc_lblIcon.anchor = GridBagConstraints.NORTH;
                gbc_lblIcon.gridheight = 3;
                gbc_lblIcon.insets = new Insets(0, 0, 0, 10);
                gbc_lblIcon.gridx = 0;
                gbc_lblIcon.gridy = 0;
                pnlTop.add(lblIcon, gbc_lblIcon);
            }
            {
                final JLabel lblName = new JLabel(L10n.getString("lbl.pondero"));
                lblName.setFont(lblName.getFont().deriveFont(Font.BOLD, 2 * lblName.getFont().getSize()));
                final GridBagConstraints gbc_lblName = new GridBagConstraints();
                gbc_lblName.gridwidth = 2;
                gbc_lblName.anchor = GridBagConstraints.WEST;
                gbc_lblName.insets = new Insets(0, 0, 5, 5);
                gbc_lblName.gridx = 1;
                gbc_lblName.gridy = 0;
                pnlTop.add(lblName, gbc_lblName);
            }
            {
                final JLabel lblVersion = new JLabel(L10n.getString("lbl.version", Context.getPonderoArtifact().getVersion()));
                final GridBagConstraints gbc_lblVersion = new GridBagConstraints();
                gbc_lblVersion.gridwidth = 2;
                gbc_lblVersion.anchor = GridBagConstraints.WEST;
                gbc_lblVersion.insets = new Insets(0, 0, 5, 5);
                gbc_lblVersion.gridx = 1;
                gbc_lblVersion.gridy = 1;
                pnlTop.add(lblVersion, gbc_lblVersion);
            }
            {
                final JLabel lblMailto = new JLabel(L10n.getString("lbl.mail-to") + ":");
                lblMailto.setFont(lblMailto.getFont().deriveFont(1.1f * lblMailto.getFont().getSize()));
                final GridBagConstraints gbc_lblMailto = new GridBagConstraints();
                gbc_lblMailto.anchor = GridBagConstraints.WEST;
                gbc_lblMailto.insets = new Insets(0, 0, 0, 5);
                gbc_lblMailto.gridx = 1;
                gbc_lblMailto.gridy = 2;
                pnlTop.add(lblMailto, gbc_lblMailto);
            }
            {
                final JLabel lblMail = new JLabel(getMailLink());
                lblMail.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(final MouseEvent e) {
                        WebUtil.sendAboutMail();
                    }

                });
                lblMail.setFont(lblMail.getFont().deriveFont(1.1f * lblMail.getFont().getSize()));
                lblMail.setCursor(new Cursor(Cursor.HAND_CURSOR));
                final GridBagConstraints gbc_lblMail = new GridBagConstraints();
                gbc_lblMail.gridx = 2;
                gbc_lblMail.gridy = 2;
                pnlTop.add(lblMail, gbc_lblMail);
            }
        }
        {
            final JPanel pnlCenter = new JPanel();
            contentPanel.add(pnlCenter, BorderLayout.CENTER);
            pnlCenter.setLayout(new BorderLayout(0, 0));
            {
                final JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.setBorder(new TitledBorder(null, L10n.getString("lbl.credits-and-licenses") + ":", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                if (OsUtil.isMacOSX()) {
                    tabbedPane.setTabPlacement(SwingConstants.TOP);
                } else {
                    tabbedPane.setTabPlacement(SwingConstants.LEFT);
                }
                pnlCenter.add(tabbedPane);
                {
                    final JPanel pnlPondero = new JPanel();
                    tabbedPane.addTab(L10n.getString("lbl.pondero"), null, pnlPondero, null);
                }
            }
        }
        {
            final JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new EmptyBorder(5, 0, 10, 15));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                final JButton okButton = new JButton(L10n.getString("lbl.close"));
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        AboutDialog.this.dispose();
                    }

                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
    }

}
