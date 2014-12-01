package pondero.ui.testing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import pondero.task.Testable;
import pondero.task.responses.PrevNextResponse;

@Deprecated
public class TestKeyAdapter extends KeyAdapter {

    private final Testable test;

    public TestKeyAdapter(final Testable test) {
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
