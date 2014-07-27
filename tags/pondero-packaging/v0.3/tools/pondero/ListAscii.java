package pondero;

public class ListAscii {

    public static void main(final String[] args) {
        for (int i = 128; i < 256; ++i) {
            System.out.println(i + " -> " + (char) i);
            // System.out.print("/");
        }
    }

}
