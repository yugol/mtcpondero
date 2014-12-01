package pondero.tests.interfaces;

import java.awt.Color;

public interface HasScreencolor {

    public Color getScreenColor();

    public void setScreenColor(Color screencolor);

    public void setScreenColor(final int r, final int g, final int b);

}
