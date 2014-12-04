package pondero.data.evaluation.profile;

import pondero.data.evaluation.scoring.Evaluation;

public class ProfileEntry {

    private final Evaluation evaluation;
    private String           name;
    private String           rawScore;
    private int              standardScore;

    public ProfileEntry(final Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public String getName() {
        return name;
    }

    public String getRawScore() {
        return rawScore;
    }

    public int getStandardScore() {
        return standardScore;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setRawScore(final String rawScore) {
        this.rawScore = rawScore;
    }

    public void setStandardScore(final int standardScore) {
        this.standardScore = standardScore;
    }

}
