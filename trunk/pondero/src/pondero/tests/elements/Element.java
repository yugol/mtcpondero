package pondero.tests.elements;

import java.util.UUID;
import pondero.tests.test.Test;
import pondero.tests.test.TestBase;

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

    public Test _getTest() {
        return test;
    }

    public String $name() {
        return name;
    }

    public abstract String $typename();

}
