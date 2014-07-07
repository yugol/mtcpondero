package pondero.model.wb;

public class PColumn {

    private final PSheet sheet;
    private final PHead  head;

    public PColumn(final PSheet sheet, final String name) {
        this.sheet = sheet;
        head = new PHead(this, name);
    }

    public PHead getHead() {
        return head;
    }

    public PSheet getSheet() {
        return sheet;
    }

}
