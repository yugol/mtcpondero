package pondero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class UnicodeToAscii {

    public static void main(String... args) throws Exception {
        String source = "./tools/pondero/unicode-sample.txt";
        // String source = "./setup/windows/inno/Romanian.unicode.isl";
        String destination = "F:/Workspace/Pondero/Romanian.isl";
        switch (args.length) {
            case 2:
                destination = args[1];
            case 1:
                source = args[0];
                break;
            case 0:
                break;
            default:
                System.err.println("Bad argument count " + args.length);
                System.exit(-1);
        }
        byte[] ascii = convertToAscii(source);
        try (OutputStream out = new FileOutputStream(destination)) {
            System.out.println("Converting unicode file to ASCII");
            out.write(ascii);
        }
    }

    private static byte[] convertToAscii(String inputFileName) throws IOException, FileNotFoundException {
        List<Byte> bytes = new ArrayList<Byte>();
        try (Reader reader = new InputStreamReader(new FileInputStream(inputFileName), "UTF-8")) {
            int chInt;
            while (-1 != (chInt = reader.read())) {
                char ch = (char) chInt;
                byte[] by = new byte[1];
                switch (ch) {
                    case 'â':
                        by[0] = (byte) 226; // 'â';
                        break;
                    case 'ă':
                        by[0] = (byte) 227; // 'ã';
                        break;
                    case 'î':
                        by[0] = (byte) 238; // 'î';
                        break;
                    case 'ș':
                        by[0] = (byte) 186; // 'º';
                        break;
                    case 'ț':
                        by[0] = (byte) 254; // 'þ';
                        break;
                    case 'Â':
                        by[0] = (byte) 194; // 'Â';
                        break;
                    case 'Ă':
                        by[0] = (byte) 195; // 0xc3;
                        break;
                    case 'Î':
                        by[0] = (byte) 206; // 'Î';
                        break;
                    case 'Ș':
                        by[0] = (byte) 170; // 'ª';
                        break;
                    case 'Ț':
                        by[0] = (byte) 222; // 0xdd;
                        break;
                    default:
                        by[0] = (byte) ch;
                }
                bytes.add(by[0]);
            }
        }
        byte[] ascii = new byte[bytes.size()];
        for (int i = 0; i < ascii.length; ++i) {
            ascii[i] = bytes.get(i);
        }
        return ascii;
    }

}
