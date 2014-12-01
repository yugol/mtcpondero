package pondero.task;

import java.util.ListIterator;
import pondero.data.Workbook;
import pondero.data.model.basic.Participant;
import pondero.task.controllers.PageController;
import pondero.task.controllers.StimulusFrame;
import pondero.task.controllers.TaskController;
import pondero.task.controllers.TrialController;
import pondero.task.launch.TaskData;
import pondero.task.launch.TaskRenderer;
import pondero.task.responses.Response;
import pondero.tests.Test;
import pondero.tests.elements.other.Instruct;
import pondero.ui.exceptions.ExceptionReporting;

public class Task extends TaskBase implements Runnable, Testable {

    private ListIterator<TaskController> controllerIterator;
    private TaskController               controller;

    public Task(final TaskRenderer renderer, final Test test) {
        super(renderer, test);
        getRenderer().setTask(this);
    }

    public Task(final TaskRenderer renderer, final Test test, final Workbook workbook) {
        super(renderer, test, workbook);
        getRenderer().setTask(this);
    }

    public Task(final TaskRenderer renderer, final Test test, final Workbook workbook, final Participant participant) {
        super(renderer, test, workbook, participant);
        getRenderer().setTask(this);
    }

    public synchronized void doEnd() throws Exception {
        cleanUp(TaskData.END_SUCCESS);
    }

    @Override
    public synchronized void doStep(final Response input) throws Exception {
        controller.doStep(input);
    }

    @Override
    public Instruct getInstructions() {
        // TODO Auto-generated method stub 
        return null;
    }

    public synchronized void goNext() throws Exception {
        if (controllerIterator.hasNext()) {
            controller = controllerIterator.next();
            controller.doBegin();
            doStep(null);
        } else {
            doEnd();
        }
    }

    public synchronized void goPrev() throws Exception {
        if (controllerIterator.hasPrevious()) {
            controller = controllerIterator.previous();
            controller.doBegin();
            doStep(null);
        }
    }

    @Override
    public synchronized void kill() {
        cleanUp(TaskData.END_USER);
    }

    public void presentStimuli(final StimulusFrame frame) {
        getRenderer().presentStimuli(frame);
    }

    @Override
    public void run() {
        try {

            controllerIterator = iterator();
            signalTaskStarted();
            getRenderer().doBegin();
            getData().markStartTime();
            goNext();
        } catch (final Exception e) {
            cleanUp(TaskData.END_ERROR);
            ExceptionReporting.showExceptionMessage(null, e);
        }
    }

    public void showCurtains(final PageController pageController) {
        getRenderer().showCurtains(pageController);
    }

    public void showScene(final TrialController trialController) {
        getRenderer().showScene(trialController);
    }

}
