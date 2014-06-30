package pondero.engine.elements.interfaces;

import java.awt.Color;

public interface HasErase {

    public void _setErase(final Color color);

    public boolean $erase();

    public Color $erasecolor();

    public int $erasecolorblue();

    public int $erasecolorgreen();

    public int $erasecolorred();

    public void erase(final boolean flag);

    public void erase(final int r, final int g, final int b);

}
