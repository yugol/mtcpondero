package pondero.util;

import static org.junit.Assert.fail;
import org.junit.Test;
import pondero.PonderoTest;

public class WebUtilTest extends PonderoTest {

    // @Test
    public void testBrowse() {
        fail("Not yet implemented");
    }

    @Test
    public void testMail() {
        WebUtil.mail("someone@somewhere.org", "Subject", "Lorem ipsum...");
        WebUtil.mail("someone@somewhere.org", "Subject", null);
        WebUtil.mail("someone@somewhere.org", null, "Lorem ipsum...");
        WebUtil.mail("someone@somewhere.org", null, null);
    }

}
