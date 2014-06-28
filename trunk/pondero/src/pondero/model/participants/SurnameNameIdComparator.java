package pondero.model.participants;

import java.util.Comparator;
import pondero.model.entities.Participant;

public class SurnameNameIdComparator implements Comparator<Participant> {

    @Override
    public int compare(final Participant o1, final Participant o2) {
        if (o1 == null) {
            if (o2 == null) { return 0; }
            return 1;
        }
        if (o2 == null) { return -1; }
        int cmp = o1.getSurname().compareTo(o2.getSurname());
        if (cmp == 0) {
            cmp = o1.getName().compareTo(o2.getName());
            if (cmp == 0) {
                cmp = o1.getId().compareTo(o2.getId());
            }
        }
        return cmp;
    }

}
