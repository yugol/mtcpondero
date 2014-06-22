package pondero.engine.elements.interfaces;

import pondero.engine.test.stimuli.VisualStimulus;

public interface IsVisualStimulus extends HasPosition, HasSize, IsStimulus {

    @Override
    public VisualStimulus _getStimulus();

}
