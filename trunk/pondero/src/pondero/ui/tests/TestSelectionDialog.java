package pondero.ui.tests;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import pondero.Context;
import pondero.L10n;
import pondero.tests.test.Test;
import pondero.ui.DialogSelectionListener;

@SuppressWarnings("serial")
public class TestSelectionDialog extends JDialog implements DialogSelectionListener, ActionListener {

    public static void main(final String[] args) {
        final TestSelectionDialog dialog = new TestSelectionDialog(null);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public static final String DIALOG_NAME    = "testSelectionDialor";

    private Test               selection;
    private int                closeOperation = JOptionPane.CANCEL_OPTION;

    private final TestSelector lstTests;
    private JButton            selectButton;
    private JButton            cancelButton;

    public TestSelectionDialog(final Frame owner) {
        super(owner);
        setName(DIALOG_NAME);
        setType(Type.UTILITY);
        setResizable(false);
        setTitle(L10n.getString("lbl.select-test")); //$NON-NLS-1$
        setBounds(100, 100, (int) (400 * Context.getUiFontScaleFactor()), (int) (450 * Context.getUiFontScaleFactor()));
        getContentPane().setLayout(new BorderLayout());

        final JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            lstTests = new TestSelector();
            lstTests.addListSelectionListener(this);
            contentPanel.add(lstTests, BorderLayout.CENTER);
        }

        {
            final JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new EmptyBorder(6, 0, 5, 0));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            {
                selectButton = new JButton(L10n.getString("lbl.choose"));
                selectButton.setEnabled(false);
                selectButton.addActionListener(this);
                buttonPane.add(selectButton);
                getRootPane().setDefaultButton(selectButton);
            }
            {
                cancelButton = new JButton(L10n.getString("lbl.cancel"));
                cancelButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        closeOperation = JOptionPane.CANCEL_OPTION;
                        TestSelectionDialog.this.setVisible(false);
                    }

                });
                buttonPane.add(cancelButton);
            }
            contentPanel.add(buttonPane, BorderLayout.SOUTH);
        }
        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        closeOperation = JOptionPane.OK_OPTION;
        setVisible(false);
    }

    public int getCloseOperation() {
        return closeOperation;
    }

    public Test getSelection() {
        return selection;
    }

    @Override
    public void valueChanged(final ListSelectionEvent e) {
        selection = lstTests.getSelectedValue();
        selectButton.setEnabled(selection != null);
    }

    @Override
    public void valueChosen(final ListSelectionEvent evt) {
        selection = lstTests.getSelectedValue();
        if (selection != null) {
            actionPerformed(null);
        } else {
            selectButton.setEnabled(false);
        }
    }

}
