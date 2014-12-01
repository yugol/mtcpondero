package pondero.tests.elements.interfaces;

import pondero.task.responses.Response;

public interface IsController {

    public void doBegin() throws Exception;

    public void doEnd() throws Exception;

    public void doNext(Response input) throws Exception;

}
