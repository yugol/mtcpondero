package pondero.ui.participants;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import pondero.model.Workbook;
import pondero.model.entities.Participant;
import pondero.model.participants.Participants;
import pondero.ui.Messages;

@SuppressWarnings("serial")
public class ParticipantSelector extends JComponent {

    private Participants                      participants;
    private final JTextField                  textPattern;
    private final JTable                      tblParticipants;
    private final List<ListSelectionListener> selectionListeners = new ArrayList<ListSelectionListener>();
    private Participant                       selectedParticipant;

    public ParticipantSelector(final Workbook wb) throws Exception {

        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 1.0 };
        setLayout(gridBagLayout);

        final JLabel lblParticipant = new JLabel(Messages.getString("lbl.participant"));
        final GridBagConstraints gbc_lblParticipant = new GridBagConstraints();
        gbc_lblParticipant.insets = new Insets(0, 0, 5, 5);
        gbc_lblParticipant.anchor = GridBagConstraints.EAST;
        gbc_lblParticipant.gridx = 0;
        gbc_lblParticipant.gridy = 0;
        add(lblParticipant, gbc_lblParticipant);

        textPattern = new JTextField();
        textPattern.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(final DocumentEvent e) {
                updateParticipants();
            }

            @Override
            public void insertUpdate(final DocumentEvent e) {
                updateParticipants();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                updateParticipants();
            }

        });

        JLabel lblColumn = new JLabel(":");
        GridBagConstraints gbc_lblColumn = new GridBagConstraints();
        gbc_lblColumn.insets = new Insets(0, 0, 5, 5);
        gbc_lblColumn.gridx = 1;
        gbc_lblColumn.gridy = 0;
        add(lblColumn, gbc_lblColumn);

        final GridBagConstraints gbc_textPattern = new GridBagConstraints();
        gbc_textPattern.weightx = 1.0;
        gbc_textPattern.insets = new Insets(0, 0, 5, 0);
        gbc_textPattern.fill = GridBagConstraints.HORIZONTAL;
        gbc_textPattern.gridx = 2;
        gbc_textPattern.gridy = 0;
        add(textPattern, gbc_textPattern);
        textPattern.setColumns(10);

        final JScrollPane scrollPane = new JScrollPane();
        final GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.weighty = 1.0;
        gbc_scrollPane.gridwidth = 3;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        add(scrollPane, gbc_scrollPane);

        tblParticipants = new JTable();
        tblParticipants.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(final ListSelectionEvent e) {
                final int selectedRowIdx = tblParticipants.getSelectedRow();
                final Participant selectedParticipant = ((ParticipantsTableModel) tblParticipants.getModel()).getParticipant(selectedRowIdx);
                setSelectedValue(selectedParticipant);
            }

        });
        scrollPane.setViewportView(tblParticipants);

        setWorkbook(wb);
    }

    public void addListSelectionListener(final ListSelectionListener listSelectionListener) {
        selectionListeners.add(listSelectionListener);
    }

    public Participant getSelectedValue() {
        return selectedParticipant;
    }

    private void setSelectedValue(final Participant foo) {
        selectedParticipant = foo;
        final ListSelectionEvent evt = new ListSelectionEvent(this, 0, 0, false);
        for (final ListSelectionListener listener : selectionListeners) {
            listener.valueChanged(evt);
        }
    }

    public void setWorkbook(final Workbook wb) {
        participants = new Participants(wb);
        updateParticipants();
    }

    private void updateParticipants() {
        final List<Participant> data = participants.select(textPattern.getText());
        final ParticipantsTableModel dataModel = new ParticipantsTableModel(data);
        tblParticipants.setModel(dataModel);
        final TableColumnModel columnModel = tblParticipants.getColumnModel();
        final int idColSize = 80;
        columnModel.getColumn(0).setMinWidth(idColSize);
        columnModel.getColumn(0).setMaxWidth(idColSize);
        setSelectedValue(null);
    }

}
