package pondero.ui.future;

import java.awt.Window;
import java.lang.reflect.Method;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fullscreen extends JFrame {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void enableOSXFullscreen(final Window window) {
        try {
            final Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
            final Class params[] = new Class[] { Window.class, Boolean.TYPE };
            final Method method = util.getMethod("setWindowCanFullScreen", params);
            method.invoke(util, window, true);
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(final String[] args) {
        final Fullscreen fs = new Fullscreen();
        Fullscreen.requestToggleFullScreen(fs);

        try {
            Thread.sleep(5000);
        } catch (final InterruptedException e) {
        }

        Fullscreen.requestToggleFullScreen(fs);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void requestToggleFullScreen(final Window window)
    {
        try {
            final Class appClass = Class.forName("com.apple.eawt.Application");
            final Class params[] = new Class[] {};

            final Method getApplication = appClass.getMethod("getApplication", params);
            final Object application = getApplication.invoke(appClass);
            final Method requestToggleFulLScreen = application.getClass().getMethod("requestToggleFullScreen", Window.class);

            requestToggleFulLScreen.invoke(application, window);
        } catch (final Exception e) {
            System.out.println("An exception occurred while trying to toggle full screen mode");
        }
    }

    public Fullscreen() {
        enableOSXFullscreen(this);
        setVisible(true);
    }
}