package pondero.tests.interfaces;

import pondero.tests.elements.other.Page;

public interface HasPreInstructions {

    void setPreInstructions(Page... pages);

    void setPreInstructions(String... pages);

}
