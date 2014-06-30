package pondero.engine.elements.other;

import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;
import pondero.engine.elements.Element;
import pondero.engine.elements.interfaces.HasBlockfeedback;
import pondero.engine.elements.interfaces.HasFont;
import pondero.engine.elements.interfaces.HasInputDevice;
import pondero.engine.elements.interfaces.HasPosition;
import pondero.engine.elements.interfaces.HasScreencolor;
import pondero.engine.elements.interfaces.HasTextColors;
import pondero.engine.staples.Coordinate;
import pondero.engine.staples.Coordinates;
import pondero.engine.staples.ElementUtil;
import pondero.engine.staples.InputDevice;

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
        blockfeedback(MEANLATENCY, WINDOW, CORRECT);
    }

    @Override
    public Set<String> _getBlockfeedback() {
        return blockfeedback;
    }

    @Override
    public Font _getFont() {
        return font;
    }

    @Override
    public InputDevice _getInputDevice() {
        return inputDevice;
    }

    @Override
    public Coordinates _getPosition() {
        return position;
    }

    @Override
    public Color _getScreencolor() {
        return screencolor;
    }

    @Override
    public void _setFont(final Font font) {
        this.font = font == null ? ElementUtil.getDefaultFont() : font;
    }

    @Override
    public void _setPosition(final Coordinates position) {
        this.position = position == null ? ElementUtil.getDefaultPosition() : position;
    }

    @Override
    public void _setScreencolor(final Color screencolor) {
        this.screencolor = screencolor == null ? ElementUtil.getDefaultScreenColor() : screencolor;
    }

    @Override
    public void _setTxbgcolor(final Color color) {
        txbgcolor = color == null ? ElementUtil.getDefaultBackgroundColor() : color;
    }

    @Override
    public void _setTxcolor(final Color color) {
        txcolor = color == null ? ElementUtil.getDefaultForegroundColor() : color;
    }

    @Override
    public int $fontheight() {
        return font.getSize();
    }

    @Override
    public Coordinate $hposition() {
        return _getPosition().getX();
    }

    @Override
    public Color $textbgcolor() {
        return txbgcolor;
    }

    @Override
    public int $textbgcolorblue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int $textbgcolorgreen() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int $textbgcolorred() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Color $textcolor() {
        return txcolor;
    }

    @Override
    public int $textcolorblue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int $textcolorgreen() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int $textcolorred() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String $typename() {
        return TYPENAME;
    }

    @Override
    public Coordinate $vposition() {
        return _getPosition().getY();
    }

    @Override
    public void blockfeedback(final String... blockfeedback) {
        ElementUtil.fillBlockfeedback(this.blockfeedback, blockfeedback);
    }

    @Override
    public void fontstyle(final String faceName) {
        fontstyle(faceName, ElementUtil.getDefaultFont().getSize(), false, false, false, false);
    }

    @Override
    public void fontstyle(final String faceName, final int height) {
        fontstyle(faceName, height, false, false, false, false);
    }

    @Override
    public void fontstyle(final String faceName, final int height, final boolean bold) {
        fontstyle(faceName, height, bold, false, false, false);
    }

    @Override
    public void fontstyle(final String faceName, final int height, final boolean bold, final boolean italic) {
        fontstyle(faceName, height, bold, italic, false, false);
    }

    @Override
    public void fontstyle(final String faceName, final int height, final boolean bold, final boolean italic, final boolean underline) {
        fontstyle(faceName, height, bold, italic, underline, false);
    }

    @Override
    public void fontstyle(final String faceName, final int height, final boolean bold, final boolean italic, final boolean underline, final boolean strikeout) {
        font = ElementUtil.createFont(faceName, height, bold, italic, underline, strikeout);
    }

    @Override
    public void inputdevice(final InputDevice device) {
        inputDevice = device == null ? DEFAULT_INPUT_DEVICE : device;
    }

    @Override
    public void position(final double x, final double y) {
        position = new Coordinates(x, y);
    }

    @Override
    public void position(final String x, final String y) {
        position = new Coordinates(x, y);
    }

    @Override
    public void screencolor(final int r, final int g, final int b) {
        screencolor = ElementUtil.createColor(r, g, b);
    }

    @Override
    public void txbgcolor(final int r, final int g, final int b) {
        txbgcolor = ElementUtil.createColor(r, g, b);
    }

    @Override
    public void txcolor(final int r, final int g, final int b) {
        txcolor = ElementUtil.createColor(r, g, b);
    }

}
