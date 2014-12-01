package pondero.ui.participants;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import pondero.L10n;
import pondero.data.model.basic.Participant;

@SuppressWarnings("serial")
public class ParticipantsTableModel extends AbstractTableModel {

    private final List<Participant> data;

    public ParticipantsTableModel(final List<Participant> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(final int column) {
        switch (column) {
            case 0:
                return L10n.getString("lbl.participant.id");
            case 1:
                return L10n.getString("lbl.participant.surname");
            case 2:
                return L10n.getString("lbl.participant.name");
        }
        return null;
    }

    public Participant getParticipant(final int index) {
        if (index < 0) { return null; }
        return data.get(index);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getId();
            case 1:
                return data.get(rowIndex).getSurname();
            case 2:
                return data.get(rowIndex).getName();
        }
        return null;
    }

}
