package pondero.engine.staples;

import org.junit.Test;
import pondero.engine.staples.Frame;
import pondero.engine.staples.FrameSequence;

public class FrameSequenceTest {

    @Test
    public void testFrame() {
        System.out.println(new Frame("1=a,\n\tb  , c"));
        System.out.println(new Frame("1=c,a,c"));
    }

    @Test
    public void testFrameSequence() {
        System.out.println(new FrameSequence("1=abc,de,e"));
        System.out.println(new FrameSequence("1=abc,de,e; 2=a,b,c"));
        System.out.println(new FrameSequence("1=abc,de,e; 2=a,b; 2=c"));
        System.out.println(new FrameSequence("3=abc; 1=abc,de,e; 2=a,b; 2=c"));
        System.out.println(new FrameSequence("3=abc, abc; 1=abc,de,e; 2=a,b; 2=c; 3=s"));
    }

}
