package pondero.ui.tests;

import java.util.Comparator;
import pondero.tests.test.Test;
import pondero.tests.update.Artifact;

public class TestComparator implements Comparator<Test> {

    @Override
    public int compare(final Test o1, final Test o2) {
        final Artifact a1 = o1.getDescriptor();
        final Artifact a2 = o2.getDescriptor();
        final int cmp = -1 * Integer.compare(a1.getMajor(), a2.getMajor());
        if (cmp == 0) { return a1.getCodeName().compareTo(a2.getCodeName()); }
        return cmp;
    }

}
