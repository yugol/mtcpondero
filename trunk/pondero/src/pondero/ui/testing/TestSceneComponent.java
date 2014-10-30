package pondero.ui.testing;

import javax.swing.JComponent;
import pondero.tests.test.Test;

@SuppressWarnings("serial")
public class TestSceneComponent extends JComponent {

    private final Test test;

    public TestSceneComponent(final Test test) {
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
