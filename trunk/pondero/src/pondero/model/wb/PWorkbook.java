package pondero.model.wb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pondero.model.WorkbookListener;

public abstract class PWorkbook {

    private final String                           name;
    private final List<PSheet>                     sheets            = new ArrayList<PSheet>();
    private transient boolean                      dirty             = false;
    private transient final List<WorkbookListener> workbookListeners = new ArrayList<WorkbookListener>();
    private transient Map<String, Integer>         name2index        = null;

    public PWorkbook(final String name) {
        this.name = name;
    }

    public PSheet addSheet(final PSheet sheet) {
        name2index = null;
        sheets.add(sheet);
        setDirty(true);
        return sheet;
    }

    public void addWorkbookListener(final WorkbookListener listener) {
        workbookListeners.add(listener);
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

    void setDirty(final boolean flag) {
        if (dirty != flag) {
            dirty = flag;
            for (final WorkbookListener listener : workbookListeners) {
                listener.onDirtyFlagChanged(dirty);
            }
        }
    }

}
