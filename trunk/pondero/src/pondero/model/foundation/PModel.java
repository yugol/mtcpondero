package pondero.model.foundation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import pondero.model.PModelListener;

public abstract class PModel implements Iterable<PSheet> {

    private final String                        name;
    private final List<PSheet>                  sheets     = new ArrayList<PSheet>();
    private transient boolean                   dirty      = false;
    private transient final List<PModelListener> listeners  = new ArrayList<PModelListener>();
    private transient Map<String, Integer>      name2index = null;

    public PModel(final String name) {
        this.name = name;
    }

    public void addModelListener(final PModelListener listener) {
        listeners.add(listener);
    }

    public PSheet addSheet(final PSheet sheet) throws Exception {
        name2index = null;
        sheets.add(sheet);
        setDirty(true);
        return sheet;
    }

    public String getName() {
        return name;
    }

    public PSheet getSheet(final Integer index) {
        if (index == null) { return null; }
        return sheets.get(index);
    }

    public PSheet getSheet(final String name) {
        return getSheet(name2index(name));
    }

    public int getSheetCount() {
        return sheets.size();
    }

    public boolean isDirty() {
        return dirty;
    }

    @Override
    public Iterator<PSheet> iterator() {
        return sheets.iterator();
    }

    public void setDirty(final boolean flag) throws Exception {
        if (dirty != flag) {
            dirty = flag;
            for (final PModelListener listener : listeners) {
                listener.onDirtyFlagChanged(dirty);
            }
        }
    }

    @Override
    public String toString() {
        return getName();
    }

    private Integer name2index(final String name) {
        if (name2index == null) {
            name2index = new HashMap<String, Integer>();
            for (int i = 0; i < sheets.size(); ++i) {
                name2index.put(sheets.get(i).getName(), i);
            }
        }
        return name2index.get(name);
    }

}
