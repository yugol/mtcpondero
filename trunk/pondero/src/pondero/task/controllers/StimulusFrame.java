package pondero.task.controllers;

import java.util.ArrayList;
import java.util.List;
import pondero.task.stimuli.AuditoryStimulus;
import pondero.task.stimuli.Stimulus;
import pondero.task.stimuli.VisualStimulus;

public class StimulusFrame {

    private final long                   timeIndex;
    private final List<VisualStimulus>   visualStimuli   = new ArrayList<>();
    private final List<AuditoryStimulus> auditoryStimuli = new ArrayList<>();

    public StimulusFrame(final long timeIndex) {
        this.timeIndex = timeIndex;
    }

    public void addStimulus(final Stimulus stimulus) {
        if (stimulus instanceof VisualStimulus) {
            visualStimuli.add((VisualStimulus) stimulus);
        } else if (stimulus instanceof AuditoryStimulus) {
            auditoryStimuli.add((AuditoryStimulus) stimulus);
        }
    }

    public List<AuditoryStimulus> getAuditoryStimuli() {
        return auditoryStimuli;
    }

    public long getTimeIndex() {
        return timeIndex;
    }

    public List<VisualStimulus> getVisualStimuli() {
        return visualStimuli;
    }

}
