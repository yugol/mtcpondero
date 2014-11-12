package pondero.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import pondero.Context;

public class StreamUtil {

    public static InputStream getResourceStream(final String path, Class<?> parent) throws IOException {
        final File file = new File(path);
        if (file.exists()) {
            return new BufferedInputStream(new FileInputStream(file));
        } else {
            if (parent == null) {
                parent = Context.class;
            }
            final String folder = "/" + parent.getPackage().getName().replace(".", "/") + "/";
            final String source = folder + path;
            return new BufferedInputStream(parent.getResourceAsStream(source));
        }
    }

}
