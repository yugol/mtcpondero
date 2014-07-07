package pondero.util;

import static pondero.Logger.trace;
import java.awt.Desktop;
import java.net.URI;

public class WebUtil {

    public static void browse(String page) throws Exception {
        trace("browsing: ", page);
        if (Desktop.isDesktopSupported()) {
            // Windows
            Desktop.getDesktop().browse(new URI(page));
        } else {
            // Ubuntu
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("/usr/bin/firefox -new-window " + page);
        }
    }

}
