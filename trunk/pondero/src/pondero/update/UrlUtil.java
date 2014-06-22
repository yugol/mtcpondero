package pondero.update;

import static pondero.Logger.trace;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import pondero.Globals;

public class UrlUtil {

    public static InputStream openCloudStream(String urlString) throws IOException {
        if (urlString.startsWith(Globals.PURL_HOME)) {
            urlString = purlToUrl(urlString);
        }
        return openUrlStream(urlString);
    }

    private static InputStream openUrlStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        trace(url);
        InputStream urlStream = url.openStream();
        return urlStream;
    }

    static String purlToUrl(String purlString) throws IOException {
        try (InputStream urlStream = openUrlStream(purlString)) {
            BufferedReader urlReader = new BufferedReader(new InputStreamReader(urlStream));
            String line = null;
            while ((line = urlReader.readLine()) != null) {
                int beginIndex = line.indexOf("HREF=");
                if (beginIndex >= 0) {
                    beginIndex += 6;
                    int endIndex = line.indexOf(">", beginIndex) - 1;
                    return line.substring(beginIndex, endIndex);
                }
            }
        }
        return null;
    }

}
