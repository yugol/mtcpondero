package pondero.tests.elements.interfaces;

import pondero.tests.test.stimuli.VisualStimulus;

public interface IsVisualStimulus extends HasPosition, HasSize, IsStimulus {

    @Override
    public VisualStimulus _getStimulus();

}
