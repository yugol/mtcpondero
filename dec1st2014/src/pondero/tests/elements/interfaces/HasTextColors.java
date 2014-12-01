package pondero.tests.elements.interfaces;

import java.awt.Color;

public interface HasTextColors extends HasTextColor {

    public Color getTextBgColor();

    public void setTextBgColor(final Color color);

    public void setTextBgColor(final int r, final int g, final int b);

}
