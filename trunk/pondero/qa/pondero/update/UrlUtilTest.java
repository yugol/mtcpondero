package pondero.update;

import java.io.IOException;
import org.junit.Test;
import pondero.Globals;

public class UrlUtilTest {

    //@Test
    public void testPurlToUrl() throws IOException {
        final String content = UrlUtil.purlToUrl(Globals.UPDATE_REGISTRY_ADDRESS);
        System.out.println(content);
    }

    @Test
    public void testUrlToString() throws IOException {
        final String content = UrlUtil.purlToString(Globals.UPDATE_REGISTRY_ADDRESS);
        System.out.println(content);
    }

}
