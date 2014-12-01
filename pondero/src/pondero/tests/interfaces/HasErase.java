package pondero.tests.interfaces;

import java.awt.Color;

public interface HasErase {

    Color getEraseColor();

    boolean isErase();

    void setErase(final boolean flag);

    void setErase(final int r, final int g, final int b);

    void setEraseColor(final Color color);

}
