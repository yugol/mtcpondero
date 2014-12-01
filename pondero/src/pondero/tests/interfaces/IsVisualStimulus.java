package pondero.tests.interfaces;

import pondero.task.stimuli.VisualStimulus;

public interface IsVisualStimulus extends HasPosition, HasSize, IsStimulus {

    @Override
    public VisualStimulus getStimulus();

}
