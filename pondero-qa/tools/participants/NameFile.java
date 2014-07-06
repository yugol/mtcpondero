package participants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import pondero.engine.staples.StringUtil;

public class NameFile {

    public static final String FNAMES_FILE_NAME   = "./tools/participants/fnames.txt";
    public static final String MNAMES_FILE_NAME   = "./tools/participants/mnames.txt";
    public static final String SURNAMES_FILE_NAME = "./tools/participants/surnames.txt";

    public static void main(final String... args) {
        final NameFile data = new NameFile(SURNAMES_FILE_NAME);
        data.getNames();
    }

    private final File nameData;

    public NameFile(final String filePath) {
        nameData = new File(filePath);
    }

    public List<String> getNames() {
        final List<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(nameData), "UTF-8"))) {
            String name = null;
            while (null != (name = reader.readLine())) {
                name = name.trim();
                if (!StringUtil.isNullOrBlank(name) && name.length() > 2 && !hasDiacritics(name)) {
                    names.add(name);
                    // System.out.println(name);
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    private boolean hasDiacritics(final String name) {
        for (int i = 0; i < name.length(); ++i) {
            final int ch = name.charAt(i);
            if (ch >= 128) { return true; }
        }
        return false;
    }

}
