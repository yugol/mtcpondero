package pondero.tests.elements.other;

import pondero.tests.elements.Element;

public class Page extends Element {

    public static final String TYPENAME = "page";

    private final String       content;
    private boolean            first    = true;
    private boolean            last     = false;

    public Page(final String name, final String content) {
        super(name);
        this.content = content;
    }

    public String getContent() {
        final String foo = content.replace("\n", "").replace("^", "\n");
        return foo;
    }

    @Override
    public String getTypeName() {
        return TYPENAME;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public void setFirst(final boolean first) {
        this.first = first;
    }

    public void setLast(final boolean last) {
        this.last = last;
    }

}
