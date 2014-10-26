package pondero.ui;

import org.junit.Test;

public class AePlayWaveTest {

    @Test
    public void testPlay() {
        final AePlayWave sound = new AePlayWave("./junit/beep.wav");
        sound.run();
    }

}
