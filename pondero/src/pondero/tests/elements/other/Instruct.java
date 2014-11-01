package pondero.tests.elements.other;

import java.awt.Color;
import java.awt.Font;
import pondero.tests.elements.Element;
import pondero.tests.elements.interfaces.HasFont;
import pondero.tests.elements.interfaces.HasInputDevice;
import pondero.tests.elements.interfaces.HasTextColor;
import pondero.tests.staples.ElementUtil;
import pondero.tests.staples.InputDevice;

public class Instruct extends Element implements HasFont, HasTextColor, HasInputDevice {

    public static final String TYPENAME    = "instruct";

    private Font               font        = null;
    private InputDevice        inputDevice = null;
    private Character          nextkey     = ' ';
    private Character          prevkey     = null;
    private Color              screencolor = null;
    private Color              txcolor     = null;

    public Instruct() {
        super("");
    }

    @Override
    public Font getFont() {
        return font == null ? test.getDefaults().getFont() : font;
    }

    @Override
    public InputDevice getInputDevice() {
        return inputDevice == null ? test.getDefaults().getInputDevice() : inputDevice;
    }

    public Character _getNextkey() {
        return nextkey;
    }

    public Character _getPrevkey() {
        return prevkey;
    }

    public Color _getScreencolor() {
        return screencolor == null ? test.getScreencolor() : screencolor;
    }

    @Override
    public void setFont(final Font font) {
        this.font = font;
    }

    public void _setScreencolor(final Color screencolor) {
        this.screencolor = screencolor;
    }

    @Override
    public void setTextColor(final Color color) {
        txcolor = color;
    }

    @Override
    public int getFontHeight() {
        return getFont().getSize();
    }

    @Override
    public Color getTextColor() {
        return txcolor == null ? test.getDefaults().getTextColor() : txcolor;
    }

    @Override
    public void setFontStyle(final String faceName) {
        setFontStyle(faceName, test.getDefaults().getFontHeight(), false, false, false, false);
    }

    @Override
    public void setFontStyle(final String faceName, final int height) {
        setFontStyle(faceName, height, false, false, false, false);
    }

    @Override
    public void setFontStyle(final String faceName, final int height, final boolean bold) {
        setFontStyle(faceName, height, bold, false, false, false);
    }

    @Override
    public void setFontStyle(final String faceName, final int height, final boolean bold, final boolean italic) {
        setFontStyle(faceName, height, bold, italic, false, false);
    }

    @Override
    public void setFontStyle(final String faceName, final int height, final boolean bold, final boolean italic, final boolean underline) {
        setFontStyle(faceName, height, bold, italic, underline, false);
    }

    @Override
    public void setFontStyle(final String faceName, final int height, final boolean bold, final boolean italic, final boolean underline, final boolean strikeout) {
        font = ElementUtil.createFont(faceName, height, bold, italic, underline, strikeout);
    }

    @Override
    public String getTypeName() {
        return TYPENAME;
    }

    @Override
    public void setInputDevice(final InputDevice device) {
        inputDevice = device;
    }

    public void nextkey(final Character nextkey) {
        this.nextkey = nextkey;
    }

    public void prevkey(final Character prevkey) {
        this.prevkey = prevkey;
    }

    public void screencolor(final int r, final int g, final int b) {
        screencolor = ElementUtil.createColor(r, g, b);
    }

    @Override
    public void setTextColor(final int r, final int g, final int b) {
        setTextColor(ElementUtil.createColor(r, g, b));
    }

}
