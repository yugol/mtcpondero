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
import pondero.engine.test.CodeNameComparator;
import pondero.engine.test.Test;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.update.Artifact;
import pondero.util.StringUtil;

public final class Globals {

    public static final String         PURL_HOME                = "http://www.purl.org";
    public static final String         HOME_PAGE_ADDRESS        = PURL_HOME + "/net/pondero/home";
    public static final String         UPDATE_REGISTRY_ADDRESS  = PURL_HOME + "/net/pondero/update/registry.xml";
    public static final String         CONTACT_MAIL_ADDRESS     = "mindtrips.communications@gmail.com";

    private static final String        CONSOLE_LOG_LEVEL_KEY    = "consoleLogLevel";
    private static final String        FILE_LOG_LEVEL_KEY       = "fileLogLevel";
    private static final String        LAST_WORKBOOK_FILE_KEY   = "lastWorkbookFile";
    private static final String        UI_LOCALE_STRING_KEY     = "uilocaleString";
    private static final String        UI_THEME_STRING_KEY      = "uiThemeString";
    private static final String        UI_SCALE_FACTOR_KEY      = "uiFontScaleFactor";
    private static final String        UPDATE_ON_STARTUP_KEY    = "updateOnStartup";
    private static final String        PROPERTIES_FILE_NAME     = "pondero.properties";

    private static final String        DEFAULT_HOME_FOLDER_NAME = "../../Pondero";
    private static final String        DEFAULT_WORKBOOK_NAME    = "default.xlsx";

    private static final Set<Artifact> ARTIFACTS                = new HashSet<Artifact>();

    private static boolean             runningFromIde;
    private static File                homeFolder;
    private static File                propertiesFile;
    private static File                lastWorkbookFile;

    private static String              uiLocaleString           = "ro";
    private static String              uiThemeString            = "Nimbus";
    private static boolean             updateOnStartup          = false;
    private static double              uiFontScaleFactor        = 1.25;

    public static Set<Artifact> getArtifacts() {
        enable();
        return ARTIFACTS;
    }

    public static Workbook getDefaultWorkbook() throws Exception {
        enable();
        return WorkbookFactory.openWorkbook(Globals.getDefaultWorkbookFile());
    }

    public static File getDefaultWorkbookFile() {
        enable();
        return new File(getFolderResults(), DEFAULT_WORKBOOK_NAME);
    }

    public static File getFolderBin() {
        enable();
        return getFolder("bin");
    }

    public static File getFolderLogs() {
        enable();
        return getFolder("logs");
    }

    public static File getFolderRes() {
        enable();
        return getFolder("res");
    }

    public static File getFolderResults() {
        enable();
        return getFolder("results");
    }

    public static File getFolderResultsBackup() {
        enable();
        return getFolder("results/backup");
    }

    public static File getFolderResultsTemp() {
        enable();
        return getFolder("results/temp");
    }

    public static File getFolderTests() {
        enable();
        return getFolder("tests");
    }

    public static File getLastWorkbookFile() {
        enable();
        if (lastWorkbookFile != null) { return lastWorkbookFile; }
        return getDefaultWorkbookFile();
    }

    public static Locale getLocale() {
        enable();
        return new Locale(uiLocaleString);
    }

    public static List<Test> getRegisteredTests() {
        enable();
        final List<Test> tests = new ArrayList<Test>();
        for (final Object artifact : ARTIFACTS) {
            if (artifact instanceof Test) {
                tests.add((Test) artifact);
            }
        }
        Collections.sort(tests, new CodeNameComparator());
        return tests;
    }

    public static String getThemeString() {
        enable();
        return uiThemeString;
    }

    public static double getUiFontScaleFactor() {
        enable();
        return uiFontScaleFactor;
    }

    public static boolean isBackupWorkbookOnOpen() {
        enable();
        return true;
    }

    public static boolean isParticipantOptional() {
        enable();
        return isRunningFromIde() && false;
    }

    public static boolean isRunningFromIde() {
        enable();
        return runningFromIde;
    }

    public static boolean isUpdateOnStartup() {
        enable();
        return updateOnStartup;
    }

    public static void loadPreferences(String homeFolderName) throws Exception {
        if (StringUtil.isNullOrBlank(homeFolderName)) {
            runningFromIde = true;
            homeFolderName = DEFAULT_HOME_FOLDER_NAME;
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
            foo = properties.getProperty(UI_LOCALE_STRING_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                uiLocaleString = foo;
            }
            foo = properties.getProperty(UI_THEME_STRING_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                uiThemeString = foo.trim();
            }
            foo = properties.getProperty(UI_SCALE_FACTOR_KEY);
            if (!StringUtil.isNullOrBlank(foo)) {
                uiFontScaleFactor = Double.parseDouble(foo);
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
        trace("property: ", UI_LOCALE_STRING_KEY, "=", uiLocaleString);
        trace("property: ", UI_THEME_STRING_KEY, "=", uiThemeString);
        trace("property: ", UI_SCALE_FACTOR_KEY, "=", uiFontScaleFactor);
        trace("property: ", UPDATE_ON_STARTUP_KEY, "=", updateOnStartup);
        registerArtifact(Artifact.fromJarFile(new File(getFolderBin(), "pondero.jar")));
        registerArtifact(Artifact.fromJarFile(new File(getFolderBin(), "pondero-libs.jar")));
        registerArtifact(Artifact.fromJarFile(new File(getFolderBin(), "pondero-install.jar")));
    }

    public static void registerArtifact(final Artifact artifact) {
        enable();
        if (artifact != null && ARTIFACTS.add(artifact)) {
            info("registered artifact: " + artifact.getCodeName());
        }
    }

    public static void savePreferences() throws Exception {
        enable();
        final Properties properties = new Properties();
        properties.setProperty(CONSOLE_LOG_LEVEL_KEY, String.valueOf(Logger.maxConsoleLevel));
        properties.setProperty(FILE_LOG_LEVEL_KEY, String.valueOf(Logger.maxFileLevel));
        properties.setProperty(UI_LOCALE_STRING_KEY, String.valueOf(uiLocaleString));
        properties.setProperty(UI_THEME_STRING_KEY, String.valueOf(uiThemeString));
        properties.setProperty(UI_SCALE_FACTOR_KEY, String.valueOf(uiFontScaleFactor));
        properties.setProperty(UPDATE_ON_STARTUP_KEY, String.valueOf(updateOnStartup));
        if (lastWorkbookFile != null) {
            properties.setProperty(LAST_WORKBOOK_FILE_KEY, lastWorkbookFile.getCanonicalPath());
        }
        final Writer propertiesWriter = new FileWriter(propertiesFile);
        properties.store(propertiesWriter, null);
        propertiesWriter.close();
    }

    public static void setLastWorkbookFile(final File file) {
        enable();
        lastWorkbookFile = file;
        try {
            savePreferences();
        } catch (final Exception e) {
            error(e);
        }
    }

    public static void setLocale(final Locale value) {
        enable();
        uiLocaleString = value.toString();
    }

    public static void setUiFontScaleFactor(final Double value) {
        enable();
        uiFontScaleFactor = value;
    }

    public static void setUiThemeString(final String value) {
        enable();
        uiThemeString = value;
    }

    private static void enable() {
        if (homeFolder == null) {
            try {
                loadPreferences(null);
            } catch (final Exception e) {
                e.printStackTrace();
            }
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
