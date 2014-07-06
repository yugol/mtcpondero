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
                if (!StringUtil.isNullOrBlank(name) && name.length() > 2) {
                    names.add(name);
                    System.out.println(name);
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return names;
    }

}
