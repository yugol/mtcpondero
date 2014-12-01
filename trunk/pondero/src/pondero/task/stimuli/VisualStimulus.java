package pondero.task.stimuli;

import java.awt.Graphics2D;
import pondero.tests.elements.Element;

public abstract class VisualStimulus extends Stimulus {

    public VisualStimulus(final Element parent) {
        super(parent);
    }

    public abstract void render(final Graphics2D g2d, int surfaceWidth, int surfaceHeight);

}
