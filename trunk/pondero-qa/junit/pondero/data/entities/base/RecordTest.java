package pondero.data.entities.base;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pondero.util.StringUtil;

public class RecordTest {

    @Test
    public void testCamel2Constant() {
        assertEquals("A", StringUtil.camel2Constant("a"));
        assertEquals("A_B", StringUtil.camel2Constant("aB"));
    }

}
