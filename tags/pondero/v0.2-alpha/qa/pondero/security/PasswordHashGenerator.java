package pondero.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class PasswordHashGenerator {

    public static void main(String... args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null,
                pf,
                "Enter Password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (okCxl == JOptionPane.OK_OPTION) {
            String password = new String(pf.getPassword());
            System.out.println("Password hash for >" + password + "<: ");
            System.out.print(PasswordHash.createHash(password));
        }
    }

}
