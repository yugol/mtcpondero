package pondero.ui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public interface DialogSelectionListener extends ListSelectionListener {

    void valueChosen(ListSelectionEvent evt);

}
