package pondero.ui.testing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;
import javax.swing.JPanel;
import pondero.task.stimuli.VisualStimulus;

@SuppressWarnings("serial")
public class TestScene extends JPanel {

    public TestScene() {
        setFocusable(true);
        setBorder(null);
        setLayout(new BorderLayout());
    }

    public void focalize() {
        final SceneComponent center = getCenter();
        if (center != null) {
            center.requestFocusInWindow();
        }
    }

    public SceneComponent getCenter() {
        return (SceneComponent) getSceneComponent(BorderLayout.CENTER);
    }

    public SceneComponent getEast() {
        return (SceneComponent) getSceneComponent(BorderLayout.EAST);
    }

    public SceneComponent getNorth() {
        return (SceneComponent) getSceneComponent(BorderLayout.NORTH);
    }

    public SceneComponent getSouth() {
        return (SceneComponent) getSceneComponent(BorderLayout.SOUTH);
    }

    public SceneComponent getWest() {
        return (SceneComponent) getSceneComponent(BorderLayout.WEST);
    }

    public void setCenter(final SceneComponent component) {
        setSceneComponent(component, BorderLayout.CENTER);
    }

    public void setEast(final SceneComponent component) {
        setSceneComponent(component, BorderLayout.EAST);
    }

    public void setNorth(final SceneComponent component) {
        setSceneComponent(component, BorderLayout.NORTH);
    }

    public void setSouth(final SceneComponent component) {
        setSceneComponent(component, BorderLayout.SOUTH);
    }

    public void setStimuli(final List<VisualStimulus> visualStimuli) {
        SceneComponent component = getNorth();
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

    public void setWest(final SceneComponent component) {
        setSceneComponent(component, BorderLayout.WEST);
    }

    private Component getSceneComponent(final String position) {
        final BorderLayout layout = (BorderLayout) getLayout();
        return layout.getLayoutComponent(position);
    }

    private void setSceneComponent(final SceneComponent component, final String position) {
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
