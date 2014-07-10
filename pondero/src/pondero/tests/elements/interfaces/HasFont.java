package pondero.tests.elements.interfaces;

import java.awt.Font;

public interface HasFont {

    public Font _getFont();

    public void _setFont(final Font font);

    public int $fontheight();

    public void fontstyle(final String faceName);

    public void fontstyle(final String faceName, final int height);

    public void fontstyle(final String faceName, final int height, final boolean bold);

    public void fontstyle(final String faceName, final int height, final boolean bold, final boolean italic);

    public void fontstyle(final String faceName, final int height, final boolean bold, final boolean italic, final boolean underline);

    public void fontstyle(final String faceName, final int height, final boolean bold, final boolean italic, final boolean underline, final boolean strikeout);

}
