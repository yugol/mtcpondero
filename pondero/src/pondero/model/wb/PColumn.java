package pondero.model.wb;

public class PColumn {

    private final PSheet sheet;
    private final String name;
    private final PType  type;

    public PColumn(final PSheet sheet, final String name, final PType type) {
        this.sheet = sheet;
        this.name = name;
        this.type = type;
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
