package pondero;

import static pondero.Logger.debug;
import static pondero.Logger.error;
import static pondero.Logger.info;
import static pondero.Logger.trace;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import pondero.engine.staples.StringUtil;
import pondero.engine.test.CodeNameComparator;
import pondero.engine.test.Test;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.update.Artifact;

public final class Globals {

    public static final String         PURL_HOME               = "http://www.purl.org";
    public static final String         HOME_PAGE_ADDRESS       = PURL_HOME + "/net/pondero/home";
    public static final String         UPDATE_REGISTRY_ADDRESS = PURL_HOME + "/net/pondero/update/registry.xml";
    public static final String         CONTACT_MAIL_ADDRESS    = "mindtrips.communications@gmail.com";

    private static final String        CONSOLE_LOG_LEVEL_KEY   = "consoleLogLevel";
    private static final String        FILE_LOG_LEVEL_KEY      = "fileLogLevel";
    private static final String        LAST_WORKBOOK_FILE_KEY  = "lastWorkbookFile";
    private static final String        LOCALE_STRING_KEY       = "localeString";
    private static final String        UI_LAF_KEY              = "uiLaf";
    private static final String        UI_SCALE_FACTOR_KEY     = "uiScaleFactor";
    private static final String        UPDATE_ON_STARTUP_KEY   = "updateOnStartup";

    private static final String        PROPERTIES_FILE_NAME    = "pondero.properties";
    private static final String        DEFAULT_WORKBOOK_NAME   = "default.xlsx";

    private static File                homeFolder;
    private static boolean             runningFromIde;
    private static File                propertiesFile;
    private static final Set<Artifact> artifacts               = new HashSet<Artifact>();

    private static File                lastWorkbookFile;
    private static String              localeString            = "ro";
    private static String              uiLaf                   = "nimbus";
    private static boolean             updateOnStartup         = false;
    private static double              uiScaleFactor           = 1.2;

    public static boolean backupWorkbookOnOpen() {
        return true;
    }

    public static Set<Artifact> getArtifacts() {
        return artifacts;
    }

    public static Workbook getDefaultWorkbook() throws Exception {
        return WorkbookFactory.openWorkbook(Globals.getDefaultWorkbookFile());
    }

    public static File getDefaultWorkbookFile() {
        return new File(getFolderResults(), DEFAULT_WORKBOOK_NAME);
    }

    public static File getFolderBin() {
        return getFolder("bin");
    }

    public static File getFolderLogs() {
        return getFolder("logs");
    }

    public static File getFolderRes() {
        return getFolder("res");
    }

    public static File getFolderResults() {
        return getFolder("results");
    }

    public static File getFolderResultsBackup() {
        return getFolder("results/backup");
    }

    public static File getFolderResultsTemp() {
        return getFolder("results/temp");
    }

    public static File getFolderTests() {
        return getFolder("tests");
    }

    public static String getLaf() {
        return uiLaf;
    }

    public static File getLastWorkbookFile() {
        if (lastWorkbookFile != null) { return lastWorkbookFile; }
        return getDefaultWorkbookFile();
    }

    public static Locale getLocale() {
        return new Locale(localeString);
    }

    public static List<Test> getRegisteredTests() {
        final List<Test> tests = new ArrayList<Test>();
        for (final Object artifact : artifacts) {
            if (artifact instanceof Test) {
                tests.add((Test) artifact);
            }
        }
        Collections.sort(tests, new CodeNameComparator());
        return tests;
    }

    public static double getUiScaleFactor() {
        return uiScaleFactor;
    }

    public static boolean isParticipantOptional() {
        return isRunningFromIde() && false;
    }

    public static boolean isRunningFromIde() {
        return runningFromIde;
    }

    public static boolean isUpdateOnStartup() {
        return updateOnStartup;
    }

