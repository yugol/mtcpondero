package pondero.ui.testing;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import pondero.tests.test.Test;

@SuppressWarnings("serial")
public class TestScene extends JPanel {

    public TestScene(final Test task) {
        setFocusable(true);
        setBorder(null);
        setLayout(new BorderLayout());
    }

    public void focalize() {
        final TestSceneComponent center = getCenter();
        if (center != null) {
            center.requestFocusInWindow();
        }
    }

    public TestSceneComponent getCenter() {
        return (TestSceneComponent) getSceneComponent(BorderLayout.CENTER);
    }

    public TestSceneComponent getSouth() {
        return (TestSceneComponent) getSceneComponent(BorderLayout.SOUTH);
    }

    public void setCenter(final TestSceneComponent component) {
        setSceneComponent(component, BorderLayout.CENTER);
    }

    public void setEast(final TestSceneComponent component) {
        setSceneComponent(component, BorderLayout.EAST);
    }

    public void setNorth(final TestSceneComponent component) {
        setSceneComponent(component, BorderLayout.NORTH);
    }

    public void setSouth(final TestSceneComponent component) {
        setSceneComponent(component, BorderLayout.SOUTH);
    }

    public void setWest(final TestSceneComponent component) {
        setSceneComponent(component, BorderLayout.WEST);
    }

    private Component getSceneComponent(final String position) {
        final BorderLayout layout = (BorderLayout) getLayout();
        return layout.getLayoutComponent(position);
    }

    private void setSceneComponent(final TestSceneComponent component, final String position) {
        final Component currentComponent = getSceneComponent(position);
        if (currentComponent != null) {
            currentComponent.setVisible(false);
            remove(currentComponent);
        }
        if (component != null) {
            add(component, position);
        }
    }

}
