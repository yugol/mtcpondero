package pondero.update;

import static pondero.Logger.trace;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlUtil {

    public static InputStream openUrlStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        trace(url);
        InputStream urlStream = url.openStream();
        return urlStream;
    }

    public static String purlToString(String purl) throws IOException {
        return urlToString(purlToUrl(purl));
    }

    public static String purlToUrl(String purl) throws IOException {
        String content = UrlUtil.urlToString(purl);
        String normalisedContent = content.toUpperCase();
        int beginIndex = normalisedContent.indexOf("HREF=") + 6;
        int endIndex = normalisedContent.indexOf(">", beginIndex) - 1;
        return content.substring(beginIndex, endIndex);
    }

    public static String urlToString(String urlString) throws IOException {
        InputStream urlStream = openUrlStream(urlString);
        BufferedReader urlReader = new BufferedReader(new InputStreamReader(urlStream));
        StringBuilder urlContent = new StringBuilder();
        String line = null;
        while ((line = urlReader.readLine()) != null) {
            urlContent.append(line);
            urlContent.append("\n");
        }
        urlStream.close();
        return urlContent.toString();
    }

}
