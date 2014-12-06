package pondero.tests.interfaces;

import pondero.tests.elements.other.Page;

public interface HasPostInstructions {

    void setPostInstructions(Page... pages);

    void setPostInstructions(String... pages);

}
