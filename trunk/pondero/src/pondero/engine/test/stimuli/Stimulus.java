package pondero.engine.test.stimuli;

import pondero.engine.elements.Element;

public abstract class Stimulus {

    protected final Element parent;

    public Stimulus(final Element parent) {
        this.parent = parent;
    }

    public Element getParent() {
        return parent;
    }

}
