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
    private List<Participant> all;
    private boolean           dirty;

    public Participants(final Workbook workbook) {
        wb = workbook;
        loadAll();
    }

    public void add(Participant participant) {
        for (int i = 0; i < all.size(); ++i) {
            Participant foo = all.get(i);
            if (foo.getId().equals(participant.getId())) {
                all.set(i, participant);
                return;
            }
        }
        all.add(participant);
    }

    public String generateNewId() {
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
        return "#" + maxIdx;
    }

    public List<Participant> getAll() {
        return all;
    }

    public Workbook getWorkbook() {
        return wb;
    }

    public boolean isDirty() {
        return dirty;
    }

    @Override
    public Iterator<Participant> iterator() {
        return all.iterator();
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
        return all.toArray(new Participant[] {});
    }

    public void update() throws Exception {
        wb.deleteParticipants();
        for (Participant p : this) {
            wb.add(p);
        }
        wb.save();
    }

    @SuppressWarnings("unchecked")
    private void loadAll() {
        if (wb != null) {
            try {
                all = (List<Participant>) wb.getAll(Participant.class);
                sort(new SurnameNameIdComparator());
            } catch (final Exception e) {
                error(e);
            }
        } else {
            all = new ArrayList<Participant>();
        }
    }

    private void sort(final Comparator<Participant> c) {
        Collections.sort(all, c);
    }

}
