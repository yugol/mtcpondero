package pondero.ui.testing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import pondero.tests.test.Test;
import pondero.tests.test.responses.PrevNextResponse;

public class TestKeyAdapter extends KeyAdapter {

    private final Test test;

    public TestKeyAdapter(final Test test) {
        this.test = test;
    }

    @Override
    public void keyPressed(final KeyEvent evt) {
        try {
            final PrevNextResponse input = new PrevNextResponse();
            input.setNext(evt.getKeyChar() == test.getInstructions().getNextkey());
            input.setPrev(evt.getKeyChar() == test.getInstructions().getPrevkey());
            test.doStep(input);
        } catch (final Exception e) {
        }
    }

}
