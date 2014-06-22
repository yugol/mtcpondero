package pondero;

import static pondero.Logger.info;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import pondero.engine.staples.StringUtil;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.update.ArtifactDescriptor;

public final class Globals {

    public static final String                   HOME_PAGE_ADDRESS       = "http://www.purl.org/net/pondero/home";
    public static final String                   UPDATE_REGISTRY_ADDRESS = "http://www.purl.org/net/pondero/update/registry.xml";
    public static final String                   CONTACT_MAIL_ADDRESS    = "mindtrips.communications@gmail.com";

    private static final String                  DEFAULT_WORKBOOK_NAME   = "default.xlsx";                                       //$NON-NLS-1$

    private static final String                  LOCALE_STRING_KEY       = "localeString";                                       //$NON-NLS-1$
    private static final String                  UI_SCALE_FACTOR_KEY     = "uiScaleFactor";                                      //$NON-NLS-1$
    private static final String                  UI_LAF_KEY              = "uiLaf";                                              //$NON-NLS-1$

    private static File                          homeFolder;
    private static File                          binFolder;
    private static File                          testsFolder;
    private static File                          resFolder;
    private static File                          resultsFolder;

    private static final Set<ArtifactDescriptor> artifacts               = new HashSet<ArtifactDescriptor>();

    private static File                          propertiesFile;
    private static String                        localeString            = "ro";                                                 //$NON-NLS-1$
    private static double                        uiScaleFactor           = 1;
    private static String                        uiLaf                   = "system";                                             //$NON-NLS-1$
    private static boolean                       executedFromIde;

    public static boolean backupWorkbookOnOpen() {
        return true;
    }

    public static Set<ArtifactDescriptor> getArtifacts() {
        return artifacts;
    }

    public static File getBinFolder() {
        return binFolder;
    }

    public static Workbook getDefaultWorkbook() throws Exception {
        return WorkbookFactory.openWorkbook(Globals.getDefaultWorkbookFile());
    }

    public static File getDefaultWorkbookFile() {
        if (resultsFolder.isDirectory()) { return new File(resultsFolder, DEFAULT_WORKBOOK_NAME); }
        return new File(DEFAULT_WORKBOOK_NAME);
    }

    public static String getLaf() {
        return uiLaf;
    }

    public static File getLastWorkbookFile() {
        return getDefaultWorkbookFile();
    }

    public static Locale getLocale() {
        return new Locale(localeString);
    }

    public static File getResultsFolder() {
        return resultsFolder;
    }

    public static File getTestsFolder() {
        return testsFolder;
    }

    public static double getUiScaleFactor() {
        return uiScaleFactor;
    }

    public static boolean isAutoSaveExperimentData() {
        return false;
    }

    public static boolean isIde() {
        return executedFromIde;
    }

    public static void loadPreferences(String homeFolderName) throws Exception {
        final String propertiesFileName = "pondero.properties";
        if (StringUtil.isNullOrBlank(homeFolderName)) {
            executedFromIde = true;
            homeFolderName = "../../Pondero";
        } else {
            executedFromIde = false;
        }
        homeFolder = new File(homeFolderName);
        if (!homeFolder.exists()) {
            homeFolder.mkdirs();
        }
        binFolder = new File(homeFolder, "bin");
        if (!binFolder.exists()) {
            binFolder.mkdirs();
        }
        resFolder = new File(homeFolder, "res");
        if (!resFolder.exists()) {
            resFolder.mkdirs();
        }
        testsFolder = new File(homeFolder, "tests");
        if (!testsFolder.exists()) {
            testsFolder.mkdirs();
        }
        resultsFolder = new File(homeFolder, "results");
        if (!resultsFolder.exists()) {
            resultsFolder.mkdirs();
        }
        propertiesFile = new File(resFolder, propertiesFileName);
        if (propertiesFile.exists()) {
            final Reader propertiesReader = new FileReader(propertiesFile);
            final Properties properties = new Properties();
            properties.load(propertiesReader);
            propertiesReader.close();
            String foo = properties.getProperty(LOCALE_STRING_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                localeString = foo;
            }
            foo = properties.getProperty(UI_SCALE_FACTOR_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                uiScaleFactor = Double.parseDouble(foo);
            }
            foo = properties.getProperty(UI_LAF_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                uiLaf = foo.trim().toLowerCase();
            }
        } else {
            savePreferences();
        }
        info("home folder: ", homeFolder.getCanonicalPath());
        info("properties file: ", propertiesFile.getCanonicalPath());
        registerArtifact(ArtifactDescriptor.fromJarFile(new File(binFolder, "pondero.jar")));
        registerArtifact(ArtifactDescriptor.fromJarFile(new File(binFolder, "pondero-libs.jar")));
        registerArtifact(ArtifactDescriptor.fromJarFile(new File(binFolder, "pondero-install.jar")));
    }

    public static void registerArtifact(final ArtifactDescriptor artifact) {
        if (artifact != null && artifacts.add(artifact)) {
            info("registered artifact: " + artifact.getCodeName());
        }
    }

    public static void savePreferences() throws Exception {
        final Properties properties = new Properties();
        properties.setProperty(LOCALE_STRING_KEY, String.valueOf(localeString));
        properties.setProperty(UI_SCALE_FACTOR_KEY, String.valueOf(uiScaleFactor));
        properties.setProperty(UI_LAF_KEY, String.valueOf(uiLaf));
        final Writer propertiesWriter = new FileWriter(propertiesFile);
        properties.store(propertiesWriter, null);
        propertiesWriter.close();
    }

    private Globals() {
    }

}
