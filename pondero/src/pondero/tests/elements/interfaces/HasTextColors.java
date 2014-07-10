package pondero.tests.elements.interfaces;

import java.awt.Color;

public interface HasTextColors extends HasTextColor {

    public void _setTxbgcolor(final Color color);

    public Color $textbgcolor();

    public int $textbgcolorblue();

    public int $textbgcolorgreen();

    public int $textbgcolorred();

    public void txbgcolor(final int r, final int g, final int b);

}
