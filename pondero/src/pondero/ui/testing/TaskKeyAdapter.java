package pondero.ui.testing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import pondero.task.controllers.PageController;
import pondero.task.responses.PrevNextResponse;

public class TaskKeyAdapter extends KeyAdapter {

    private final PageController pageController;

    public TaskKeyAdapter(final PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void keyPressed(final KeyEvent evt) {
        try {
            final PrevNextResponse input = new PrevNextResponse();
            input.setNext(evt.getKeyChar() == pageController.getInstructNextKey());
            input.setPrev(evt.getKeyChar() == pageController.getInstructPrevKey());
            pageController.doStep(input);
        } catch (final Exception e) {
        }
    }
}
