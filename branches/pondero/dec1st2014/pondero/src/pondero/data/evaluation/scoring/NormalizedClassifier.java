package pondero.data.evaluation.scoring;

import java.util.Arrays;

public class NormalizedClassifier extends Classifier {

    private final Double[] classes;

    public NormalizedClassifier(final Double... classes) {
        this.classes = classes;
    }

    public NormalizedClassifier(final int classCount, final double mean, final double stdDev) {
        classes = new Double[classCount - 1];
        final double halfIncrement = stdDev / classes.length * 2;
        final double increment = halfIncrement * 2;
        final double firstDivision = mean - halfIncrement * (classCount - 2);
        for (int division = 0; division < classes.length; ++division) {
            classes[division] = firstDivision + division * increment;
        }
    }

    public int getScore(final double value) {
        if (classes.length > 0) {
            int score = Arrays.binarySearch(classes, value);
            if (score < 0) {
                score = -score - 1;
            }
            return score;
        }
        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (classes.length > 0) {
            sb.append(String.format("(-inf, %1.2f]", classes[0]));
            for (int i = 1; i < classes.length; ++i) {
                sb.append(String.format(" (%1.2f, %1.2f]", classes[i - 1], classes[i]));
            }
            sb.append(String.format(" (%1.2f, +inf)", classes[classes.length - 1]));
        } else {
            sb.append("(-inf, +inf)");
        }
        return sb.toString();
    }

}
