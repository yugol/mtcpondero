package pondero.data.evaluation.scoring;

import java.util.List;
import pondero.data.evaluation.profile.ProfileEntry;
import pondero.data.model.basic.TestInstance;
import pondero.tests.test.Test;

public abstract class Evaluation {

    private final TestInstance instance;

    public Evaluation(final TestInstance instance) {
        this.instance = instance;
    }

    public TestInstance getInstance() {
        return instance;
    }

    public abstract List<ProfileEntry> getProfileEntries();

    public Test getTest() {
        return instance.getTest();
    }

    protected double evaluate(final TestInstance instance, final int oneBasedIndex) {
        if (oneBasedIndex <= instance.size()) { return Double.parseDouble(instance.get(oneBasedIndex - 1).getResponse()); }
        return 0;
    }

}
