package pondero.model.wb;

public class PColumn {

    private final PSheet sheet;
    private final String name;

    public PColumn(final PSheet sheet, final String name) {
        this.sheet = sheet;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PSheet getSheet() {
        return sheet;
    }

}
