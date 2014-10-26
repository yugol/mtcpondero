package pondero.ui;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AePlayWave extends Thread {

    enum Position {
        LEFT, RIGHT, NORMAL
    }

    private final String   filename;
    private final Position curPosition;
    private final int      EXTERNAL_BUFFER_SIZE = 524288; // 128Kb ;

    public AePlayWave(final String wavfile) {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }

    public AePlayWave(final String wavfile, final Position p) {
        filename = wavfile;
        curPosition = p;
    }

    @Override
    public void run() {

        final File soundFile = new File(filename);
        if (!soundFile.exists()) {
            System.err.println("Wave file not found: " + filename);
            return;
        }

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (final UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        } catch (final IOException e1) {
            e1.printStackTrace();
            return;
        }

        final AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        final DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (final LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (final Exception e) {
            e.printStackTrace();
            return;
        }

        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            final FloatControl pan = (FloatControl) auline
                    .getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT) {
                pan.setValue(1.0f);
            } else if (curPosition == Position.LEFT) {
                pan.setValue(-1.0f);
            }
        }

        auline.start();
        int nBytesRead = 0;
        final byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }
    }

}
