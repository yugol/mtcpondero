package pondero.update;

import java.io.IOException;
import org.junit.Test;
import pondero.Globals;

public class UrlUtilTest {

    @Test
    public void testPurlToUrl() throws IOException {
        final String urlString = UrlUtil.purlToUrl(Globals.UPDATE_REGISTRY_ADDRESS);
        System.out.println(urlString);
    }

}
