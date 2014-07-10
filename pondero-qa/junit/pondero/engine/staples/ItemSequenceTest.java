package pondero.engine.staples;

import org.junit.Test;
import pondero.tests.staples.ItemSequence;

public class ItemSequenceTest {

    @Test
    public void testParse() {
        System.out.println(new ItemSequence("1=abc"));
        System.out.println(new ItemSequence("1,2=abc"));
        System.out.println(new ItemSequence("1,2=ab,c"));
        System.out.println(new ItemSequence("1-4=sequence(a,b,c)"));
        System.out.println(new ItemSequence("1-4=random(a,b,c)"));
    }

}
