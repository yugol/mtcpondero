package pondero.ui;

import org.junit.Test;

public class AePlayWaveTest {

    @Test
    public void testPlay() {
        final WavPlayer sound = new WavPlayer("./junit/beep.wav");
        sound.run();
    }

}
