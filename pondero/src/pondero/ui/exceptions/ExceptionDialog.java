package pondero.ui.exceptions;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import pondero.Context;
import pondero.L10n;
import pondero.util.OsUtil;
import pondero.util.WebUtil;

@SuppressWarnings("serial")
public class ExceptionDialog extends JDialog {

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        try {
            final ExceptionDialog dialog = new ExceptionDialog(null);
            dialog.setException(new NullPointerException());
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private Throwable    t;

    private final JPanel contentPanel = new JPanel();
    private JLabel       lblMessage;

    /**
     * Create the dialog.
     */
    public ExceptionDialog(final Window owner) {
        super(owner);
        setTitle(L10n.getString("lbl.error"));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, (int) (460 * Context.getUiFontScaleFactor()), (int) (210 * Context.getUiFontScaleFactor()));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        final GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] { 0, 0 };
        gbl_contentPanel.rowHeights = new int[] { 0, 0 };
        gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0 };
        gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0 };
        contentPanel.setLayout(gbl_contentPanel);
        {
            final JLabel lblIcon = new JLabel();
            lblIcon.setIcon(new ImageIcon(ExceptionDialog.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
            final GridBagConstraints gbc_lblIcon = new GridBagConstraints();
            gbc_lblIcon.anchor = GridBagConstraints.NORTH;
            gbc_lblIcon.insets = new Insets(0, 0, 5, 15);
            gbc_lblIcon.gridx = 0;
            gbc_lblIcon.gridy = 0;
            contentPanel.add(lblIcon, gbc_lblIcon);
        }
        {
            lblMessage = new JLabel(L10n.getString("lbl.n-a"));
            final GridBagConstraints gbc_lblMessage = new GridBagConstraints();
            gbc_lblMessage.anchor = GridBagConstraints.WEST;
            gbc_lblMessage.fill = GridBagConstraints.HORIZONTAL;
            gbc_lblMessage.insets = new Insets(0, 0, 5, 5);
            gbc_lblMessage.gridx = 1;
            gbc_lblMessage.gridy = 0;
            contentPanel.add(lblMessage, gbc_lblMessage);
        }
        {
            final JLabel lblInfo = new JLabel(L10n.getString("html.exception-info", L10n.getString("lbl.report")));
            final GridBagConstraints gbc_lblInfo = new GridBagConstraints();
            gbc_lblInfo.fill = GridBagConstraints.HORIZONTAL;
            gbc_lblInfo.anchor = GridBagConstraints.NORTHWEST;
            gbc_lblInfo.insets = new Insets(0, 0, 5, 5);
            gbc_lblInfo.gridx = 1;
            gbc_lblInfo.gridy = 1;
            contentPanel.add(lblInfo, gbc_lblInfo);
        }
        {
            final JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new EmptyBorder(0, 10, 10, 10));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                final JButton okButton = new JButton(L10n.getString("lbl.close"));
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        ExceptionDialog.this.dispose();
                    }

                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                final JButton cancelButton = new JButton(L10n.getString("lbl.report"));
                cancelButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        WebUtil.mail(Context.CONTACT_MAIL_ADDRESS, "[PONDERO][EXCEPTION]: ", buildMessageBody());
                        dispose();
                    }

                });
                buttonPane.add(cancelButton);
            }
        }
    }

    public void setException(final Throwable e) {
        t = e;
        lblMessage.setText("<html>" + t.getClass().getSimpleName() + ":<br/>" + t.getMessage() + "</html>");
    }

    protected String buildMessageBody() {
        final StringBuilder msg = new StringBuilder();
        msg.append(OsUtil.getContextDescription());
        return msg.toString();
    }

}
