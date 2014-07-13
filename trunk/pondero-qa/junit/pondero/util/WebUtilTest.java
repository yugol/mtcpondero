package pondero.util;

import static org.junit.Assert.fail;
import java.io.IOException;
import org.junit.Test;
import pondero.Context;
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

    @Test
    public void testPurlToUrl() throws IOException {
        final String urlString = WebUtil.purlToUrl(Context.UPDATE_REGISTRY_ADDRESS);
        System.out.println(urlString);
    }

}
