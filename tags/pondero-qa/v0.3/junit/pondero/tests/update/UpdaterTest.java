package pondero.tests.update;

import static pondero.Logger.error;
import pondero.PonderoTest;

public class UpdaterTest extends PonderoTest {

    public static void main(final String... args) {
        updater.addListener(new UpdaterTest().new UpdateLogger());
        updater.readUpdates();
    }

    class UpdateLogger implements UpdateListener {

        @Override
        public void readRegistryEnded(final Updates applicableUpdates) {
            System.out.println("readRegistryEnded " + applicableUpdates.size());
            updater.downloadUpdates(applicableUpdates);
        }

        @Override
        public void readRegistryFailed(final Exception e) {
            System.out.println("readRegistryFailed");
            error(e);
        }

        @Override
        public void readRegistryStarted() {
            System.out.println("readRegistryStarted");
        }

        @Override
        public void updateArtifactEnded(final Artifact update) {
            System.out.println("updateArtifactEnded " + update.getCodeName());
        }

        @Override
        public void updateArtifactFailed(final Artifact update, final Exception e) {
            System.out.println("updateArtifactFailed");
            error(e);
        }

        @Override
        public void updateArtifactStarted(final Artifact update) {
            System.out.println("updateArtifactStarted " + update.getCodeName());
        }

        @Override
        public void updateProcessEnded() {
            System.out.println("updateProcessEnded");
        }

        @Override
        public void updateProcessStarted(final int updateCount) {
            System.out.println("updateProcessStarted " + updateCount);
        }

    }

    static UpdateEngine updater = new UpdateEngine();

}
