package pondero.task.controllers;

import pondero.task.Task;
import pondero.task.responses.Response;
import pondero.tests.elements.other.Experiment;

public class ExperimentController extends TaskController {

    private final Experiment experiment;

    public ExperimentController(final Task task, final Experiment experiment) {
        super(task);
        this.experiment = experiment;
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

    public Experiment getExperiment() {
        return experiment;
    }

}
