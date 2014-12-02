package pondero.task.launch;

import pondero.task.Task;
import pondero.task.controllers.PageController;
import pondero.task.controllers.StimulusFrame;
import pondero.task.controllers.TrialController;
import pondero.task.stimuli.AuditoryStimulus;
import pondero.tests.elements.workflow.Trial.TrialLayout;
import pondero.ui.testing.TestFrame;
import pondero.ui.testing.TestScene;
import pondero.ui.testing.TestSceneComponent;

public class DefaultRenderer implements TaskRenderer {

    private TestFrame uiFrame;

    @Override
    public void doBegin() {
        uiFrame.setVisible(true);
    }

    @Override
    public void doEnd() {
        uiFrame.dispose();
    }

    @Override
    public synchronized void presentStimuli(final StimulusFrame frame) {
        for (final AuditoryStimulus stimulus : frame.getAuditoryStimuli()) {
            stimulus.play();
        }
        uiFrame.getScene().setStimuli(frame.getVisualStimuli());
        uiFrame.invalidateScene();
    }

    @Override
    public void setTask(final Task task) {
        uiFrame = new TestFrame(task);
    }

    @Override
    public void showCurtains(final PageController controller) {
        uiFrame.showCurtains(controller);
    }

    @Override
    public void showScene(final TrialController controller) {
        final TestScene scene = uiFrame.getScene();
        final TrialLayout layout = controller.getElement().getLayout();
        if (layout.getNorth() != null) {
            scene.setNorth(prepareComponent(scene.getNorth(), layout.getNorth(), controller));
        } else {
            scene.setNorth(null);
        }
        if (layout.getWest() != null) {
            scene.setWest(prepareComponent(scene.getWest(), layout.getWest(), controller));
        } else {
            scene.setWest(null);
        }
        if (layout.getCenter() != null) {
            scene.setCenter(prepareComponent(scene.getCenter(), layout.getCenter(), controller));
        } else {
            scene.setCenter(null);
        }
        if (layout.getEast() != null) {
            scene.setEast(prepareComponent(scene.getEast(), layout.getEast(), controller));
        } else {
            scene.setEast(null);
        }
        if (layout.getSouth() != null) {
            scene.setSouth(prepareComponent(scene.getSouth(), layout.getSouth(), controller));
        } else {
            scene.setSouth(null);
        }
        uiFrame.showScene();
    }

    private TestSceneComponent prepareComponent(TestSceneComponent component, final String name, final TrialController controller) {
        if (component == null || !component.getClass().getName().equals(name)) {
            component = TestSceneComponent.getInstance(name);
        }
        component.setController(controller);
        return component;
    }

}
