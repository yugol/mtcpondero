package pondero.tests.elements.interfaces;

import java.awt.Font;

public interface HasFont {

    public Font getFont();

    public void setFont(final Font font);

    public int getFontHeight();

    public void setFontStyle(final String faceName);

    public void setFontStyle(final String faceName, final int height);

    public void setFontStyle(final String faceName, final int height, final boolean bold);

    public void setFontStyle(final String faceName, final int height, final boolean bold, final boolean italic);

    public void setFontStyle(final String faceName, final int height, final boolean bold, final boolean italic, final boolean underline);

    public void setFontStyle(final String faceName, final int height, final boolean bold, final boolean italic, final boolean underline, final boolean strikeout);

}
