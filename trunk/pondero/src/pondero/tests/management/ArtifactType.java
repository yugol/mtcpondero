package pondero.tests.management;

public enum ArtifactType {

    APPLICATION,
    MODULE,
    TEST;

    public static ArtifactType parseArtifactType(final String value) {
        try {
            return valueOf(value.trim().toUpperCase());
        } catch (final Exception e) {
            return null;
        }
    }

}
