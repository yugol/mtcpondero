package participants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import pondero.engine.staples.StringUtil;

public class NameFile {

    public static final String SURNAMES_FILE_NAME = "./tools/participants/surnames.txt";

    public static void main(String... args) {
        NameFile data = new NameFile(SURNAMES_FILE_NAME);
        data.getNames();
    }

    private final File nameData;

    public NameFile(String filePath) {
        nameData = new File(filePath);
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(nameData), "UTF-8"));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nameData.getAbsolutePath() + ".clean"), "UTF-8"))) {
            String name = null;
            while (null != (name = reader.readLine())) {
                name = name.trim();
                if (!StringUtil.isNullOrBlank(name) && name.length() > 2) {
                    names.add(name);
                    writer.println(name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }
}
