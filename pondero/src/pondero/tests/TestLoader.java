package pondero.tests;

import static pondero.Logger.info;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import pondero.Globals;
import pondero.engine.test.Test;
import pondero.ui.Pondero;
import pondero.update.Artifact;

public class TestLoader {

    public static ClassLoader testClassLoader;

    public static void registerTests(final Pondero pondero) {
        try {
            final File pluginFolder = Globals.getTestsFolder();

            final List<Artifact> artifacts = new ArrayList<Artifact>();
            final List<URL> jarUrls = new ArrayList<URL>();
            for (final File jarFile : pluginFolder.listFiles()) {
                if (jarFile.getName().toLowerCase().endsWith(".jar")) {
                    Artifact artifact = Artifact.fromJarFile(jarFile);
                    if (artifact != null && artifact.getTestClassName() != null) {
                        jarUrls.add(jarFile.toURI().toURL());
                        artifacts.add(artifact);
                    }
                }
            }

            if (Globals.isIde()) {
                testClassLoader = new URLClassLoader(jarUrls.toArray(new URL[] {}));
            } else {
                testClassLoader = TestLoader.class.getClassLoader();
            }

            for (final Artifact candidate : artifacts) {
                try {
                    final Class<? extends Test> testClass = testClassLoader.loadClass(candidate.getTestClassName()).asSubclass(Test.class);
                    Test testInstance = testClass.newInstance();
                    pondero.registerTask(testInstance);
                    info("registered test: ", testClass.getCanonicalName());
                    Globals.registerArtifact(candidate);
                } catch (final ClassCastException e) {
                    e.printStackTrace();
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
