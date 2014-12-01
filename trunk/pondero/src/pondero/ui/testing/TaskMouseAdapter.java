package pondero.ui.testing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pondero.task.controllers.PageController;
import pondero.task.responses.PrevNextResponse;

public class TaskMouseAdapter extends MouseAdapter {

    private final PageController pageController;
    private final boolean        next;

    public TaskMouseAdapter(final PageController pageController, final boolean next) {
        this.pageController = pageController;
        this.next = next;
    }

    @Override
    public void mouseClicked(final MouseEvent evt) {
        final PrevNextResponse input = new PrevNextResponse();
        input.setNext(next);
        try {
            pageController.doStep(input);
        } catch (final Exception e) {
        }
    }

}
