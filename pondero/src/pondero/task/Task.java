package pondero.task;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import pondero.data.Workbook;
import pondero.data.model.basic.Participant;
import pondero.task.controllers.BlockController;
import pondero.task.controllers.ExperimentController;
import pondero.task.controllers.PageController;
import pondero.task.controllers.RootController;
import pondero.task.controllers.StimulusFrame;
import pondero.task.controllers.TaskController;
import pondero.task.controllers.TrialController;
import pondero.task.launch.TaskData;
import pondero.task.launch.TaskMonitor;
import pondero.task.launch.TaskRenderer;
import pondero.task.responses.Response;
import pondero.tests.Test;
import pondero.tests.elements.workflow.Block;
import pondero.tests.elements.workflow.Experiment;
import pondero.ui.exceptions.ExceptionReporting;

public class Task extends Thread implements Iterable<TaskController> {

    private final Test                   test;
    private final Workbook               workbook;
    private final Participant            participant;
    private final TaskData               data     = new TaskData(System.currentTimeMillis()); ;

    private final TaskRenderer           renderer;

    private TaskController               controller;
    private ListIterator<TaskController> controllerIterator;

    private final List<TaskMonitor>      monitors = new ArrayList<>();
    private final List<TaskController>   leaves   = new ArrayList<>();

    public Task(final TaskRenderer renderer, final Test test) {
        this(renderer, test, null);
    }

    public Task(final TaskRenderer renderer, final Test test, final Workbook workbook) {
        this(renderer, test, workbook, null);
    }

    public Task(final TaskRenderer renderer, final Test test, final Workbook workbook, final Participant participant) {
        this.test = test;
        this.workbook = workbook;
        this.participant = participant;
        this.renderer = renderer;
        renderer.setTask(this);
        buildExperimentTree();
    }

    public void addMonitor(final TaskMonitor monitor) {
        monitors.add(monitor);
    }

    public synchronized void doEnd() throws Exception {
        cleanUp(TaskData.END_SUCCESS);
    }

    public synchronized void doStep(final Response input) throws Exception {
        controller.doStep(input);
    }

    public TaskData getData() {
        return data;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Test getTest() {
        return test;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public synchronized void goNext() throws Exception {
        if (controllerIterator.hasNext()) {
            final TaskController foo = controllerIterator.next();
            controller = foo == controller ? controllerIterator.next() : foo;
            controller.doBegin();
            doStep(null);
        } else {
            doEnd();
        }
    }

    public synchronized void goPrev() throws Exception {
        if (controllerIterator.hasPrevious()) {
            final TaskController foo = controllerIterator.previous();
            controller = foo == controller ? controllerIterator.previous() : foo;
            controller.doBegin();
            doStep(null);
        }
    }

    @Override
    public ListIterator<TaskController> iterator() {
        return leaves.listIterator();
    }

    public synchronized void kill() {
        cleanUp(TaskData.END_USER);
    }

    public void presentStimuli(final StimulusFrame frame) {
        renderer.presentStimuli(frame);
    }

    @Override
    public void run() {
        try {
            controllerIterator = iterator();
            signalTaskStarted();
            renderer.doBegin();
            data.markStartTime();
            goNext();
        } catch (final Exception e) {
            cleanUp(TaskData.END_ERROR);
            ExceptionReporting.showExceptionMessage(null, e);
        }
    }

    public void showCurtains(final PageController pageController) {
        renderer.showCurtains(pageController);
    }

    public void showScene(final TrialController trialController) {
        renderer.showScene(trialController);
    }

    private synchronized void cleanUp(final int errorCode) {
        data.markStopTime(errorCode);
        if (workbook != null) {
            for (final TaskController controller : leaves) {
                if (controller instanceof TrialController) {
                    data.add(((TrialController) controller).getRecord());
                }
            }
        }
        renderer.doEnd();
        for (final TaskMonitor monitor : monitors) {
            monitor.onTaskEnded(this, data);
        }
    }

    private void signalTaskStarted() {
        for (final TaskMonitor monitor : monitors) {
            monitor.onTaskStarted(this);
        }
    }

    protected void buildExperimentTree() {
        final Experiment experiment = test.getExperiment();
        final TaskController experimentController = new ExperimentController(this, experiment);
        if (experiment.getPreinstructions() != null) {
            for (final String pageId : experiment.getPreinstructions()) {
                final PageController pageController = new PageController(this, test.getPage(pageId));
                experimentController.addChild(pageController);
                leaves.add(pageController);
            }
        }
        for (final String blockId : experiment.getBlocks()) {
            final Block block = test.getBlock(blockId);
            final BlockController blockController = new BlockController(this, block);
            experimentController.addChild(blockController);
            if (block.getPreinstructions() != null) {
                for (final String pageId : block.getPreinstructions()) {
                    final PageController pageController = new PageController(this, test.getPage(pageId));
                    blockController.addChild(pageController);
                    leaves.add(pageController);
                }
            }
            for (final String trialId : block.getTrials()) {
                final TrialController trialController = new TrialController(this, test.getTrial(trialId));
                blockController.addChild(trialController);
                leaves.add(trialController);
            }
            if (block.getPostinstructions() != null) {
                for (final String pageId : block.getPostinstructions()) {
                    final PageController pageController = new PageController(this, test.getPage(pageId));
                    blockController.addChild(pageController);
                    leaves.add(pageController);
                }
            }
        }
        if (experiment.getPostinstructions() != null) {
            for (final String pageId : experiment.getPostinstructions()) {
                final PageController pageController = new PageController(this, test.getPage(pageId));
                experimentController.addChild(pageController);
                leaves.add(pageController);
            }
        }
        final RootController rootController = new RootController(this, test.getDefaults(), test.getInstructions());
        rootController.addChild(experimentController);
    }

}
