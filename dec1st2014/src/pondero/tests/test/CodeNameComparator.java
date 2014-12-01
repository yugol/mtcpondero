package pondero.tests.test;

import java.util.Comparator;

public class CodeNameComparator implements Comparator<Test> {

    @Override
    public int compare(Test o1, Test o2) {
        return o1.getCodeName().compareTo(o2.getCodeName());
    }

}
