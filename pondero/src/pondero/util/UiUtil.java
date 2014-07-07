package pondero.util;

import static pondero.Logger.error;
import static pondero.Logger.trace;
import static pondero.Logger.warning;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.FontUIResource;
import pondero.Globals;

public class UiUtil {

    static {
        final String seaglassLafClass = "com.seaglasslookandfeel.SeaGlassLookAndFeel";
        try {
            // Class.forName(seaglassLafClass);
            // UIManager.installLookAndFeel("Seaglass", seaglassLafClass);
        } catch (final Exception e) {
            warning("could not install: ", seaglassLafClass);
            error(e);
        }
    }

    public static void enableFullScreenMode(final JFrame window) {
        if (SystemUtil.isMacOSX()) {
            final String className = "com.apple.eawt.FullScreenUtilities";
            final String methodName = "setWindowCanFullScreen";
            try {
                final Class<?> clazz = Class.forName(className);
                final Method method = clazz.getMethod(methodName, new Class<?>[] { Window.class, boolean.class });
                method.invoke(null, window, true);
            } catch (final Throwable t) {
                System.err.println("Full screen mode is not supported");
                error(t);
            }
        }
    }

    public static List<String> getAvailableLafs() {
        final List<String> names = new ArrayList<String>();
        for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            names.add(info.getName());
        }
        Collections.sort(names);
        trace(names);
        return names;
    }

    public static Color getListBackgroundColor() {
        return SystemColor.control;
    }

    public static Color getListBackgroundEvenColor() {
        final Color color = SystemColor.text;
        return color;
    }

    public static Color getListBackgroundOddColor() {
        Color color = UIManager.getColor("controlLHighlight");
        if (color == null) {
            color = SystemColor.info;
        }
        return color;
    }

    public static Color getListForegroundColor() {
        final Color color = SystemColor.textText;
        return color;
    }

    public static Color getListSelectedBackgroundColor() {
        Color color = UIManager.getColor("List[Selected].textBackground");
        if (color == null) {
            color = SystemColor.textHighlight;
        }
        return color;
    }

    public static Color getListSelectedForegroundColor() {
        Color color = UIManager.getColor("List[Selected].textForeground");
        if (color == null) {
            color = SystemColor.textHighlightText;
        }
        return color;
    }

    public static void scaleUi(final double factor) {
        final float multiplier = (float) factor;
        final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
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

    public static void setLaf() {
        final String themeHint = Globals.getThemeString().trim().toLowerCase();
        try {
            for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (info.getName().toLowerCase().indexOf(themeHint) >= 0) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final Exception e) {
            error(e);
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
