package pondero.update;

import static pondero.Logger.trace;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import pondero.security.PasswordHash;

public class Artifact implements Comparable<Artifact> {

    public static Artifact fromJarFile(File jarFile) {
        try {
            trace(jarFile.getCanonicalPath());
            final JarInputStream jarStream = new JarInputStream(new FileInputStream(jarFile));
            final Manifest manifest = jarStream.getManifest();
            jarStream.close();
            final Attributes attrs = manifest.getMainAttributes();
            final ArtifactType type = ArtifactType.valueOf(attrs.getValue("Pondero-Artifact-Type"));
            final String id = attrs.getValue("Pondero-Artifact-Id");
            final int major = Integer.parseInt(attrs.getValue("Pondero-Artifact-Major"));
            final int minor = Integer.parseInt(attrs.getValue("Pondero-Artifact-Minor"));
            final String maturity = attrs.getValue("Pondero-Artifact-Maturity");
            Artifact artifact = new Artifact(type, id, major, minor, maturity);
            if (ArtifactType.TEST.equals(type)) {
                artifact.setTestClassName(attrs.getValue("Main-Class"));
            }
            return artifact;
        } catch (Exception e) {
            return null;
        }
    }

    private final ArtifactType type;
    private final String       id;
    private final int          major;
    private final int          minor;
    private final String       maturity;
    private boolean            isProtected;
    private boolean            mandatory;
    private Calendar           releaseDate;
    private String             url;
    private String             testClassName;

    private String             passwordHash;

    public Artifact(ArtifactType type, String id, int major, int minor, String maturity) {
        this.type = type;
        this.id = id;
        this.major = major;
        this.minor = minor;
        this.maturity = maturity;
    }

    @Override
    public int compareTo(Artifact other) {
        int cmp = type.compareTo(other.type);
        if (cmp == 0) {
            cmp = id.compareTo(other.id);
            if (cmp == 0) {
                cmp = Integer.compare(major, other.major);
                if (cmp == 0) {
                    cmp = Integer.compare(minor, other.minor);
                }
            }
        }
        return cmp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Artifact) {
            Artifact other = (Artifact) obj;
            return type.equals(other.type) && id.equals(other.id);
        }
        return false;
    }

    public String getCodeName() {
        return getId() + "-v" + getMajor() + "." + getMinor() + "-" + getMaturity();
    }

    public String getFileName() {
        switch (type) {
            case APPLICATION:
                return (getId() + ".jar").toLowerCase();
            case TEST:
                return ("pondero-test-" + getId() + ".jar").toLowerCase();
            default:
                return null;
        }
    }

    public String getId() {
        return id;
    }

    public int getMajor() {
        return major;
    }

    public String getMaturity() {
        return maturity;
    }

    public int getMinor() {
        return minor;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public String getTestClassName() {
        return testClassName;
    }

    public ArtifactType getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int hashCode() {
        return (id + type).hashCode();
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ArtifactDescriptor [type=" + type + ", id=" + id + ", major=" + major + ", minor=" + minor + "]";
    }

    public boolean validatePassword(String password) {
        try {
            if (password == null) { return false; }
            return PasswordHash.validatePassword(password, passwordHash);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
