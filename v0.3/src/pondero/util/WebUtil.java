package pondero.util;

import static pondero.Logger.trace;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import pondero.Context;
import pondero.ui.exceptions.ExceptionReporting;

public class WebUtil {

    public static void browse(final String page) {
        trace("browsing: ", page);
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(page));
            } else {
                // Ubuntu
                final Runtime runtime = Runtime.getRuntime();
                runtime.exec("/usr/bin/firefox -new-window " + page);
            }
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(null, e);
        }
    }

    public static void mail(final String address, final String subject, final String body) {
        trace("mailing to: ", address);
        try {
            if (Desktop.isDesktopSupported()) {
                final StringBuilder forUri = new StringBuilder("mailto:").append(address);
                if (!StringUtil.isNullOrBlank(subject)) {
                    forUri.append("?subject=").append(urlEncode(subject));
                    if (!StringUtil.isNullOrBlank(body)) {
                        forUri.append("&body=").append(urlEncode(body));
                    }
                } else if (!StringUtil.isNullOrBlank(body)) {
                    forUri.append("?body=").append(urlEncode(body));
                }
                final URI mailUri = new URI(forUri.toString());
                Desktop.getDesktop().mail(mailUri);
            } else {
                throw new Exception("Mail is not supported for this platform");
            }
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(null, e);
        }
    }

    public static InputStream openCloudStream(String urlString) throws IOException {
        if (urlString.startsWith(Context.PURL_HOME)) {
            urlString = purlToUrl(urlString);
        }
        return openUrlStream(urlString);
    }

    private static InputStream openUrlStream(final String urlString) throws IOException {
        final URL url = new URL(urlString);
        trace("opening:", url);
        final InputStream urlStream = url.openStream();
        return urlStream;
    }

    private static final String urlEncode(final String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
    }

    static String purlToUrl(final String purlString) throws IOException {
        try (InputStream urlStream = openUrlStream(purlString)) {
            final BufferedReader urlReader = new BufferedReader(new InputStreamReader(urlStream));
            String line = null;
            while ((line = urlReader.readLine()) != null) {
                int beginIndex = line.indexOf("HREF=");
                if (beginIndex >= 0) {
                    beginIndex += 6;
                    final int endIndex = line.indexOf(">", beginIndex) - 1;
                    return line.substring(beginIndex, endIndex);
                }
            }
        }
        return null;
    }

}
