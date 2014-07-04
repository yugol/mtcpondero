package pondero.model.participants;

import static pondero.Logger.error;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import pondero.engine.staples.StringUtil;
import pondero.model.Workbook;
import pondero.model.entities.Participant;

public class Participants implements Iterable<Participant> {

    private final Workbook    wb;
    private List<Participant> buffer;

    public Participants(final Workbook workbook) {
        wb = workbook;
        loadAll();
    }

    public String generateNewParticipantId() {
        int maxIdx = 100;
        for (Participant p : this) {
            String id = p.getId();
            try {
                int idx = Integer.parseInt(id.substring(1));
                if (idx > maxIdx) {
                    maxIdx = idx;
                }
            } catch (NumberFormatException nfe) {
            }
        }
        maxIdx++;
        return String.valueOf(maxIdx);
    }

    public List<Participant> getAll() {
        return buffer;
    }

    public Workbook getWorkbook() {
        return wb;
    }

    @Override
    public Iterator<Participant> iterator() {
        return buffer.iterator();
    }

    public void put(Participant participant) {
        for (int i = 0; i < buffer.size(); ++i) {
            Participant foo = buffer.get(i);
            if (foo.getId().equals(participant.getId())) {
                buffer.set(i, participant);
                return;
            }
        }
        buffer.add(participant);
    }

    public void save() throws Exception {
        wb.deleteParticipants();
        for (Participant p : this) {
            wb.add(p);
        }
    }

    public List<Participant> select(String pattern) {
        pattern = StringUtil.normalizeForSearch(pattern);
        final List<Participant> selection = new ArrayList<Participant>();
        for (final Participant participant : this) {
            if (participant.getFootprint().indexOf(pattern) >= 0) {
                selection.add(participant);
            }
        }
        return selection;
    }

    public Participant[] toArray() {
        return buffer.toArray(new Participant[] {});
    }

    @SuppressWarnings("unchecked")
    private void loadAll() {
        if (wb != null) {
            try {
                buffer = (List<Participant>) wb.getAll(Participant.class);
                sort(new SurnameNameIdComparator());
            } catch (final Exception e) {
                error(e);
            }
        } else {
            buffer = new ArrayList<Participant>();
        }
    }

    private void sort(final Comparator<Participant> c) {
        Collections.sort(buffer, c);
    }

}
