package pondero.data.evaluation.scoring;

import java.util.List;
import pondero.data.evaluation.ProfileEntry;
import pondero.data.model.basic.TestInstance;

public abstract class Evaluation {

    private final TestInstance instance;

    public Evaluation(final TestInstance instance) {
        this.instance = instance;
    }

    public TestInstance getInstance() {
        return instance;
    }

    public abstract List<ProfileEntry> getProfileEntries();

}
