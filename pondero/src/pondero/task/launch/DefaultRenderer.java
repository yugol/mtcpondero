package pondero.task.launch;

import pondero.task.Task;
import pondero.task.controllers.PageController;
import pondero.task.controllers.StimulusFrame;
import pondero.task.controllers.TrialController;
import pondero.task.stimuli.AuditoryStimulus;
import pondero.tests.elements.trial.TrialLayout;
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
        scene.clear();
        final TrialLayout layout = controller.getElement().getLayout();
        if (layout.getNorth() != null) {
            scene.setNorth(TestSceneComponent.newInstance(layout.getNorth(), controller));
        }
        if (layout.getWest() != null) {
            scene.setWest(TestSceneComponent.newInstance(layout.getWest(), controller));
        }
        if (layout.getCenter() != null) {
            scene.setCenter(TestSceneComponent.newInstance(layout.getCenter(), controller));
        }
        if (layout.getEast() != null) {
            scene.setEast(TestSceneComponent.newInstance(layout.getEast(), controller));
        }
        if (layout.getSouth() != null) {
            scene.setSouth(TestSceneComponent.newInstance(layout.getSouth(), controller));
        }
        uiFrame.showScene();
    }

}
