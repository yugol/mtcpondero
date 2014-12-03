package pondero.tests.elements.stimulus;

import java.awt.Color;
import java.awt.Font;
import pondero.task.stimuli.TextStimulus;
import pondero.tests.elements.Element;
import pondero.tests.elements.other.Item;
import pondero.tests.interfaces.HasErase;
import pondero.tests.interfaces.HasFont;
import pondero.tests.interfaces.HasItems;
import pondero.tests.interfaces.HasTextColors;
import pondero.tests.interfaces.IsVisualStimulus;
import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;
import pondero.tests.staples.ElementUtil;
import pondero.tests.staples.ItemSelection;

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

    public String _nextItem() {
        if (ItemSelection.NOREPLACE == select) { return $item(ElementUtil.getRandomIndex($itemcount())); }
        return null;
    }

    public void $appenditem(final String item) {
        items._add(item);
    }

    @Override
    public void $clearitems() {
        items.$clearitems();
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
    public Color getEraseColor() {
        if (!eraseFlag) { return null; }
        return eraseColor;
    }

    @Override
    public Font getFont() {
        return font == null ? test.getDefaults().getFont() : font;
    }

    @Override
    public int getFontHeight() {
        return getFont().getSize();
    }

    @Override
    public Coordinate getHeight() {
        return getSize().getY();
    }

    @Override
    public Coordinate getHPosition() {
        return getPosition().getX();
    }

    @Override
    public Coordinates getPosition() {
        return position == null ? test.getDefaults().getPosition() : position;
    }

    @Override
    public Coordinates getSize() {
        return size;
    }

    @Override
    public TextStimulus getStimulus() {
        final TextStimulus stimulus = new TextStimulus(this);
        stimulus.setBgColor(getTextBgColor());
        stimulus.setFgColor(getTextColor());
        stimulus.setFont(getFont());
        stimulus.setPosition(getPosition());
        stimulus.setSize(size);
        stimulus.setText(_nextItem());
        return stimulus;
    }

    @Override
    public Color getTextBgColor() {
        return txbgcolor == null ? test.getDefaults().getTextBgColor() : txbgcolor;
    }

    @Override
    public Color getTextColor() {
        return txcolor == null ? test.getDefaults().getTextColor() : txcolor;
    }

    @Override
    public String getTypeName() {
        return TYPENAME;
    }

    @Override
    public Coordinate getVPosition() {
        return getPosition().getY();
    }

    @Override
    public Coordinate getWidth() {
        return getSize().getY();
    }

    @Override
    public boolean isErase() {
        return eraseFlag;
    }

    public void select(final ItemSelection select) {
        this.select = select;
    }

    @Override
    public void setErase(final boolean flag) {
        eraseFlag = flag;
    }

    @Override
    public void setErase(final int r, final int g, final int b) {
        setEraseColor(ElementUtil.createColor(r, g, b));
    }

    @Override
    public void setEraseColor(final Color color) {
        eraseColor = color;
        setErase(true);
    }

    @Override
    public void setFont(final Font font) {
        this.font = font;
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
    public void setHeight(final double height) {
        size = getSize();
        size.setY(height);
    }

    @Override
    public void setHeight(final String height) {
        size = getSize();
        size.setY(height);
    }

    @Override
    public void setPosition(final Coordinates position) {
        this.position = position;
    }

    @Override
    public void setPosition(final double x, final double y) {
        position = new Coordinates(x, y);
    }

    @Override
    public void setPosition(final String xExpr, final String yExpr) {
        position = new Coordinates(xExpr, yExpr);
    }

    @Override
    public void setSize(final Coordinates size) {
        this.size = size;
    }

    @Override
    public void setSize(final double width, final double height) {
        size = new Coordinates(width, height);
    }

    @Override
    public void setSize(final String width, final String height) {
        size = new Coordinates(width, height);
    }

    @Override
    public void setTextBgColor(final Color color) {
        txbgcolor = color;
    }

    @Override
    public void setTextBgColor(final int r, final int g, final int b) {
        setTextBgColor(ElementUtil.createColor(r, g, b));
    }

    @Override
    public void setTextColor(final Color color) {
        txcolor = color;
    }

    @Override
    public void setTextColor(final int r, final int g, final int b) {
        setTextColor(ElementUtil.createColor(r, g, b));
    }

    @Override
    public void setWidth(final double width) {
        size = getSize();
        size.setY(width);
    }

    @Override
    public void setWidth(final String width) {
        size = getSize();
        size.setY(width);
    }

}
