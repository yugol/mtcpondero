package pondero.model.participants;

import java.util.ArrayList;
import java.util.List;
import pondero.model.entities.Participant;
import pondero.model.entities.domains.Education;
import pondero.model.entities.domains.Sex;

public class DefaultParticipants {

    private static final List<Participant> DEFAULT = new ArrayList<Participant>();

    public static List<Participant> getParticipants() {
        Participant foo;
        if (DEFAULT.size() == 0) {
            foo = new Participant();
            foo.setId("#1");
            foo.setName("Svetlana");
            foo.setSurname("Bularca");
            foo.setSex(Sex.FEMININE);
            foo.setDob("");
            foo.setAge(46);
            foo.setEducation(Education.BSC);
            foo.setDrivingAge(18);
            foo.setMileage(138000);
            DEFAULT.add(foo);

            foo = new Participant();
            foo.setId("#2");
            foo.setName("Blanduzia");
            foo.setSurname("Dinu");
            foo.setSex(Sex.FEMININE);
            foo.setDob("");
            foo.setAge(34);
            foo.setEducation(Education.MSC);
            foo.setDrivingAge(10);
            foo.setMileage(90000);
            DEFAULT.add(foo);

            foo = new Participant();
            foo.setId("#3");
            foo.setName("Visarion");
            foo.setSurname("Ionescu");
            foo.setSex(Sex.MASCULINE);
            foo.setDob("");
            foo.setAge(52);
            foo.setEducation(Education.SEC);
            foo.setDrivingAge(33);
            foo.setMileage(432000);
            DEFAULT.add(foo);

            foo = new Participant();
            foo.setId("#4");
            foo.setName("Dochia");
            foo.setSurname("Nagy");
            foo.setSex(Sex.FEMININE);
            foo.setDob("");
            foo.setAge(24);
            foo.setEducation(Education.SEC);
            foo.setDrivingAge(1);
            foo.setMileage(2000);
            DEFAULT.add(foo);

            foo = new Participant();
            foo.setId("#5");
            foo.setName("Rafael");
            foo.setSurname("Popescu");
            foo.setSex(Sex.MASCULINE);
            foo.setDob("");
            foo.setAge(40);
            foo.setEducation(Education.BSC);
            foo.setDrivingAge(16);
            foo.setMileage(31000);
            DEFAULT.add(foo);

            foo = new Participant();
            foo.setId("#6");
            foo.setName("Eufrosina");
            foo.setSurname("Radu");
            foo.setSex(Sex.FEMININE);
            foo.setDob("");
            foo.setAge(45);
            foo.setEducation(Education.BSC);
            foo.setDrivingAge(14);
            foo.setMileage(16000);
            DEFAULT.add(foo);

            foo = new Participant();
            foo.setId("#7");
            foo.setName("Iolanda");
            foo.setSurname("Hacichian");
            foo.setSex(Sex.FEMININE);
            foo.setDob("");
            foo.setAge(20);
            foo.setEducation(Education.PRI);
            foo.setDrivingAge(0);
            foo.setMileage(0);
            DEFAULT.add(foo);

            foo = new Participant();
            foo.setId("#8");
            foo.setName("Alistar");
            foo.setSurname("Zbucea");
            foo.setSex(Sex.MASCULINE);
            foo.setDob("");
            foo.setAge(52);
            foo.setEducation(Education.BSC);
            foo.setDrivingAge(17);
            foo.setMileage(362000);
            DEFAULT.add(foo);

            foo = new Participant();
            foo.setId("#9");
            foo.setName("Ghenadie");
            foo.setSurname("Schirliu");
            foo.setSex(Sex.MASCULINE);
            foo.setDob("");
            foo.setAge(38);
            foo.setEducation(Education.SEC);
            foo.setDrivingAge(7);
            foo.setMileage(59000);
            DEFAULT.add(foo);
        }
        return DEFAULT;
    }
}
