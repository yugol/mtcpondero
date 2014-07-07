package pondero.util;

import pondero.L10n;

public class SysUtil {

    public static void configure() {
        if (isMacOSX()) {
            try {
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", L10n.getString("lbl.pondero"));
                System.setProperty("com.apple.macos.useScreenMenuBar", "true");
                System.setProperty("apple.laf.useScreenMenuBar", "true"); // for older versions of Java
            } catch (final SecurityException e) {
                /* probably running via webstart, do nothing */
            }
        }
        if (isWindows()) {
            // System.setProperty("awt.useSystemAAFontSettings", "on");
            System.setProperty("swing.aatext", "true");
        }
    }

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

}
