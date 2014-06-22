package pondero.update;

public enum ArtifactType {

    APPLICATION,
    TEST;

    public static ArtifactType parseArtifactType(String value) {
        try {
            return valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

}
