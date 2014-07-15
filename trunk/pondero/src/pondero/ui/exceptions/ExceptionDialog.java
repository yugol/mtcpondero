package pondero.ui.exceptions;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import pondero.L10n;

@SuppressWarnings("serial")
public class ExceptionDialog extends JDialog {

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        try {
            final ExceptionDialog dialog = new ExceptionDialog();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private final JPanel contentPanel = new JPanel();

    /**
     * Create the dialog.
     */
    public ExceptionDialog() {
        setTitle(L10n.getString("lbl.error"));
        setResizable(false);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 200);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        final GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0 };
        gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE, 0.0 };
        gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE, 0.0 };
        contentPanel.setLayout(gbl_contentPanel);
        {
            final Component verticalStrut = Box.createVerticalStrut(20);
            final GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
            gbc_verticalStrut.weighty = 1.0;
            gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
            gbc_verticalStrut.gridx = 1;
            gbc_verticalStrut.gridy = 0;
            contentPanel.add(verticalStrut, gbc_verticalStrut);
        }
        {
            final Component horizontalStrut = Box.createHorizontalStrut(20);
            final GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
            gbc_horizontalStrut.weightx = 2.0;
            gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
            gbc_horizontalStrut.gridx = 0;
            gbc_horizontalStrut.gridy = 1;
            contentPanel.add(horizontalStrut, gbc_horizontalStrut);
        }
        {
            final JLabel lblIcon = new JLabel();
            lblIcon.setIcon(new ImageIcon(ExceptionDialog.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
            final GridBagConstraints gbc_lblIcon = new GridBagConstraints();
            gbc_lblIcon.anchor = GridBagConstraints.NORTH;
            gbc_lblIcon.insets = new Insets(0, 0, 5, 15);
            gbc_lblIcon.gridx = 1;
            gbc_lblIcon.gridy = 1;
            contentPanel.add(lblIcon, gbc_lblIcon);
        }
        {
            final JLabel lblInfo = new JLabel(L10n.getString("html.exception-info"));
            final GridBagConstraints gbc_lblInfo = new GridBagConstraints();
            gbc_lblInfo.fill = GridBagConstraints.HORIZONTAL;
            gbc_lblInfo.anchor = GridBagConstraints.NORTHWEST;
            gbc_lblInfo.insets = new Insets(0, 0, 5, 5);
            gbc_lblInfo.gridx = 2;
            gbc_lblInfo.gridy = 1;
            contentPanel.add(lblInfo, gbc_lblInfo);
        }
        {
            final Component horizontalStrut = Box.createHorizontalStrut(20);
            final GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
            gbc_horizontalStrut.weightx = 3.0;
            gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
            gbc_horizontalStrut.gridx = 3;
            gbc_horizontalStrut.gridy = 1;
            contentPanel.add(horizontalStrut, gbc_horizontalStrut);
        }
        {
            final JLabel lblMessage = new JLabel(L10n.getString("lbl.n-a"));
            final GridBagConstraints gbc_lblMessage = new GridBagConstraints();
            gbc_lblMessage.anchor = GridBagConstraints.WEST;
            gbc_lblMessage.fill = GridBagConstraints.HORIZONTAL;
            gbc_lblMessage.insets = new Insets(0, 0, 5, 5);
            gbc_lblMessage.gridx = 2;
            gbc_lblMessage.gridy = 2;
            contentPanel.add(lblMessage, gbc_lblMessage);
        }
        {
            final Component verticalStrut = Box.createVerticalStrut(20);
            final GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
            gbc_verticalStrut.weighty = 1.0;
            gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
            gbc_verticalStrut.gridx = 1;
            gbc_verticalStrut.gridy = 3;
            contentPanel.add(verticalStrut, gbc_verticalStrut);
        }
        {
            final JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                final JButton okButton = new JButton(L10n.getString("lbl.close"));
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                final JButton cancelButton = new JButton(L10n.getString("lbl.report"));
                buttonPane.add(cancelButton);
            }
        }
    }

}
