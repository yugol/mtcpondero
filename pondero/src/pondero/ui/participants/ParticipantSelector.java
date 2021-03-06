package pondero.ui.participants;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import pondero.Context;
import pondero.L10n;
import pondero.data.Workbook;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.Participants;
import pondero.ui.DialogSelectionListener;
import pondero.util.StringUtil;
import pondero.util.UiUtil;

@SuppressWarnings("serial")
public class ParticipantSelector extends JComponent {

    private final ParticipantCellRenderer       participantRenderer = new ParticipantCellRenderer();

    private Participants                        participants;
    private final JTextField                    textPattern;
    private final JTable                        tblParticipants;
    private final List<DialogSelectionListener> selectionListeners  = new ArrayList<DialogSelectionListener>();
    private Participant                         selectedParticipant;

    public ParticipantSelector(final Workbook wb) throws Exception {

        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 1.0 };
        setLayout(gridBagLayout);

        final JLabel lblParticipant = new JLabel(L10n.getString("lbl.search"));
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

        final JLabel lblColumn = new JLabel(":");
        final GridBagConstraints gbc_lblColumn = new GridBagConstraints();
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
        scrollPane.getViewport().setBackground(UiUtil.getListBackgroundColor());
        final GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.weighty = 1.0;
        gbc_scrollPane.gridwidth = 3;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        add(scrollPane, gbc_scrollPane);

        tblParticipants = new JTable();
        tblParticipants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblParticipants.setRowHeight((int) (18 * Context.getUiFontScaleFactor()));
        tblParticipants.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
                    final int index = tblParticipants.getSelectedRow();
                    setSelectedIndex(index);
                    final ListSelectionEvent listEvet = new ListSelectionEvent(ParticipantSelector.this, index, index, false);
                    for (final DialogSelectionListener listener : selectionListeners) {
                        listener.valueChosen(listEvet);
                    }
                }
            }

        });
        tblParticipants.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(final ListSelectionEvent e) {
                setSelectedIndex(tblParticipants.getSelectedRow());
            }

        });
        scrollPane.setViewportView(tblParticipants);

        setWorkbook(wb);
    }

    public void addListSelectionListener(final DialogSelectionListener listSelectionListener) {
        selectionListeners.add(listSelectionListener);
    }

    public Participant getSelectedValue() {
        return selectedParticipant;
    }

    public void setWorkbook(final Workbook wb) throws Exception {
        participants = wb.getAllParticipants();
        updateParticipants();
    }

    private String getFootprint(final Participant participant) {
        final StringBuilder fp = new StringBuilder();
        fp.append(participant.getId().toLowerCase());
        fp.append(" ");
        fp.append(participant.getSurname().toLowerCase());
        fp.append(" ");
        fp.append(participant.getName().toLowerCase());
        return StringUtil.normalizeForSearch(fp.toString());
    }

    private List<Participant> select(String pattern) {
        pattern = StringUtil.normalizeForSearch(pattern);
        final List<Participant> selection = new ArrayList<Participant>();
        for (int i = 0; i < participants.getRowCount(); ++i) {
            final pondero.data.model.basic.Participant participant = participants.getRow(i);
            if (getFootprint(participant).indexOf(pattern) >= 0) {
                selection.add(participant);
            }
        }
        return selection;
    }

    private void setSelectedIndex(final int index) {
        if (index >= 0) {
            selectedParticipant = ((ParticipantsTableModel) tblParticipants.getModel()).getParticipant(index);
        } else {
            selectedParticipant = null;
        }

        final ListSelectionEvent evt = new ListSelectionEvent(this, index, index, false);
        for (final ListSelectionListener listener : selectionListeners) {
            listener.valueChanged(evt);
        }
    }

    private void updateParticipants() {
        final int idColWidth = (int) (60 * Context.getUiFontScaleFactor());
        final List<Participant> data = select(textPattern.getText());
        final ParticipantsTableModel dataModel = new ParticipantsTableModel(data);
        tblParticipants.setModel(dataModel);
        final TableColumnModel columnModel = tblParticipants.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(participantRenderer);
        columnModel.getColumn(0).setMinWidth(idColWidth);
        columnModel.getColumn(0).setMaxWidth(idColWidth);
        columnModel.getColumn(1).setCellRenderer(participantRenderer);
        columnModel.getColumn(1).setMinWidth(idColWidth);
        columnModel.getColumn(2).setCellRenderer(participantRenderer);
        columnModel.getColumn(2).setMinWidth(idColWidth);
        setSelectedIndex(-1);
    }

}
