package pondero.tests.elements.interfaces;

import pondero.task.stimuli.AuditoryStimulus;

public interface IsAuditoryStimulus extends IsStimulus {

    @Override
    public AuditoryStimulus getStimulus();

}
