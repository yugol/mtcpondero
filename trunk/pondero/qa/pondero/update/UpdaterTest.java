package pondero.update;

public class UpdaterTest {

    class UpdateLogger implements UpdateListener {

        @Override
        public void readRegistryEnded(Updates applicableUpdates) {
            System.out.println("readRegistryEnded " + applicableUpdates.size());
            updater.downloadUpdates(applicableUpdates);
        }

        @Override
        public void readRegistryFailed(Exception e) {
            System.out.println("readRegistryFailed");
            e.printStackTrace();
        }

        @Override
        public void readRegistryStarted() {
            System.out.println("readRegistryStarted");
        }

        @Override
        public void updateArtifactEnded(Artifact update) {
            System.out.println("updateArtifactEnded " + update.getCodeName());
        }

        @Override
        public void updateArtifactFailed(Artifact update, Exception e) {
            System.out.println("updateArtifactFailed");
            e.printStackTrace();
        }

        @Override
        public void updateArtifactStarted(Artifact update) {
            System.out.println("updateArtifactStarted " + update.getCodeName());
        }

        @Override
        public void updateProcessEnded() {
            System.out.println("updateProcessEnded");
        }

        @Override
        public void updateProcessStarted(int updateCount) {
            System.out.println("updateProcessStarted " + updateCount);
        }

    }

    static UpdateEngine updater = new UpdateEngine();

    public static void main(String... args) {
        updater.addListener(new UpdaterTest().new UpdateLogger());
        updater.readUpdates();
    }

}
