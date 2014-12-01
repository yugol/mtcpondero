package pondero.task.controllers;

import pondero.task.Task;
import pondero.task.responses.Response;
import pondero.tests.elements.other.Block;

public class BlockController extends TaskController {

    public BlockController(final Task task, final Block block) {
        super(task, block);
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
    public Block getElement() {
        return (Block) super.getElement();
    }

}
