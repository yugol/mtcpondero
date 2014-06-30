package pondero.engine.elements.other;

import java.util.ArrayList;
import java.util.List;
import pondero.engine.elements.Element;
import pondero.engine.elements.interfaces.HasItems;

public class Item extends Element implements HasItems {

    public static final String TYPENAME = "item";

    private final List<String> original = new ArrayList<String>();
    private final List<String> items    = new ArrayList<String>();

    public Item(final String name, final String... items) {
        super(name);
        for (final String item : items) {
            original.add(item);
            this.items.add(item);
        }
    }

    public void _add(final String item) {
        items.add(item);
    }

    @Override
    public void $clearitems() {
        items.clear();
    }

    @Override
    public void $insertitem(final String value, final int index) {
        items.add(index - 1, value);
    }

    @Override
    public String $item(final int index) {
        return items.get(index - 1);
    }

    @Override
    public int $itemcount() {
        return items.size();
    }

    @Override
    public String[] $items() {
        return items.toArray(new String[0]);
    }

    @Override
    public void $removeitem(final int index) {
        items.remove(index - 1);
    }

    @Override
    public void $settitem(final String value, final int index) {
        items.set(index - 1, value);
    }

    @Override
    public String $typename() {
        return TYPENAME;
    }

    public void reset() {
        items.clear();
        items.addAll(original);
    }
}
