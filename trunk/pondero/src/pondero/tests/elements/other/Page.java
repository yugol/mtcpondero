package pondero.tests.elements.other;

import pondero.tests.elements.Element;

public class Page extends Element {

    public static final String TYPENAME = "page";

    private final String       content;

    public Page(final String name, final String content) {
        super(name);
        this.content = content;
    }

    public String $content() {
        final String foo = content.replace("\n", "").replace("^", "\n");
        return foo;
    }

    @Override
    public String $typename() {
        return TYPENAME;
    }

}
