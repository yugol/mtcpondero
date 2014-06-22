package pondero.update;

import org.junit.Test;

public class UpdateUtilTest {

    @Test
    public void testDownload() throws Exception {
        Artifact update = new Artifact(ArtifactType.TEST, "test", 0, 1, "dev");
        update.setUrl("http://en.wikipedia.org/wiki/JAR_(file_format)#mediaviewer/File:Nuvola_mimetypes_java_jar.png");
        UpdateUtil.download(update);
    }

    // @Test
    public void testReadCloudArtifacts() throws Exception {
        Updates available = UpdateUtil.getAvailableUpdates();
        System.out.println("Updates available : " + available.size());
        Updates applicable = UpdateUtil.getApplicableUpdates(available);
        System.out.println("Updates applicable: " + applicable.size());
    }

}
