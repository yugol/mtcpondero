package pondero.ui.participants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import pondero.Globals;
import pondero.L10n;
import pondero.model.Workbook;
import pondero.model.entities.Participant;

@SuppressWarnings("serial")
public class ParticipantSelectionDialog extends JDialog {

    public static void main(final String[] args) throws Exception {
        final ParticipantSelectionDialog dialog = new ParticipantSelectionDialog(Globals.getDefaultWorkbook());
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    private Participant               selection;
    private int                       closeOperation = JOptionPane.CANCEL_OPTION;

    private final ParticipantSelector lstParticipants;
    private final JButton             okButton;
    private final JButton             cancelButton;

    public ParticipantSelectionDialog(final Workbook wb) throws Exception {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ParticipantSelectionDialog.class.getResource("/com/famfamfam/silk/user_go.png")));
        setTitle(L10n.getString("lbl.participants")); //$NON-NLS-1$
        setBounds(100, 100, 400, 450);
        getContentPane().setLayout(new BorderLayout());

        final JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            lstParticipants = new ParticipantSelector(wb);
            lstParticipants.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(final ListSelectionEvent e) {
                    selection = lstParticipants.getSelectedValue();
                    okButton.setEnabled(selection != null);
                }

            });
            contentPanel.add(lstParticipants, BorderLayout.CENTER);
        }

        {
            final JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new EmptyBorder(6, 0, 5, 0));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            {
                okButton = new JButton(L10n.getString("lbl.choose")); //$NON-NLS-1$
                okButton.setEnabled(false);
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        closeOperation = JOptionPane.OK_OPTION;
                        ParticipantSelectionDialog.this.setVisible(false);
                    }

                });
                okButton.setActionCommand("Ok"); //$NON-NLS-1$
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                cancelButton = new JButton(L10n.getString("lbl.cancel")); //$NON-NLS-1$
                cancelButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        closeOperation = JOptionPane.CANCEL_OPTION;
                        ParticipantSelectionDialog.this.setVisible(false);
                    }

                });
                cancelButton.setActionCommand("Cancel"); //$NON-NLS-1$
                buttonPane.add(cancelButton);
            }
            contentPanel.add(buttonPane, BorderLayout.SOUTH);
        }

        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    public int getCloseOperation() {
        return closeOperation;
    }

    public Participant getSelection() {
        return selection;
    }

}
