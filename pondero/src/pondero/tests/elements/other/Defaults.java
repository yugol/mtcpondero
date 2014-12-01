package pondero.tests.elements.other;

import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;
import pondero.tests.elements.Element;
import pondero.tests.interfaces.HasBlockfeedback;
import pondero.tests.interfaces.HasFont;
import pondero.tests.interfaces.HasInputDevice;
import pondero.tests.interfaces.HasPosition;
import pondero.tests.interfaces.HasScreencolor;
import pondero.tests.interfaces.HasTextColors;
import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;
import pondero.tests.staples.ElementUtil;
import pondero.tests.staples.InputDevice;

public class Defaults extends Element implements HasBlockfeedback, HasFont, HasTextColors, HasScreencolor, HasPosition, HasInputDevice {

    public static final String       TYPENAME             = "defaults";

    private static final InputDevice DEFAULT_INPUT_DEVICE = InputDevice.KEYBOARD;

    private final Set<String>        blockfeedback        = new HashSet<String>();
    private Font                     font                 = ElementUtil.getDefaultFont();
    private InputDevice              inputDevice          = DEFAULT_INPUT_DEVICE;
    private Coordinates              position             = ElementUtil.getDefaultPosition();
    private Color                    screencolor          = ElementUtil.getDefaultScreenColor();
    private Color                    txbgcolor            = ElementUtil.getDefaultBackgroundColor();
    private Color                    txcolor              = ElementUtil.getDefaultForegroundColor();

    public Defaults() {
        super("");
        setBlockFeedback(MEANLATENCY, WINDOW, CORRECT);
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public InputDevice getInputDevice() {
        return inputDevice;
    }

    @Override
    public Coordinates getPosition() {
        return position;
    }

    @Override
    public Color getScreenColor() {
        return screencolor;
    }

    @Override
    public void setFont(final Font font) {
        this.font = font == null ? ElementUtil.getDefaultFont() : font;
    }

    @Override
    public void setPosition(final Coordinates position) {
        this.position = position == null ? ElementUtil.getDefaultPosition() : position;
    }

    @Override
    public void setScreenColor(final Color screencolor) {
        this.screencolor = screencolor == null ? ElementUtil.getDefaultScreenColor() : screencolor;
    }

    @Override
    public void setTextBgColor(final Color color) {
        txbgcolor = color == null ? ElementUtil.getDefaultBackgroundColor() : color;
    }

    @Override
    public void setTextColor(final Color color) {
        txcolor = color == null ? ElementUtil.getDefaultForegroundColor() : color;
    }

    @Override
    public int getFontHeight() {
        return font.getSize();
    }

    @Override
    public Coordinate getHPosition() {
        return getPosition().getX();
    }

    @Override
    public Color getTextBgColor() {
        return txbgcolor;
    }

    @Override
    public Color getTextColor() {
        return txcolor;
    }

    @Override
    public Coordinate getVPosition() {
        return getPosition().getY();
    }

    @Override
    public void setFontStyle(final String faceName) {
        setFontStyle(faceName, ElementUtil.getDefaultFont().getSize(), false, false, false, false);
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
    public Set<String> getBlockFeedback() {
        return blockfeedback;
    }

    @Override
    public String getTypeName() {
        return TYPENAME;
    }

    @Override
    public void setInputDevice(final InputDevice device) {
        inputDevice = device == null ? DEFAULT_INPUT_DEVICE : device;
    }

    @Override
    public void setPosition(final double x, final double y) {
        position = new Coordinates(x, y);
    }

    @Override
    public void setPosition(final String x, final String y) {
        position = new Coordinates(x, y);
    }

    @Override
    public void setScreenColor(final int r, final int g, final int b) {
        screencolor = ElementUtil.createColor(r, g, b);
    }

    @Override
    public void setBlockFeedback(final String... blockfeedback) {
        ElementUtil.fillBlockfeedback(this.blockfeedback, blockfeedback);
    }

    @Override
    public void setTextBgColor(final int r, final int g, final int b) {
        txbgcolor = ElementUtil.createColor(r, g, b);
    }

    @Override
    public void setTextColor(final int r, final int g, final int b) {
        txcolor = ElementUtil.createColor(r, g, b);
    }

}
