package pondero.task;

import pondero.data.Workbook;
import pondero.data.model.basic.Participant;
import pondero.task.launch.TaskData;
import pondero.task.launch.TaskRenderer;
import pondero.task.responses.Response;
import pondero.tests.Test;
import pondero.tests.elements.other.Instruct;

public class Task extends TaskBase implements Runnable, Testable {

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

    @Override
    public void doStep(final Response input) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Instruct getInstructions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void kill() {
        getData().markStopTime(TaskData.END_KILL);
        getRenderer().destroy();
        signalTaskEnded();
    }

    @Override
    public void run() {
        signalTaskStarted();
        getData().markStartTime();
        getRenderer().init();
    }

}
