package pondero;

import static org.junit.Assert.assertTrue;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import javax.swing.JMenu;

public class TestUtil {

    public static Component getChild(final Component parent, final Class<? extends Component> clazz, final int index) throws InterruptedException {
        Component child = null;
        for (int i = 0; child == null; ++i) {
            Thread.sleep(200);
            child = TestUtil.findChild(parent, clazz, index);
            assertTrue(i < 10);
        }
        return child;
    }

    public static Component getChild(final Component parent, final String name) throws InterruptedException {
        Component child = null;
        for (int i = 0; child == null; ++i) {
            Thread.sleep(200);
            child = TestUtil.findChild(parent, name);
            assertTrue(i < 10);
        }
        return child;
    }

    public static Component findChild(final Component parent, final String name) {
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

    public static Component findChild(final Component parent, final Class<? extends Component> clazz, final int index) {
        if (parent instanceof Window) {
            final Component[] children = ((Window) parent).getOwnedWindows();
            for (final Component element : children) {
                final Component window = element;
                if (!(window instanceof Window)) {
                    continue;
                }
                final Component child = getChildIndexedInternal(window, clazz, index, 0);
                if (child != null) { return child; }
            }
        }
        return null;
    }

    private static Component getChildIndexedInternal(final Component parent,
            final Class<? extends Component> clazz,
            final int index, int counter) {
        System.out.println(parent.getClass().getName());
        if (clazz.isInstance(parent)) {
            if (counter == index) { return parent; }
            counter++;
        }
        if (parent instanceof Container) {
            final Component[] children = getChildren(parent);
            for (final Component element : children) {
                final Component child = getChildIndexedInternal(element, clazz, index, counter);
                if (child != null) { return child; }
            }
        }
        return null;
    }

    private static Component[] getChildren(final Component parent) {
        if (parent instanceof JMenu) { return ((JMenu) parent).getMenuComponents(); }
        return ((Container) parent).getComponents();
    }

}
