package pondero.tests.update;

import org.junit.Test;
import pondero.PonderoTest;

public class UpdateUtilTest extends PonderoTest {

    @Test
    public void testDownload() throws Exception {
        final Artifact update = new Artifact(ArtifactType.TEST, "test", 0, 1, "dev");
        update.setUrl("http://en.wikipedia.org/wiki/JAR_(file_format)#mediaviewer/File:Nuvola_mimetypes_java_jar.png");
        UpdateUtil.download(update);
    }

    // @Test
    public void testReadCloudArtifacts() throws Exception {
        final Updates available = UpdateUtil.getAvailableUpdates();
        System.out.println("Updates available : " + available.size());
        final Updates applicable = UpdateUtil.getApplicableUpdates(available);
        System.out.println("Updates applicable: " + applicable.size());
    }

}
