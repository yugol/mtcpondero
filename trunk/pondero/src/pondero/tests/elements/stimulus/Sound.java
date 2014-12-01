package pondero.tests.elements.stimulus;

import pondero.task.stimuli.AuditoryStimulus;
import pondero.task.stimuli.SoundStimulus;
import pondero.tests.elements.Element;
import pondero.tests.elements.interfaces.HasItems;
import pondero.tests.elements.interfaces.IsAuditoryStimulus;
import pondero.tests.elements.other.Item;
import pondero.tests.staples.ElementUtil;
import pondero.tests.staples.ItemSelection;
import pondero.ui.exceptions.ExceptionReporting;

public class Sound extends Element implements HasItems, IsAuditoryStimulus {

    public static final String TYPENAME = "sound";

    private final Item         items;
    private ItemSelection      select   = ItemSelection.NOREPLACE;

    public Sound(final String name, final Item items) {
        super(name);
        this.items = items;
    }

    public Sound(final String name, final String... items) {
        this(name, new Item(null, items));
    }

    public String _nextItem() {
        if (ItemSelection.NOREPLACE == select) { return $item(ElementUtil.getRandomIndex($itemcount())); }
        return null;
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
    public AuditoryStimulus getStimulus() {
        final SoundStimulus stimulus = new SoundStimulus(this);
        try {
            stimulus.setPath(_nextItem());
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(null, e);
        }
        return stimulus;
    }

    @Override
    public String getTypeName() {
        return TYPENAME;
    }

    public void setSelectionMode(final ItemSelection select) {
        this.select = select;
    }

}
