package pondero.task;

import java.util.ArrayList;
import java.util.List;
import pondero.data.Workbook;
import pondero.data.model.basic.Participant;
import pondero.task.controllers.ExperimentController;
import pondero.task.launch.TaskData;
import pondero.task.launch.TaskMonitor;
import pondero.task.launch.TaskRenderer;
import pondero.tests.Test;

public abstract class TaskBase {

    private final TaskRenderer      renderer;
    private final Test              test;
    private final Workbook          workbook;
    private final Participant       participant;
    private final TaskData          data     = new TaskData(System.currentTimeMillis()); ;
    private final List<TaskMonitor> monitors = new ArrayList<>();
    private ExperimentController    root;

    protected TaskBase(final TaskRenderer renderer, final Test test) {
        this(renderer, test, null);
    }

    protected TaskBase(final TaskRenderer renderer, final Test test, final Workbook workbook) {
        this(renderer, test, workbook, null);
    }

    protected TaskBase(final TaskRenderer renderer, final Test test, final Workbook workbook, final Participant participant) {
        this.renderer = renderer;
        this.workbook = workbook;
        this.participant = participant;
        this.test = test;
    }

    public void addMonitor(final TaskMonitor monitor) {
        monitors.add(monitor);
    }

    public String getCodeName() {
        return test.getCodeName();
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

    protected void buildExperimentTree() {
        root = new ExperimentController((Task) this, test.getExperiment());
    }

    protected void cleanup() {
        renderer.doEnd();
        signalTaskEnded();
    }

    protected TaskRenderer getRenderer() {
        return renderer;
    }

    protected void signalTaskEnded() {
        for (final TaskMonitor monitor : monitors) {
            monitor.onTaskEnded(test, data);
        }
    }

    protected void signalTaskStarted() {
        for (final TaskMonitor monitor : monitors) {
            monitor.onTaskStarted(test);
        }
    }

}
