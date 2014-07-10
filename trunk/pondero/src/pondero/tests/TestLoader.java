package pondero.tests;

import static pondero.Logger.error;
import static pondero.Logger.info;
import static pondero.Logger.warning;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import pondero.Context;
import pondero.tests.test.Test;
import pondero.tests.update.Artifact;

public class TestLoader {

    public static ClassLoader testClassLoader;

    public static List<Test> loadTests() {
        try {
            final File pluginFolder = Context.getFolderTests();
            final List<Artifact> artifacts = new ArrayList<Artifact>();
            final List<URL> jarUrls = new ArrayList<URL>();
            for (final File jarFile : pluginFolder.listFiles()) {
                if (jarFile.getName().toLowerCase().endsWith(".jar")) {
                    final Artifact artifact = Artifact.fromJarFile(jarFile);
                    if (artifact != null && artifact.getTestClassName() != null) {
                        jarUrls.add(jarFile.toURI().toURL());
                        artifacts.add(artifact);
                    }
                }
            }

            if (Context.isRunningFromIde()) {
                testClassLoader = new URLClassLoader(jarUrls.toArray(new URL[] {}));
            } else {
                testClassLoader = TestLoader.class.getClassLoader();
            }

            final List<Test> tests = new ArrayList<Test>();
            for (final Artifact candidate : artifacts) {
                try {
                    final Class<? extends Test> testClass = testClassLoader.loadClass(candidate.getTestClassName()).asSubclass(Test.class);
                    tests.add(testClass.newInstance());
                    info("registered test: ", testClass.getCanonicalName());
                    Context.registerArtifact(candidate);
                } catch (final ClassCastException e) {
                    warning(candidate.getFileName(), " is not a test");
                }
            }
            return tests;
        } catch (final Exception e) {
            error(e);
        }
        return null;
    }

}
