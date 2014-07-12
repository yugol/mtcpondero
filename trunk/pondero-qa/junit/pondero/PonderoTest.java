package pondero;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JTextField;
import org.junit.BeforeClass;
import pondero.ui.Pondero;

public class PonderoTest {

    @BeforeClass
    public static void initContext() throws Exception {
        Context.initForTesting();
    }

    private int counter = 0;

    public Pondero getAppWithoutParticipants() throws Exception {
        final Pondero app = Pondero.start(new String[] {});
        final JTextField participantCount = (JTextField) getGrandchild(app.getMainFrame(), JTextField.class, 0);
        if (participantCount != null) {
            participantCount.setText("0");
            final JButton btnOk = (JButton) getGrandchild(app.getMainFrame(), JButton.class, 0);
            btnOk.doClick();
        }
        return app;
    }

    public Pondero getAppWithParticipants() throws Exception {
        final Pondero app = Pondero.start(new String[] {});
        final JTextField participantCount = (JTextField) getGrandchild(app.getMainFrame(), JTextField.class, 0);
        if (participantCount != null) {
            participantCount.setText("5");
            final JButton btnOk = (JButton) getGrandchild(app.getMainFrame(), JButton.class, 0);
            btnOk.doClick();
        }
        return app;
    }

    public synchronized Component getChild(final Component parent, final String name) throws InterruptedException {
        Component child = null;
        for (int i = 0; i < 10; ++i) {
            Thread.sleep(200);
            child = findChild(parent, name);
            if (child != null) { return child; }
        }
        return null;
    }

    public synchronized Component getGrandchild(final Component parent, final Class<? extends Component> clazz, final int index) throws InterruptedException {
        Component child = null;
        for (int i = 0; i < 10; ++i) {
            Thread.sleep(200);
            child = findGrandchild(parent, clazz, index);
            if (child != null) { return child; }
        }
        return null;
    }

    public synchronized Component getGrandchild(final Component parent, final String name) throws InterruptedException {
        Component child = null;
        for (int i = 0; i < 10; ++i) {
            Thread.sleep(200);
            child = findGrandchild(parent, name);
            if (child != null) { return child; }
        }
        return null;
    }

    public synchronized Component getWindow(final Component parent, final String name) throws InterruptedException {
        Component child = null;
        for (int i = 0; i < 10; ++i) {
            Thread.sleep(200);
            child = findWindow(parent, name);
            if (child != null) { return child; }
        }
        return null;
    }

    private Component findChild(final Component parent, final String name) {
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

    private Component findGrandchild(final Component parent, final Class<? extends Component> clazz, final int index) {
        if (parent instanceof Window) {
            final Component[] children = ((Window) parent).getOwnedWindows();
            for (final Component element : children) {
                final Component window = element;
                if (!(window instanceof Window) || !window.isVisible()) {
                    continue;
                }
                counter = 0;
                final Component child = findInternalChildByIndex(window, clazz, index);
                if (child != null) { return child; }
            }
        }
        return null;
    }

    private Component findGrandchild(final Component parent, final String name) {
        if (parent instanceof Window) {
            final Component[] children = ((Window) parent).getOwnedWindows();
            for (final Component element : children) {
                final Component window = element;
                if (!(window instanceof Window) || !window.isVisible()) {
                    continue;
                }
                final Component child = findChild(window, name);
                if (child != null) { return child; }
            }
        }
        return null;
    }

    private Component findInternalChildByIndex(final Component parent, final Class<? extends Component> clazz, final int index) {
        // System.out.println(parent.getClass().getName());
        if (clazz.isInstance(parent)) {
            if (counter == index) { return parent; }
            counter++;
        }
        if (parent instanceof Container) {
            final Component[] children = getChildren(parent);
            for (final Component element : children) {
                final Component child = findInternalChildByIndex(element, clazz, index);
                if (child != null) { return child; }
            }
        }
        return null;
    }

    private Component findWindow(final Component parent, final String name) {
        if (parent instanceof Window) {
            if (name.equals(parent.getName())) { return parent; }
            final Component[] children = ((Window) parent).getOwnedWindows();
            for (final Component element : children) {
                final Component child = findWindow(element, name);
                if (child != null) { return child; }
            }
        }
        return null;
    }

    private Component[] getChildren(final Component parent) {
        if (parent instanceof JMenu) { return ((JMenu) parent).getMenuComponents(); }
        return ((Container) parent).getComponents();
    }

}
