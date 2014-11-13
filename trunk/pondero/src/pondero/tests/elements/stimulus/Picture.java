package pondero.tests.elements.stimulus;

import java.awt.Color;
import java.io.IOException;
import pondero.tests.elements.Element;
import pondero.tests.elements.interfaces.HasErase;
import pondero.tests.elements.interfaces.HasItems;
import pondero.tests.elements.interfaces.IsVisualStimulus;
import pondero.tests.elements.other.Item;
import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;
import pondero.tests.staples.ElementUtil;
import pondero.tests.staples.ItemSelection;
import pondero.tests.test.stimuli.PictureStimulus;
import pondero.ui.exceptions.ExceptionReporting;

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
    public Coordinates getPosition() {
        return position == null ? test.getDefaults().getPosition() : position;
    }

    @Override
    public Coordinates getSize() {
        return size;
    }

    @Override
    public PictureStimulus getStimulus() {
        final PictureStimulus stimulus = new PictureStimulus(this);
        try {
            stimulus.setPosition(getPosition());
            stimulus.setSize(getSize());
            stimulus.setPath(_nextItem());
        } catch (final IOException e) {
            ExceptionReporting.showExceptionMessage(null, e);
        }
        return stimulus;
    }

    public String _nextItem() {
        if (ItemSelection.NOREPLACE == select) { return $item(ElementUtil.getRandomIndex($itemcount())); }
        return null;
    }

    @Override
    public void setPosition(final Coordinates position) {
        this.position = position;
    }

    @Override
    public void setSize(final Coordinates size) {
        this.size = size;
    }

    @Override
    public void $clearitems() {
        items.$clearitems();
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
    public Color getEraseColor() {
        if (!eraseFlag) { return null; }
        if (eraseColor != null) { return eraseColor; }
        return test.getScreenColor();
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
    public boolean isErase() {
        return eraseFlag;
    }

    @Override
    public void setPosition(final double x, final double y) {
        position = new Coordinates(x, y);
    }

    @Override
    public void setPosition(final String xExpr, final String yExpr) {
        position = new Coordinates(xExpr, yExpr);
    }

    public void setSelectionMode(final ItemSelection select) {
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
    public void setSize(final double width, final double height) {
        size = new Coordinates(width, height);
    }

    @Override
    public void setSize(final String width, final String height) {
        size = new Coordinates(width, height);
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
