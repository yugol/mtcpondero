package pondero.engine.staples;

public class TimingUtil {

    public static void pause(final long millis) {
        if (millis > 0) {
            try {
                Thread.sleep(millis);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
