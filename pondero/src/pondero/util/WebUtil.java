package pondero.util;

import static pondero.Logger.trace;
import java.awt.Desktop;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import pondero.Context;
import pondero.tests.update.Artifact;

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
            MsgUtil.showExceptionMessage(null, e);
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
                Desktop.getDesktop().mail(new URI(forUri.toString()));
            } else {
                throw new Exception("Mail is not supported for this platform");
            }
        } catch (final Exception e) {
            MsgUtil.showExceptionMessage(null, e);
        }
    }

    public static void sendAboutMail() {
        WebUtil.mail(Context.CONTACT_MAIL_ADDRESS, "[PONDERO][ABOUT]: ", getContextDescription());
    }

    private static String getContextDescription() {
        final StringBuilder context = new StringBuilder();
        context.append("\n\n\nPS: CONTEXT:");
        context.append("\nos: ").append(System.getProperty("os.name"));
        context.append("\njava: ").append(System.getProperty("java.version"));
        context.append("\nmodules:");
        for (final Artifact artifact : Context.getArtifacts()) {
            context.append("\n").append("  ").append(artifact.getCodeName());
        }
        return context.toString();
    }

    private static final String urlEncode(final String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
    }

}
