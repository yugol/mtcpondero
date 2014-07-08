package pondero.ui;

import javax.swing.JFrame;
import pondero.engine.test.Test;
import pondero.model.Workbook;
import pondero.model.participants.Participant;

public interface Ponderable {

    Participant getCurrentParticipant();

    Test getCurrentTask();

    Workbook getCurrentWorkbook();

    JFrame getFrame();

    void setCurrentParticipant(Participant participant);

    void setCurrentState(PonderoState state);

    void setCurrentTask(Test task);

    void setCurrentWorkbook(Workbook workbook);

    void updateCurrentState();

}
