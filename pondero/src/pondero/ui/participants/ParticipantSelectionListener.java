package pondero.ui.participants;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public interface ParticipantSelectionListener extends ListSelectionListener {

    void valueChosen(ListSelectionEvent evt);

}
