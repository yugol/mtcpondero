package pondero.ui.tests;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import pondero.engine.test.Test;

@SuppressWarnings("serial")
public class TestScene extends JPanel {

    public TestScene(final Test task) {
        setFocusable(true);
        setBorder(null);
        setLayout(new BorderLayout());
    }

    public void focalize() {
        getCenter().requestFocusInWindow();
    }

    public TestVisualComponent getCenter() {
        return (TestVisualComponent) getSceneComponent(BorderLayout.CENTER);
    }

    public TestVisualComponent getSouth() {
        return (TestVisualComponent) getSceneComponent(BorderLayout.SOUTH);
    }

    public void setCenter(final TestVisualComponent component) {
        setSceneComponent(component, BorderLayout.CENTER);
    }

    public void setEast(final TestVisualComponent component) {
        setSceneComponent(component, BorderLayout.EAST);
    }

    public void setNorth(final TestVisualComponent component) {
        setSceneComponent(component, BorderLayout.NORTH);
    }

    public void setSouth(final TestVisualComponent component) {
        setSceneComponent(component, BorderLayout.SOUTH);
    }

    public void setWest(final TestVisualComponent component) {
        setSceneComponent(component, BorderLayout.WEST);
    }

    private Component getSceneComponent(final String position) {
        final BorderLayout layout = (BorderLayout) getLayout();
        return layout.getLayoutComponent(position);
    }

    private void setSceneComponent(final TestVisualComponent component, final String position) {
        final Component currentComponent = getSceneComponent(position);
        if (currentComponent != null) {
            remove(currentComponent);
        }
        if (component != null) {
            add(component, position);
        }
    }

}
