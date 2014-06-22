package pondero.update;

public interface UpdateListener {

    void readRegistryEnded(Updates applicableUpdates);

    void readRegistryFailed(Exception e);

    void readRegistryStarted();

    void updateArtifactEnded(ArtifactDescriptor update);

    void updateArtifactFailed(ArtifactDescriptor update, Exception e);

    void updateArtifactStarted(ArtifactDescriptor update);

    void updateProcessEnded();

    void updateProcessStarted(int updateCount);

}