    public static void loadPreferences(String homeFolderName) throws Exception {
        if (StringUtil.isNullOrBlank(homeFolderName)) {
            runningFromIde = true;
            homeFolderName = "../../Pondero";
        } else {
            runningFromIde = false;
        }
        homeFolder = new File(homeFolderName);
        if (!homeFolder.exists()) {
            homeFolder.mkdirs();
        }
        propertiesFile = new File(getFolderRes(), PROPERTIES_FILE_NAME);
        if (propertiesFile.exists()) {
            final Reader propertiesReader = new FileReader(propertiesFile);
            final Properties properties = new Properties();
            properties.load(propertiesReader);
            propertiesReader.close();
            String foo = null;
            foo = properties.getProperty(CONSOLE_LOG_LEVEL_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                Logger.maxConsoleLevel = Integer.parseInt(foo.trim());
            }
            foo = properties.getProperty(FILE_LOG_LEVEL_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                Logger.maxFileLevel = Integer.parseInt(foo.trim());
            }
            foo = properties.getProperty(LOCALE_STRING_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                localeString = foo;
            }
            foo = properties.getProperty(UI_LAF_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                uiLaf = foo.trim().toLowerCase();
            }
            foo = properties.getProperty(UI_SCALE_FACTOR_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                uiScaleFactor = Double.parseDouble(foo);
            }
            foo = properties.getProperty(UPDATE_ON_STARTUP_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                updateOnStartup = Boolean.parseBoolean(foo.trim().toLowerCase());
            }
            foo = properties.getProperty(LAST_WORKBOOK_FILE_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                lastWorkbookFile = new File(foo);
                if (!lastWorkbookFile.exists()) {
                    lastWorkbookFile = null;
                }
            }
        } else {
            savePreferences();
        }
        info("home folder: ", homeFolder.getCanonicalPath());
        debug("properties file: ", propertiesFile.getCanonicalPath());
        trace("property: ", CONSOLE_LOG_LEVEL_KEY, "=", Logger.maxConsoleLevel);
        trace("property: ", FILE_LOG_LEVEL_KEY, "=", Logger.maxFileLevel);
        trace("property: ", LOCALE_STRING_KEY, "=", localeString);
        trace("property: ", UI_LAF_KEY, "=", uiLaf);
        trace("property: ", UI_SCALE_FACTOR_KEY, "=", uiScaleFactor);
        trace("property: ", UPDATE_ON_STARTUP_KEY, "=", updateOnStartup);
        registerArtifact(Artifact.fromJarFile(new File(getFolderBin(), "pondero.jar")));
        registerArtifact(Artifact.fromJarFile(new File(getFolderBin(), "pondero-libs.jar")));
        registerArtifact(Artifact.fromJarFile(new File(getFolderBin(), "pondero-install.jar")));
    }

    public static void registerArtifact(final Artifact artifact) {
        if (artifact != null && artifacts.add(artifact)) {
            info("registered artifact: " + artifact.getCodeName());
        }
    }

    public static void savePreferences() throws Exception {
        final Properties properties = new Properties();
        properties.setProperty(CONSOLE_LOG_LEVEL_KEY, String.valueOf(Logger.maxConsoleLevel));
        properties.setProperty(FILE_LOG_LEVEL_KEY, String.valueOf(Logger.maxFileLevel));
        properties.setProperty(LOCALE_STRING_KEY, String.valueOf(localeString));
        properties.setProperty(UI_LAF_KEY, String.valueOf(uiLaf));
        properties.setProperty(UI_SCALE_FACTOR_KEY, String.valueOf(uiScaleFactor));
        properties.setProperty(UPDATE_ON_STARTUP_KEY, String.valueOf(updateOnStartup));
        if (lastWorkbookFile != null) {
            properties.setProperty(LAST_WORKBOOK_FILE_KEY, lastWorkbookFile.getCanonicalPath());
        }
        final Writer propertiesWriter = new FileWriter(propertiesFile);
        properties.store(propertiesWriter, null);
        propertiesWriter.close();
    }

    public static void setLastWorkbookFile(final File file) {
        lastWorkbookFile = file;
        try {
            savePreferences();
        } catch (final Exception e) {
            error(e);
        }
    }

    private static File getFolder(final String name) {
        try {
            final File folder = new File(homeFolder.getCanonicalPath(), name);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        } catch (final IOException e) {
            error(e);
            return null;
        }
    }

    private Globals() {
    }

}
