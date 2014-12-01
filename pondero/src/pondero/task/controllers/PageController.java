package pondero.task.controllers;

import pondero.task.Task;
import pondero.task.responses.PrevNextResponse;
import pondero.task.responses.Response;
import pondero.tests.elements.other.Page;

public class PageController extends TaskController {

    public PageController(final Task task, final Page page) {
        super(task, page);
    }

    @Override
    public void doBegin() throws Exception {
        getTask().showCurtains(this);
    }

    @Override
    public void doEnd() throws Exception {
    }

    @Override
    public void doStep(final Response input) throws Exception {
        if (input != null && input instanceof PrevNextResponse) {
            final PrevNextResponse foo = (PrevNextResponse) input;
            if (foo.isNext()) {
                getTask().goNext();
            } else if (foo.isPrev()) {
                getTask().goPrev();
            }
        }
    }

    @Override
    public Page getElement() {
        return (Page) super.getElement();
    }

}
