package pondero.model.wb;

public class PHead {

    private final PColumn column;
    private final String  name;
    private PType         type;

    public PHead(final PColumn column, final String name) {
        this.column = column;
        this.name = name;
        type = PType.ANY;
    }

    public PColumn getColumn() {
        return column;
    }

    public String getName() {
        return name;
    }

    public PType getType() {
        return type;
    }

    public void setType(final PType type) {
        this.type = type;
    }

}
