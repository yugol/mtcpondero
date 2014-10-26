package pondero;

import java.io.File;
import java.io.IOException;

public class UpdateInstaller {

    public static final String UPDATE_EXTENSION = ".update";

    public static void main(final String... args) throws Exception {
        String home = "../../Pondero";
        if (args.length > 0) {
            home = args[0];
        }
        final File homeFolder = new File(home);
        System.out.println("Installing updates for " + homeFolder.getCanonicalPath() + " ...");
        // installUpdates(new File(homeFolder.getCanonicalPath(), "bin"));
        installUpdates(new File(homeFolder.getCanonicalPath(), "tests"));
        System.out.println("done installing updates");
    }

    private static void installUpdates(final File folder) throws IOException {
        System.out.println("In " + folder.getCanonicalPath() + " ... ");
        for (final File updatedFile : folder.listFiles()) {
            if (updatedFile.isFile() && updatedFile.getName().endsWith(UPDATE_EXTENSION)) {
                final String updatedFilePath = updatedFile.getCanonicalPath();
                System.out.print("installing: " + updatedFilePath + " ... ");
                final String previousFilePath = updatedFilePath.substring(0, updatedFilePath.length() - UPDATE_EXTENSION.length());
                final File previousFile = new File(previousFilePath);
                if (previousFile.exists() && previousFile.isFile()) {
                    if (previousFile.delete()) {
                        updatedFile.renameTo(previousFile);
                        System.out.println("done");
                    } else {
                        System.out.println("could not delete previous file");
                    }
                } else {
                    updatedFile.renameTo(previousFile);
                    System.out.println("done");
                }
            }
        }
        System.out.println("done folder");
    }

}