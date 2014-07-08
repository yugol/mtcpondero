package pondero.ui.participants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
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
import pondero.Globals;
import pondero.L10n;
import pondero.model.Workbook;
import pondero.model.foundation.basic.Participant;

@SuppressWarnings("serial")
public class ParticipantSelectionDialog extends JDialog implements ParticipantSelectionListener, ActionListener {

    public static final String DIALOG_NAME = "participantSelectionDialor";

    public static void main(final String[] args) throws Exception {
        final ParticipantSelectionDialog dialog = new ParticipantSelectionDialog(null, Globals.getDefaultWorkbook());
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    private Participant               selection;
    private int                       closeOperation = JOptionPane.CANCEL_OPTION;

    private final ParticipantSelector lstParticipants;
    private final JButton             selectButton;
    private final JButton             cancelButton;

    public ParticipantSelectionDialog(final Frame owner, final Workbook wb) throws Exception {
        super(owner);
        setName(DIALOG_NAME);
        setType(Type.UTILITY);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ParticipantSelectionDialog.class.getResource("/com/famfamfam/silk/user_go.png")));
        setTitle(L10n.getString("lbl.select-participant")); //$NON-NLS-1$
        setBounds(100, 100, (int) (400 * Globals.getUiFontScaleFactor()), (int) (450 * Globals.getUiFontScaleFactor()));
        getContentPane().setLayout(new BorderLayout());

        final JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            lstParticipants = new ParticipantSelector(wb);
            lstParticipants.addListSelectionListener(this);
            contentPanel.add(lstParticipants, BorderLayout.CENTER);
        }

        {
            final JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new EmptyBorder(6, 0, 5, 0));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            {
                selectButton = new JButton(L10n.getString("lbl.choose")); //$NON-NLS-1$
                selectButton.setEnabled(false);
                selectButton.addActionListener(this);
                selectButton.setActionCommand("Ok"); //$NON-NLS-1$
                buttonPane.add(selectButton);
                getRootPane().setDefaultButton(selectButton);
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
                buttonPane.add(cancelButton);
            }
            contentPanel.add(buttonPane, BorderLayout.SOUTH);
        }

        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        closeOperation = JOptionPane.OK_OPTION;
        ParticipantSelectionDialog.this.setVisible(false);
    }

    public int getCloseOperation() {
        return closeOperation;
    }

    public Participant getSelection() {
        return selection;
    }

    @Override
    public void valueChanged(final ListSelectionEvent e) {
        selection = lstParticipants.getSelectedValue();
        selectButton.setEnabled(selection != null);
    }

    @Override
    public void valueChosen(final ListSelectionEvent evt) {
        selection = lstParticipants.getSelectedValue();
        if (selection != null) {
            actionPerformed(null);
        } else {
            selectButton.setEnabled(false);
        }
    }

}
