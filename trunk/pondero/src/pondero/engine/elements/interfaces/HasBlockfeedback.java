package pondero.engine.elements.interfaces;

import java.util.Set;

public interface HasBlockfeedback {

    String CORRECT       = "correct";
    String LATENCY       = "latency";
    String MEANLATENCY   = "meanlatency";
    String MEDIANLATENCY = "medianlatency";
    String WINDOW        = "window";

    Set<String> _getBlockfeedback();

    void blockfeedback(String... blockfeedback);

}
