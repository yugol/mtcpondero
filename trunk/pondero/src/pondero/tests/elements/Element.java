package pondero.tests.elements;

import java.util.UUID;
import pondero.tests.Test;
import pondero.tests.TestBase;

/*
 * method naming conventions
 * NO PREFIX - script commands
 * _ PREFIX - API methods
 * $ PREFIX - expression properties / functions
 */

public abstract class Element {

    private final String name;
    protected final Test test;

    public Element(final String name) {
        this.name = name == null ? UUID.randomUUID().toString() : name;
        test = TestBase.instance();
        test.add(this);
    }

    public Test getTest() {
        return test;
    }

    public String getName() {
        return name;
    }

    public abstract String getTypeName();

}
