package pondero.ui.participants;

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
import pondero.data.Workbook;
import pondero.data.model.basic.Participant;
import pondero.ui.DialogSelectionListener;

@SuppressWarnings("serial")
public class ParticipantSelectionDialog extends JDialog implements DialogSelectionListener, ActionListener {

    public static void main(final String[] args) throws Exception {
        final ParticipantSelectionDialog dialog = new ParticipantSelectionDialog(null, Context.getDefaultWorkbook());
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public static final String DIALOG_NAME = "participantSelectionDialor";

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
        setTitle(L10n.getString("lbl.select-participant"));
        setBounds(100, 100, (int) (400 * Context.getUiFontScaleFactor()), (int) (450 * Context.getUiFontScaleFactor()));
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
        setVisible(false);
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
