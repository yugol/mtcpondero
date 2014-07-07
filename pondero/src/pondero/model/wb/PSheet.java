package pondero.model.wb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PSheet {

    private final PWorkbook                workbook;
    private final String                   name;
    private final List<PColumn>            columns    = new ArrayList<PColumn>();
    private final List<PRow>               rows       = new ArrayList<PRow>();
    private transient Map<String, Integer> name2index = null;
    private boolean                        locked     = false;

    public PSheet(final PWorkbook workbook, final String name) {
        this.workbook = workbook;
        this.name = name;
    }

    public PColumn addColumn(final PColumn column) {
        if (!locked) {
            name2index = null;
            columns.add(column);
            return column;
        }
        throw new IndexOutOfBoundsException("sheet is locked for adding/removing columns");
    }

    public PColumn addColumn(final String name) {
        return addColumn(new PColumn(this, name));
    }

    public PRow addRow(final PRow row) {
        lock();
        rows.add(row);
        return row;
    }

    public PColumn getColumn(final int index) {
        return columns.get(index);
    }

    public int getColumnCount() {
        return columns.size();
    }

    public String getName() {
        return name;
    }

    public PColumn getSheet(final String name) {
        return getColumn(name2index(name));
    }

    public PWorkbook getWorkbook() {
        return workbook;
    }

    public Iterator<PColumn> itera1torColumns() {
        return columns.iterator();
    }

    private int name2index(final String name) {
        if (name2index == null) {
            name2index = new HashMap<String, Integer>();
            for (int i = 0; i < columns.size(); ++i) {
                name2index.put(columns.get(i).getName(), i);
            }
        }
        return name2index.get(name);
    }

    protected void lock() {
        locked = true;
    }

}
