package pondero.tests.interfaces;

import pondero.task.stimuli.AuditoryStimulus;

public interface IsAuditoryStimulus extends IsStimulus {

    @Override
    public AuditoryStimulus getStimulus();

}
