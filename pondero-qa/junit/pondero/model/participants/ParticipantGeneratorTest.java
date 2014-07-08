package pondero.model.participants;

import org.junit.Test;
import pondero.model._.ParticipantGenerator;

public class ParticipantGeneratorTest {

    @Test
    public void testParticipantGenerator() {
        final ParticipantGenerator pgen = new ParticipantGenerator();
        System.out.println(pgen.nextParticipant().toCsv());
    }

}
