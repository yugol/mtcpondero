package pondero.data.evaluation.profile;

public class ProfileEntry {

    private String name;
    private String rawScore;
    private int    standardScore;

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
