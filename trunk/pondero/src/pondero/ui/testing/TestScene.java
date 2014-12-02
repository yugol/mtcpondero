package pondero.ui.testing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;
import javax.swing.JPanel;
import pondero.task.Testable;
import pondero.task.stimuli.VisualStimulus;

@SuppressWarnings("serial")
public class TestScene extends JPanel {

    public TestScene(final Testable task) {
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

    public TestSceneComponent getEast() {
        return (TestSceneComponent) getSceneComponent(BorderLayout.EAST);
    }

    public TestSceneComponent getNorth() {
        return (TestSceneComponent) getSceneComponent(BorderLayout.NORTH);
    }

    public TestSceneComponent getSouth() {
        return (TestSceneComponent) getSceneComponent(BorderLayout.SOUTH);
    }

    public TestSceneComponent getWest() {
        return (TestSceneComponent) getSceneComponent(BorderLayout.WEST);
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

    public void setStimuli(final List<VisualStimulus> visualStimuli) {
        TestSceneComponent component = getNorth();
        if (component != null) {
            component.setVisualStimuli(visualStimuli);
        }
        component = getWest();
        if (component != null) {
            component.setVisualStimuli(visualStimuli);
        }
        component = getCenter();
        if (component != null) {
            component.setVisualStimuli(visualStimuli);
        }
        component = getEast();
        if (component != null) {
            component.setVisualStimuli(visualStimuli);
        }
        component = getSouth();
        if (component != null) {
            component.setVisualStimuli(visualStimuli);
        }
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
        if (currentComponent != component) {
            if (currentComponent != null) {
                remove(currentComponent);
            }
            if (component != null) {
                add(component, position);
            }
        }
    }

}
