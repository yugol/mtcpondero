package pondero.engine.elements.interfaces;

import java.awt.Color;

public interface HasErase {

    void _setErase(final Color color);

    boolean $erase();

    Color $erasecolor();

    int $erasecolorblue();

    int $erasecolorgreen();

    int $erasecolorred();

    void erase(final boolean flag);

    void erase(final int r, final int g, final int b);

}
