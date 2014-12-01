package pondero.tests.management;

public interface UpdateListener {

    void readRegistryEnded(Updates applicableUpdates);

    void readRegistryFailed(Exception e);

    void readRegistryStarted();

    void updateArtifactEnded(Artifact update);

    void updateArtifactFailed(Artifact update, Exception e);

    void updateArtifactStarted(Artifact update);

    void updateProcessEnded();

    void updateProcessStarted(int updateCount);

}
