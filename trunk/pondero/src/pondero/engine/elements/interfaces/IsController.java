package pondero.engine.elements.interfaces;

import pondero.engine.test.responses.Response;

public interface IsController {

    public void doBegin();

    public void doEnd();

    public void doStep(Response input);

}
