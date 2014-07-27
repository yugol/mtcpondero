package pondero.tests.update;

import static pondero.Logger.info;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pondero.Context;
import pondero.util.TimeUtil;
import pondero.util.StringUtil;
import pondero.util.WebUtil;

public class UpdateUtil {

    public static void download(final Artifact update) throws Exception {
        final URL url = new URL(update.getUrl());
        File destination = null;
        switch (update.getType()) {
            case APPLICATION:
                destination = new File(Context.getFolderBin(), update.getFileName());
                break;
            case TEST:
                destination = new File(Context.getFolderTests(), update.getFileName());
                break;
            default:
                return;
        }
        final File downloaded = new File(destination.getAbsolutePath() + UPDATE_EXTENSION);
        if (destination != null) {
            info("downloading: ", url, " -> ", downloaded.getCanonicalPath());
            FileUtils.copyURLToFile(url, downloaded);
            boolean install = true;
            if (destination.exists()) {
                if (!destination.delete()) {
                    install = false;
                }
            }
            if (install) {
                info("installing: ", downloaded.getCanonicalPath());
                downloaded.renameTo(destination);
            }
        }
    }

    public static Updates getApplicableUpdates(final Updates availableUpdates) {
        final Updates applicableUpdates = new Updates();
        for (final Artifact update : availableUpdates) {
            boolean found = false;
            for (final Artifact installed : Context.getArtifacts()) {
                if (installed.equals(update)) {
                    found = true;
                    if (installed.compareTo(update) < 0) {
                        applicableUpdates.add(update);
                    }
                }
            }
            if (!found) {
                applicableUpdates.add(update);
            }
        }
        return applicableUpdates;
    }

    public static Updates getAvailableUpdates() throws Exception {
        InputStream registryStream = null;
        try {
            registryStream = WebUtil.openCloudStream(Context.UPDATE_REGISTRY_ADDRESS);
            final Updates availableArtifacts = new Updates();

            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(registryStream);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("application");
            for (int foo = 0; foo < nList.getLength(); ++foo) {
                final Element applicationElement = (Element) nList.item(foo);
                final Artifact descriptor = readArtifactDescriptor(ArtifactType.APPLICATION, applicationElement);
                if (descriptor != null) {
                    availableArtifacts.add(descriptor);
                }
            }
            nList = doc.getElementsByTagName("test");
            for (int foo = 0; foo < nList.getLength(); ++foo) {
                final Element testElement = (Element) nList.item(foo);
                final Artifact descriptor = readArtifactDescriptor(ArtifactType.TEST, testElement);
                if (descriptor != null) {
                    availableArtifacts.add(descriptor);
                }
            }

            Collections.sort(availableArtifacts);
            return availableArtifacts;
        } finally {
            if (registryStream != null) {
                registryStream.close();
            }
        }
    }

    private static boolean parseBoolean(String str) {
        if (StringUtil.isNullOrBlank(str)) { return false; }
        str = str.trim().toLowerCase();
        return "true".equals(str);
    }

    private static Artifact readArtifactDescriptor(final ArtifactType artifactType, final Element artifactElement) throws ParseException {
        final boolean valid = parseBoolean(artifactElement.getAttribute("valid"));
        if (valid) {
            final String id = artifactElement.getAttribute("id");
            final boolean mandatory = parseBoolean(artifactElement.getAttribute("mandatory"));
            String releaseDate = null;
            String major = null;
            String minor = null;
            String maturity = null;
            boolean isProtected = false;
            String passwordHash = null;
            String url = null;
            final NodeList cList = artifactElement.getChildNodes();
            for (int bar = 0; bar < cList.getLength(); bar++) {
                final Node cNode = cList.item(bar);
                if (cNode.getNodeType() == Node.ELEMENT_NODE) {
                    final Element cElement = (Element) cNode;
                    if ("releaseDate".equalsIgnoreCase(cElement.getTagName())) {
                        releaseDate = cElement.getTextContent();
                    } else if ("major".equalsIgnoreCase(cElement.getTagName())) {
                        major = cElement.getTextContent().trim();
                    } else if ("minor".equalsIgnoreCase(cElement.getTagName())) {
                        minor = cElement.getTextContent().trim();
                    } else if ("maturity".equalsIgnoreCase(cElement.getTagName())) {
                        maturity = cElement.getTextContent().trim();
                    } else if ("protected".equalsIgnoreCase(cElement.getTagName())) {
                        isProtected = parseBoolean(cElement.getTextContent());
                    } else if ("passwordHash".equalsIgnoreCase(cElement.getTagName())) {
                        passwordHash = cElement.getTextContent().trim();
                    } else if ("url".equalsIgnoreCase(cElement.getTagName())) {
                        url = cElement.getTextContent().trim();
                    }
                }
            }
            final Artifact aDesc = new Artifact(artifactType, id, Integer.parseInt(major), Integer.parseInt(minor), maturity);
            aDesc.setProtected(isProtected);
            aDesc.setMandatory(mandatory);
            aDesc.setReleaseDate(TimeUtil.parseIsoCalendar(releaseDate));
            aDesc.setPasswordHash(passwordHash);
            aDesc.setUrl(url);
            return aDesc;
        }
        return null;
    }

    public static final String UPDATE_EXTENSION = ".update";

}
