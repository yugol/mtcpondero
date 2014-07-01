package pondero;


public class OsUtil {

    public static int getFrameRate() {
        return 15;
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

    public static void setupMainWindow(final String applicationName) {
        if (isMacOSX()) {
            try {
                // System.setProperty("com.apple.mrj.application.apple.menu.about.name", applicationName);
                // System.setProperty("com.apple.macos.useScreenMenuBar", "true");
                // System.setProperty("apple.laf.useScreenMenuBar", "true"); // for older versions of Java
            } catch (final SecurityException e) {
                /* probably running via webstart, do nothing */
            }
        }
    }

}
