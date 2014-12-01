package pondero.tests.elements.interfaces;

import pondero.tests.test.stimuli.AuditoryStimulus;

public interface IsAuditoryStimulus extends IsStimulus {

    @Override
    public AuditoryStimulus getStimulus();

}
