package pondero.data.entities.base;

import java.util.Random;
import org.junit.Test;
import pondero.data.domains.Education;

public class RandomGen {

    @Test
    public void test() {
        Random r = new Random();
        Education[] values = Education.values();
        for (int i = 0; i < 100; ++i) {
            System.out.println(values[r.nextInt(values.length)]);
        }
    }
}
