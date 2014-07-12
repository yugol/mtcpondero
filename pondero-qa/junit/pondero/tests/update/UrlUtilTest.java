package pondero.tests.update;

import java.io.IOException;
import org.junit.Test;
import pondero.Context;
import pondero.PonderoTest;

public class UrlUtilTest extends PonderoTest {

    @Test
    public void testPurlToUrl() throws IOException {
        final String urlString = UrlUtil.purlToUrl(Context.UPDATE_REGISTRY_ADDRESS);
        System.out.println(urlString);
    }

}
