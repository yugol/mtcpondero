package pondero.ui.tests;

import javax.swing.JComponent;
import pondero.engine.test.Test;

@SuppressWarnings("serial")
public class TestVisualComponent extends JComponent {

    private final Test test;

    public TestVisualComponent(final Test test) {
        this.test = test;
        setFocusable(true);
    }

    public Test getTest() {
        return test;
    }

    public boolean hasTest() {
        return test != null;
    }

}
