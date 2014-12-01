package pondero.task.controllers;

import pondero.task.Task;
import pondero.task.responses.Response;
import pondero.tests.elements.trial.Trial;

public class TrialController extends TaskController {

    public TrialController(final Task task, final Trial trial) {
        super(task, trial);
    }

    @Override
    public void doBegin() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void doEnd() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void doStep(final Response input) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Trial getElement() {
        return (Trial) super.getElement();
    }

}
