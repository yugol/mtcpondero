package pondero.model.foundation;

public class PColumn {

    private final PSheet sheet;
    private final String name;
    private final PType  type;
    private final int    index;

    public PColumn(final PSheet sheet, final int index, final String name, final PType type) {
        this.sheet = sheet;
        this.index = index;
        this.name = name;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public PSheet getSheet() {
        return sheet;
    }

    public PType getType() {
        return type;
    }

}
