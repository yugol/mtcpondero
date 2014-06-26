package pondero;

import java.io.FileReader;
import java.io.Reader;

public class UnicodeToAscii {

    public static void main(String... args) throws Exception {
        try (Reader reader = new FileReader(args[0])) {
            int chInt;
            while (-1 != (chInt = reader.read())) {
                char ch = (char) chInt;
                switch (ch) {
                    case 'ă':
                        ch = 'ã';
                        break;
                    case 'â':
                        ch = 'â';
                        break;
                    case 'î':
                        ch = 'î';
                        break;
                    case 'ș':
                        ch = 'º';
                        break;
                    case 'ț':
                        ch = 'þ';
                        break;
                    case 'Ă':
                        ch = (char) 0xc3;
                        break;
                    case 'Â':
                        ch = 'Â';
                        break;
                    case 'Î':
                        ch = 'Î';
                        break;
                    case 'Ș':
                        ch = 'ª';
                        break;
                    case 'Ț':
                        ch = (char) 0xdd;
                        break;
                }
                System.out.print(ch);
            }
        }
    }

}
