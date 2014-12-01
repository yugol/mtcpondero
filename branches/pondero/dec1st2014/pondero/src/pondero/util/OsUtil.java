package pondero.util;

import java.io.File;
import java.io.IOException;
import pondero.Context;
import pondero.tests.update.Artifact;

public final class OsUtil {

    public static String getContextDescription() {
        final StringBuilder context = new StringBuilder();
        context.append("CONTEXT:");
        context.append("\nos: ").append(System.getProperty("os.name"));
        context.append("\njava: ").append(System.getProperty("java.version"));
        context.append("\nmodules:");
        for (final Artifact artifact : Context.getArtifacts()) {
            context.append("\n").append("  ").append(artifact.getCodeName());
        }
        return context.toString();
    }

    public static String getLauncherName() {
        if (isWindows()) { return "pondero.bat"; }
        return "pondero.command";
    }

    public static boolean isLinux() {
        return System.getProperty("os.name").indexOf("Linux") >= 0;
    }

    public static boolean isMacOSX() {
        return System.getProperty("os.name").indexOf("Mac OS X") >= 0;
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").indexOf("Windows") >= 0;
    }

    public static void openFile(final File file) throws IOException {
        String[] cmd = null;
        if (isWindows()) {
            cmd = new String[] { "cmd.exe", "/c", file.getCanonicalPath() };
        } else if (isMacOSX()) {
            cmd = new String[] { "open", file.getCanonicalPath() };
        } else {
            throw new UnsupportedOperationException("Your OS is not supported for view");
        }
        Runtime.getRuntime().exec(cmd);
    }

    private OsUtil() {
    }

}
