package pondero.engine.elements.stimulus;

import java.awt.Color;
import java.awt.Font;
import pondero.engine.elements.Element;
import pondero.engine.elements.interfaces.HasErase;
import pondero.engine.elements.interfaces.HasFont;
import pondero.engine.elements.interfaces.HasItems;
import pondero.engine.elements.interfaces.HasTextColors;
import pondero.engine.elements.interfaces.IsVisualStimulus;
import pondero.engine.elements.other.Item;
import pondero.engine.staples.Coordinate;
import pondero.engine.staples.Coordinates;
import pondero.engine.staples.ElementUtil;
import pondero.engine.staples.ItemSelection;
import pondero.engine.test.stimuli.TextStimulus;

public class Text extends Element implements HasErase, HasFont, HasItems, HasTextColors, IsVisualStimulus {

    public static final String TYPENAME   = "text";

    private boolean            eraseFlag  = true;
    private Color              eraseColor = null;
    private Font               font       = null;
    private final Item         items;
    private Coordinates        position   = null;
    private ItemSelection      select     = ItemSelection.NOREPLACE;
    private Coordinates        size       = null;
    private Color              txbgcolor  = null;
    private Color              txcolor    = null;

    public Text(final String name, final Item items) {
        super(name);
        this.items = items;
    }

    public Text(final String name, final String... items) {
        this(name, new Item(null, items));
    }

    @Override
    public Font _getFont() {
        return font == null ? test.getDefaults()._getFont() : font;
    }

    @Override
    public Coordinates _getPosition() {
        return position == null ? test.getDefaults()._getPosition() : position;
    }

    @Override
    public Coordinates _getSize() {
        return size == null ? test.getTextSize(this) : size;
    }

    @Override
    public TextStimulus _getStimulus() {
        final TextStimulus stimulus = new TextStimulus(this);
        stimulus.setBgColor($textbgcolor());
        stimulus.setFgColor($textcolor());
        stimulus.setFont(_getFont());
        stimulus.setPosition(_getPosition());
        stimulus.setSize(size);
        stimulus.setText(_nextItem());
        return stimulus;
    }

    public String _nextItem() {
        if (ItemSelection.NOREPLACE == select) { return $item(ElementUtil.getRandomIndex($itemcount())); }
        return null;
    }

    @Override
    public void _setErase(final Color color) {
        eraseColor = color;
        erase(true);
    }

    @Override
    public void _setFont(final Font font) {
        this.font = font;
    }

    @Override
    public void _setPosition(final Coordinates position) {
        this.position = position;
    }

    @Override
    public void _setSize(final Coordinates size) {
        this.size = size;
    }

    @Override
    public void _setTxbgcolor(final Color color) {
        txbgcolor = color;
    }

    @Override
    public void _setTxcolor(final Color color) {
        txcolor = color;
    }

    public void $appenditem(final String item) {
        items._add(item);
    }

    @Override
    public void $clearitems() {
        items.$clearitems();
    }

    @Override
    public boolean $erase() {
        return eraseFlag;
    }

    @Override
    public Color $erasecolor() {
        if (!eraseFlag) { return null; }
        if (eraseColor != null) { return eraseColor; }
        return test.getScreencolor();
    }

    @Override
    public int $erasecolorblue() {
        return $erasecolor().getBlue();
    }

    @Override
    public int $erasecolorgreen() {
        return $erasecolor().getGreen();
    }

    @Override
    public int $erasecolorred() {
        return $erasecolor().getRed();
    }

    @Override
    public int $fontheight() {
        return _getFont().getSize();
    }

    @Override
    public Coordinate $height() {
        return _getSize().getY();
    }

    @Override
    public Coordinate $hposition() {
        return _getPosition().getX();
    }

    @Override
    public void $insertitem(final String value, final int index) {
        items.$insertitem(value, index);
    }

    @Override
    public String $item(final int index) {
        return items.$item(index);
    }

    @Override
    public int $itemcount() {
        return items.$itemcount();
    }

    @Override
    public String[] $items() {
        return items.$items();
    }

    @Override
    public void $removeitem(final int index) {
        items.$removeitem(index);
    }

    @Override
    public void $settitem(final String value, final int index) {
        items.$settitem(value, index);
    }

    @Override
    public Color $textbgcolor() {
        return txbgcolor == null ? test.getDefaults().$textbgcolor() : txbgcolor;
    }

    @Override
    public int $textbgcolorblue() {
        return $textbgcolor().getBlue();
    }

    @Override
    public int $textbgcolorgreen() {
        return $textbgcolor().getGreen();
    }

    @Override
    public int $textbgcolorred() {
        return $textbgcolor().getRed();
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
    public Coordinate $vposition() {
        return _getPosition().getY();
    }

    @Override
    public Coordinate $width() {
        return _getSize().getY();
    }

    @Override
    public void erase(final boolean flag) {
        eraseFlag = flag;
    }

    @Override
    public void erase(final int r, final int g, final int b) {
        _setErase(ElementUtil.createColor(r, g, b));
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
    public void height(final double height) {
        size = _getSize();
        size.setY(height);
    }

    @Override
    public void height(final String height) {
        size = _getSize();
        size.setY(height);
    }

    @Override
    public void position(final double x, final double y) {
        position = new Coordinates(x, y);
    }

    @Override
    public void position(final String xExpr, final String yExpr) {
        position = new Coordinates(xExpr, yExpr);
    }

    public void select(final ItemSelection select) {
        this.select = select;
    }

    @Override
    public void size(final double width, final double height) {
        size = new Coordinates(width, height);
    }

    @Override
    public void size(final String width, final String height) {
        size = new Coordinates(width, height);
    }

    @Override
    public void txbgcolor(final int r, final int g, final int b) {
        _setTxbgcolor(ElementUtil.createColor(r, g, b));
    }

    @Override
    public void txcolor(final int r, final int g, final int b) {
        _setTxcolor(ElementUtil.createColor(r, g, b));
    }

    @Override
    public void width(final double width) {
        size = _getSize();
        size.setY(width);
    }

    @Override
    public void width(final String width) {
        size = _getSize();
        size.setY(width);
    }

}
