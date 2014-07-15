package pondero.tests.staples;

public class Timing {

    public static void pause(final long millis) {
        if (millis > 0) {
            try {
                Thread.sleep(millis);
            } catch (final InterruptedException e) {
            }
        }
    }

}
