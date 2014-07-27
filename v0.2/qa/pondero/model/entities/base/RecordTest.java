package pondero.model.entities.base;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pondero.model.entities.base.Record;

public class RecordTest {

    @Test
    public void testCamel2Constant() {
        assertEquals("A", Record.camel2Constant("a"));
        assertEquals("A_B", Record.camel2Constant("aB"));
    }

}
