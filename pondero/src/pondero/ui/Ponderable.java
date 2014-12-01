package pondero.ui;

import javax.swing.JFrame;
import pondero.data.Workbook;
import pondero.data.model.basic.Participant;
import pondero.tests.Test;

public interface Ponderable {

    Participant getCurrentParticipant();

    Test getCurrentTask();

    Workbook getCurrentWorkbook();

    JFrame getMainFrame();

    JFrame getTestFrame();

    void setCurrentParticipant(Participant participant) throws Exception;

    void setCurrentState(PonderoState state) throws Exception;

    void setCurrentTask(Test task) throws Exception;

    void setCurrentWorkbook(Workbook workbook) throws Exception;

    void updateCurrentState() throws Exception;

}
