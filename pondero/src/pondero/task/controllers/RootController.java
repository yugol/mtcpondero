package pondero.task.controllers;

import pondero.task.Task;
import pondero.task.responses.Response;
import pondero.tests.elements.other.Defaults;
import pondero.tests.elements.other.Instruct;

public class RootController extends TaskController {

    public RootController(final Task task, final Defaults defaults, final Instruct instruct) {
        super(task, defaults);
        setInstructFont(instruct.getFont());
        setInstructNextKey(instruct.getNextkey());
        setInstructPrevKey(instruct.getPrevkey());
        setInstructScreenColor(instruct.getScreenColor());
        setInstructTextColor(instruct.getTextColor());
    }

    @Override
    public void doBegin() throws Exception {
    }

    @Override
    public void doEnd() throws Exception {
    }

    @Override
    public void doStep(final Response input) throws Exception {
    }

}
