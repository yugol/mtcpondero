package pondero.tests.test.stimuli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import pondero.tests.elements.Element;
import pondero.ui.exceptions.ExceptionReporting;

public class SoundStimulus extends AuditoryStimulus implements Runnable {

    private static final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb ;

    private AudioInputStream audioInputStream;

    public SoundStimulus(final Element parent) {
        super(parent);
    }

    @Override
    public void play() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        final AudioFormat format = audioInputStream.getFormat();
        final DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        final byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

        SourceDataLine audioLine = null;
        try {
            audioLine = (SourceDataLine) AudioSystem.getLine(info);
            audioLine.open(format);
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(null, e);
        }

        audioLine.start();
        int nBytesRead = 0;
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    audioLine.write(abData, 0, nBytesRead);
                }
            }
        } catch (final IOException e) {
            ExceptionReporting.showExceptionMessage(null, e);
        } finally {
            audioLine.drain();
            audioLine.close();
        }
    }

    public void setPath(final String path) throws IOException, UnsupportedAudioFileException {
        final File soundFile = new File(path);
        if (soundFile.exists()) {
            // try to open the file directly
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } else {
            final String folder = "/" + getParent().getTest().getClass().getPackage().getName().replace(".", "/") + "/";
            final String source = folder + path;
            final InputStream soundStream = getParent().getTest().getClass().getResourceAsStream(source);
            audioInputStream = AudioSystem.getAudioInputStream(soundStream);
        }
    }

}
