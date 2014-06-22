package pondero.engine.elements.stimulus;

import java.awt.Color;
import java.io.IOException;
import pondero.engine.elements.Element;
import pondero.engine.elements.interfaces.HasErase;
import pondero.engine.elements.interfaces.HasItems;
import pondero.engine.elements.interfaces.IsVisualStimulus;
import pondero.engine.elements.other.Item;
import pondero.engine.staples.Coordinate;
import pondero.engine.staples.Coordinates;
import pondero.engine.staples.ElementUtil;
import pondero.engine.staples.ItemSelection;
import pondero.engine.test.stimuli.PictureStimulus;

public class Picture extends Element implements HasErase, HasItems, IsVisualStimulus {

    public static final String TYPENAME   = "picture";

    private Color              eraseColor = null;
    private Coordinates        position   = null;
    private Coordinates        size       = null;
    private ItemSelection      select     = ItemSelection.NOREPLACE;
    private boolean            eraseFlag  = true;
    private final Item         items;

    public Picture(final String name, final Item items) {
        super(name);
        this.items = items;
    }

    public Picture(final String name, final String... items) {
        this(name, new Item(null, items));
    }

    @Override
    public Coordinates _getPosition() {
        return position == null ? test.getDefaults()._getPosition() : position;
    }

    @Override
    public Coordinates _getSize() {
        return size;
    }

    @Override
    public PictureStimulus _getStimulus() {
        final PictureStimulus stimulus = new PictureStimulus(this);
        try {
            stimulus.setPosition(_getPosition());
            stimulus.setSize(_getSize());
            stimulus.setPath(_nextItem());
        } catch (final IOException e) {
            e.printStackTrace();
        }
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
    public void _setPosition(final Coordinates position) {
        this.position = position;
    }

    @Override
    public void _setSize(final Coordinates size) {
        this.size = size;
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
