package pondero.data.evaluation.profile;

import java.util.Comparator;
import pondero.data.evaluation.scoring.Evaluation;

public class EvaluationsComparator implements Comparator<Evaluation> {

    @Override
    public int compare(final Evaluation o1, final Evaluation o2) {
        return o1.getInstance().getTestName().compareTo(o2.getInstance().getTestName());
    }

}
