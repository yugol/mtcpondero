package pondero.data.evaluation.scoring;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NormalizedClassifierTest {

    @Test
    public void testNormalizedClassifier() {
        System.out.println(new NormalizedClassifier());
        System.out.println(new NormalizedClassifier(0));
        System.out.println(new NormalizedClassifier(-1, 1));

        System.out.println(new NormalizedClassifier(1, 2.7860, 1.12497));
        System.out.println(new NormalizedClassifier(3, 2.7860, 1.12497));
        System.out.println(new NormalizedClassifier(5, 2.7860, 1.12497));
        System.out.println(new NormalizedClassifier(7, 2.7860, 1.12497));
        System.out.println(new NormalizedClassifier(9, 2.7860, 1.12497));
    }

    @Test
    public void testScoring() {
        final NormalizedClassifier classifier = new NormalizedClassifier(-1., 0., 1.1);
        System.out.println(classifier);
        assertEquals(0, classifier.getScore(-1.5));
        assertEquals(0, classifier.getScore(-1));
        assertEquals(1, classifier.getScore(-0.5));
        assertEquals(1, classifier.getScore(0));
        assertEquals(2, classifier.getScore(0.5));
        assertEquals(2, classifier.getScore(1.1));
        assertEquals(3, classifier.getScore(1.5));
    }

}
