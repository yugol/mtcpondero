package pondero.tests.interfaces;

import pondero.task.responses.Response;

public interface IsController {

    public void doBegin() throws Exception;

    public void doEnd() throws Exception;

    public void doStep(Response input) throws Exception;

}
