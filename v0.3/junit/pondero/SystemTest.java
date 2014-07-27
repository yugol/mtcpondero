package pondero;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;

public class SystemTest {

    @Test
    public void list() {
        final Map<String, String> env = System.getenv();
        for (final String name : env.keySet()) {
            System.out.println(name + "=" + env.get(name));
        }

        System.out.println();

        final Properties props = System.getProperties();
        final Enumeration<?> names = props.propertyNames();
        while (names.hasMoreElements()) {
            final String name = (String) names.nextElement();
            System.out.println(name + "=" + props.getProperty(name));
        }
    }

}
