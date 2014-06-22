package pondero;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Method;
import java.util.Enumeration;
import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.FontUIResource;
import pondero.ui.Messages;

public class OsUtil {

    public static void enableFullScreenMode(final JFrame window) {
        if (isMacOSX()) {
            final String className = "com.apple.eawt.FullScreenUtilities";
            final String methodName = "setWindowCanFullScreen";
            try {
                final Class<?> clazz = Class.forName(className);
                final Method method = clazz.getMethod(methodName, new Class<?>[] { Window.class, boolean.class });
                method.invoke(null, window, true);
            } catch (final Throwable t) {
                System.err.println("Full screen mode is not supported");
                t.printStackTrace();
            }
        }
    }

    public static void factorFontSize(final double factor) {
        final float multiplier = (float) factor;
        final UIDefaults defaults = UIManager.getDefaults();
        for (@SuppressWarnings("rawtypes")
        final Enumeration e = defaults.keys(); e.hasMoreElements();) {
            final Object key = e.nextElement();
            final Object value = defaults.get(key);
            if (value instanceof Font) {
                final Font font = (Font) value;
                final int newSize = Math.round(font.getSize() * multiplier);
                if (value instanceof FontUIResource) {
                    defaults.put(key, new FontUIResource(font.getName(), font.getStyle(), newSize));
                } else {
                    defaults.put(key, new Font(font.getName(), font.getStyle(), newSize));
                }
            }
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

    public static void localizeJOptionPaneButtons() {
        UIManager.put("OptionPane.yesButtonText", Messages.getString("lbl.yes"));
        UIManager.put("OptionPane.noButtonText", Messages.getString("lbl.no"));
        UIManager.put("OptionPane.cancelButtonText", Messages.getString("lbl.cancel"));

    }

    public static void setLaf() {
        try {
            for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (info.getName().toLowerCase().indexOf(Globals.getLaf()) >= 0) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final Exception e) {
            e.printStackTrace();
        }
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

    public static void showCentered(final Window window) {
        final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int x = (gd.getDisplayMode().getWidth() - window.getWidth()) / 2;
        final int y = (gd.getDisplayMode().getHeight() - window.getHeight()) / 2;
        window.setLocation(x, y);
    }

    public static void showFractionedCentered(final Window window) {
        final double xFraction = 8. / 10;
        final double yFraction = 11. / 12;
        final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int width = (int) (gd.getDisplayMode().getWidth() * xFraction);
        final int height = (int) (gd.getDisplayMode().getHeight() * yFraction);
        window.setSize(width, height);
        showCentered(window);
    }

    public static void showFullScreen(final JFrame window) {
        final GraphicsDevice[] gds = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        GraphicsDevice d = null;
        for (final GraphicsDevice gd : gds) {
            if (gd.isFullScreenSupported()) {
                d = gd;
                break;
            }
        }
        if (d == null) {
            // d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        }
        if (d != null) {
            window.setExtendedState(Frame.MAXIMIZED_BOTH);
            window.setUndecorated(true);
            window.setResizable(false);
            window.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(final FocusEvent arg0) {
                    window.setAlwaysOnTop(true);
                }

                @Override
                public void focusLost(final FocusEvent arg0) {
                    window.setAlwaysOnTop(false);
                }

            });
            d.setFullScreenWindow(window);
        } else {
            window.setVisible(true);
        }
    }

    public static void showMaximized(final JFrame window) {
        final Rectangle usableArea = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        window.setBounds(usableArea);
    }

}
