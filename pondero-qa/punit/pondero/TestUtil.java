package pondero;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JTextField;
import pondero.ui.Pondero;

public class TestUtil {

    public static synchronized Component findChild(final Component parent, final Class<? extends Component> clazz, final int index) {
        if (parent instanceof Window) {
            final Component[] children = ((Window) parent).getOwnedWindows();
            for (final Component element : children) {
                final Component window = element;
                if (!(window instanceof Window) || !window.isVisible()) {
                    continue;
                }
                counter = 0;
                final Component child = getChildIndexedInternal(window, clazz, index);
                if (child != null) { return child; }
            }
        }
        return null;
    }

    public static synchronized Component findChild(final Component parent, final String name) {
        if (name.equals(parent.getName())) { return parent; }
        if (parent instanceof Container) {
            final Component[] children = getChildren(parent);
            for (final Component element : children) {
                final Component child = findChild(element, name);
                if (child != null) { return child; }
            }
        }
        return null;
    }

    public static synchronized Pondero getApp() throws Exception {
        final Pondero app = Pondero.start(new String[] {});
        final JTextField participantCount = (JTextField) TestUtil.getChild(app.getMainFrame(), JTextField.class, 0);
        if (participantCount != null) {
            participantCount.setText("5");
            final JButton btnOk = (JButton) TestUtil.getChild(app.getMainFrame(), JButton.class, 0);
            btnOk.doClick();
        }
        return app;
    }

    public static synchronized Component getChild(final Component parent, final Class<? extends Component> clazz, final int index) throws InterruptedException {
        Component child = null;
        for (int i = 0; i < 10; ++i) {
            Thread.sleep(200);
            child = TestUtil.findChild(parent, clazz, index);
            if (child != null) { return child; }
        }
        return null;
    }

    public static synchronized Component getChild(final Component parent, final String name) throws InterruptedException {
        Component child = null;
        for (int i = 0; i < 10; ++i) {
            Thread.sleep(200);
            child = TestUtil.findChild(parent, name);
            if (child != null) { return child; }
        }
        return null;
    }

    private static Component getChildIndexedInternal(final Component parent, final Class<? extends Component> clazz, final int index) {
        // System.out.println(parent.getClass().getName());
        if (clazz.isInstance(parent)) {
            if (counter == index) { return parent; }
            counter++;
        }
        if (parent instanceof Container) {
            final Component[] children = getChildren(parent);
            for (final Component element : children) {
                final Component child = getChildIndexedInternal(element, clazz, index);
                if (child != null) { return child; }
            }
        }
        return null;
    }

    private static Component[] getChildren(final Component parent) {
        if (parent instanceof JMenu) { return ((JMenu) parent).getMenuComponents(); }
        return ((Container) parent).getComponents();
    }

    private static int counter = 0;

}
