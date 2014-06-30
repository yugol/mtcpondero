package pondero.engine.elements.interfaces;

import pondero.engine.test.responses.Response;

public interface IsController {

    public void _doBegin();

    public void _doEnd();

    public void _doStep(Response input);

}
