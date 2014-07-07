package pondero.model.wb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pondero.util.StringUtil;

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
            setDirty(true);
            return column;
        }
        throw new IndexOutOfBoundsException("sheet is locked for adding/removing columns");
    }

    public PColumn addColumn(final String name) {
        return addColumn(name, PType.ANY);
    }

    public PColumn addColumn(final String name, final PType type) {
        return addColumn(new PColumn(this, columns.size(), name, type));
    }

    public Object get(final int rowIdx, final int colIdx) {
        return rows.get(rowIdx).get(colIdx);
    }

    public Object get(final int rowIdx, final String colId) {
        return rows.get(rowIdx).get(index(colId));
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

    public PRow getRow(final int index) {
        return rows.get(index);
    }

    public int getRowCount() {
        return rows.size();
    }

    public PColumn getSheet(final String name) {
        return getColumn(index(name));
    }

    public PWorkbook getWorkbook() {
        return workbook;
    }

    public int index(final String name) {
        if (name2index == null) {
            name2index = new HashMap<String, Integer>();
            for (int i = 0; i < columns.size(); ++i) {
                name2index.put(columns.get(i).getName(), i);
            }
        }
        return name2index.get(name);
    }

    public void set(final int rowIdx, final int colIdx, final Object value) {
        rows.get(rowIdx).set(colIdx, value);
    }

    public void set(final int rowIdx, final String colId, final Object value) {
        set(rowIdx, index(colId), value);
    }

    @Override
    public String toString() {
        final StringBuilder txt = new StringBuilder();
        txt.append(renderName());
        txt.append("\n");
        txt.append(renderColumns());
        txt.append("\n");
        final int[] columnLenghts = new int[1 + getColumnCount()];
        columnLenghts[0] = String.valueOf(getRowCount()).length();
        for (int colIdx = 0; colIdx < getColumnCount(); ++colIdx) {
            columnLenghts[colIdx + 1] = columns.get(colIdx).getName().length();
        }
        final String[][] data = new String[getRowCount()][];
        for (int rowIdx = 0; rowIdx < getRowCount(); ++rowIdx) {
            data[rowIdx] = new String[getColumnCount()];
            for (int colIdx = 0; colIdx < getColumnCount(); ++colIdx) {
                final Object value = get(rowIdx, colIdx);
                String valueString = "-";
                if (value != null) {
                    valueString = StringUtil.toString(value, getColumn(colIdx).getType());
                }
                data[rowIdx][colIdx] = valueString;
                if (valueString.length() > columnLenghts[colIdx + 1]) {
                    columnLenghts[colIdx + 1] = valueString.length();
                }
            }
        }
        final String heading = renderHeading(columnLenghts);
        txt.append(heading).append("\n");
        appendPadding(txt, '-', heading.length()).append("\n");
        for (int rowIdx = 0; rowIdx < getRowCount(); ++rowIdx) {
            appendToken(txt, String.valueOf(rowIdx + 1), ' ', columnLenghts[0], 1);
            for (int colIdx = 0; colIdx < getColumnCount(); ++colIdx) {
                txt.append("|");
                appendToken(txt, data[rowIdx][colIdx], ' ', columnLenghts[1 + colIdx], getAlignment(getColumn(colIdx).getType()));
            }
            txt.append("\n");
        }
        if (getRowCount() > 3) {
            appendPadding(txt, '-', heading.length()).append("\n");
            txt.append(heading).append("\n");
        }
        return txt.toString();
    }

    private StringBuilder appendPadding(final StringBuilder txt, final char padding, final int length) {
        for (int i = 0; i < length; ++i) {
            txt.append(padding);
        }
        return txt;
    }

    private StringBuilder appendToken(final StringBuilder txt, final String token, final char padding, final int available, final int position) {
        final int delta = available - token.length();
        int before = 0;
        int after = 0;
        if (delta > 0) {
            if (position < 0) {
                after = delta;
            } else if (position > 0) {
                before = delta;
            } else {
                before = delta / 2;
                after = delta - before;
            }
        }
        appendPadding(txt, padding, before);
        txt.append(token);
        appendPadding(txt, padding, after);
        return txt;
    }

    private int getAlignment(final PType type) {
        switch (type) {
            case STRING:
                return -1;
            case FIXED:
            case FLOAT:
                return 1;
            case ANY:
            case DATE:
            case TIME:
            case TIMESTAMP:
            default:
                return 0;
        }
    }

    private int getMaxColumnNameLength() {
        int maxColumnNameLength = 0;
        for (final PColumn column : columns) {
            final int columnNameLength = column.getName().length();
            if (columnNameLength > maxColumnNameLength) {
                maxColumnNameLength = columnNameLength;
            }
        }
        return maxColumnNameLength;
    }

    private String renderColumns() {
        final StringBuilder txt = new StringBuilder();
        final int maxColumnNameLength = getMaxColumnNameLength();
        for (final PColumn column : columns) {
            appendToken(txt, column.getName(), ' ', maxColumnNameLength, 1);
            txt.append(" : ");
            txt.append(column.getType());
            txt.append("\n");
        }
        return txt.toString();
    }

    private String renderHeading(final int[] columnLenghts) {
        final StringBuilder txt = new StringBuilder();
        appendToken(txt, "#", ' ', columnLenghts[0], 0);
        for (int colIdx = 0; colIdx < getColumnCount(); ++colIdx) {
            txt.append("|");
            appendToken(txt, columns.get(colIdx).getName(), ' ', columnLenghts[colIdx + 1], 0);
        }
        return txt.toString();
    }

    private String renderName() {
        final StringBuilder txt = new StringBuilder(getName());
        txt.append("\n");
        appendPadding(txt, '=', getName().length());
        txt.append("\n");
        return txt.toString();
    }

    protected PRow addRow(final PRow row) {
        lock();
        rows.add(row);
        setDirty(true);
        return row;
    }

    protected void lock() {
        locked = true;
    }

    void setDirty(final boolean flag) {
        workbook.setDirty(flag);
    }

}
