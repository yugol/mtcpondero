package pondero.task.stimuli;

import pondero.tests.elements.Element;

public abstract class Stimulus {

    protected final Element parent;

    public Stimulus(final Element parent) {
        this.parent = parent;
    }

    public Element getParent() {
        return parent;
    }

}
