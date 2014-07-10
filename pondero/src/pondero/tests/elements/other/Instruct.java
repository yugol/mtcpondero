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
    public Font _getFont() {
        return font == null ? test.getDefaults()._getFont() : font;
    }

    @Override
    public InputDevice _getInputDevice() {
        return inputDevice == null ? test.getDefaults()._getInputDevice() : inputDevice;
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
    public void _setFont(final Font font) {
        this.font = font;
    }

    public void _setScreencolor(final Color screencolor) {
        this.screencolor = screencolor;
    }

    @Override
    public void _setTxcolor(final Color color) {
        txcolor = color;
    }

    @Override
    public int $fontheight() {
        return _getFont().getSize();
    }

    @Override
    public Color $textcolor() {
        return txcolor == null ? test.getDefaults().$textcolor() : txcolor;
    }

    @Override
    public int $textcolorblue() {
        return $textcolor().getBlue();
    }

    @Override
    public int $textcolorgreen() {
        return $textcolor().getGreen();
    }

    @Override
    public int $textcolorred() {
        return $textcolor().getRed();
    }

    @Override
    public String $typename() {
        return TYPENAME;
    }

    @Override
    public void fontstyle(final String faceName) {
        fontstyle(faceName, test.getDefaults().$fontheight(), false, false, false, false);
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
    public void txcolor(final int r, final int g, final int b) {
        _setTxcolor(ElementUtil.createColor(r, g, b));
    }

}
