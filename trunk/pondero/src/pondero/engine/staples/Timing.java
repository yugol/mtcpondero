package pondero.engine.staples;

import static pondero.Logger.error;

public class Timing {

    public static void pause(final long millis) {
        if (millis > 0) {
            try {
                Thread.sleep(millis);
            } catch (final InterruptedException e) {
                error(e);
            }
        }
    }

}
