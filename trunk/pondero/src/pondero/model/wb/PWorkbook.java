package pondero.model.wb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PWorkbook {

    private final String                   name;
    private final List<PSheet>             sheets     = new ArrayList<PSheet>();
    private transient Map<String, Integer> name2index = null;

    public PWorkbook(final String name) {
        this.name = name;
    }

    public PSheet addSheet(final PSheet sheet) {
        name2index = null;
        sheets.add(sheet);
        return sheet;
    }

    public PSheet addSheet(final String name) {
        return addSheet(new PSheet(this, name));
    }

    public String getName() {
        return name;
    }

    public PSheet getSheet(final int index) {
        return sheets.get(index);
    }

    public PSheet getSheet(final String name) {
        return getSheet(name2index(name));
    }

    public int getSheetCount() {
        return sheets.size();
    }

    public Iterator<PSheet> iteratorSheets() {
        return sheets.iterator();
    }

    private int name2index(final String name) {
        if (name2index == null) {
            name2index = new HashMap<String, Integer>();
            for (int i = 0; i < sheets.size(); ++i) {
                name2index.put(sheets.get(i).getName(), i);
            }
        }
        return name2index.get(name);
    }

}
