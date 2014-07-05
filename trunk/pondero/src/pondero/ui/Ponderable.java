package pondero.ui;

import javax.swing.JFrame;
import pondero.engine.test.Test;
import pondero.model.Workbook;
import pondero.model.participants.Participant;

public interface Ponderable {

    public abstract Participant getCurrentParticipant();

    public abstract Test getCurrentTask();

    public abstract Workbook getCurrentWorkbook();

    public abstract JFrame getFrame();

    public abstract void setCurrentParticipant(Participant participant);

    public abstract void setCurrentState(PonderoState state);

    public abstract void setCurrentTask(Test task);

    public abstract void setCurrentWorkbook(Workbook workbook);

    public abstract void updateCurrentState();

}