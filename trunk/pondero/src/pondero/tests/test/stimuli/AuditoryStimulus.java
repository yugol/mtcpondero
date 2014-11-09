package pondero.tests.test.stimuli;

import pondero.tests.elements.Element;

public abstract class AuditoryStimulus extends Stimulus {

    public AuditoryStimulus(final Element parent) {
        super(parent);
    }

    public abstract void play();

}
