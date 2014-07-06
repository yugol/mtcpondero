package pondero.model.participants;

import static pondero.Logger.error;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pondero.engine.staples.StringUtil;
import pondero.model.entities.domains.Education;
import pondero.model.entities.domains.Gender;

public class ParticipantGenerator {

    private final List<String> fnames;
    private final List<String> mnames;
    private final List<String> snames;
    private final Random       rnd = new Random();

    public ParticipantGenerator() {
        fnames = readNames("/pondero/res/fnames.txt");
        mnames = readNames("/pondero/res/mnames.txt");
        snames = readNames("/pondero/res/snames.txt");
    }

    public Participant nextParticipant() {
        final Participant p = new Participant();
        // gender
        p.setGender(rnd.nextBoolean() ? Gender.MASCULINE : Gender.FEMININE);
        // name
        p.setSurname(snames.get(rnd.nextInt(snames.size())));
        if (p.getGender() == Gender.FEMININE) {
            p.setName(fnames.get(rnd.nextInt(fnames.size())));
        } else {
            p.setName(mnames.get(rnd.nextInt(mnames.size())));
        }
        // age
        p.setAge(rnd.nextInt(80) + 20);
        // education
        if (p.getAge() >= 18 && rnd.nextDouble() > 0.05) {
            if (p.getAge() >= 20 && rnd.nextDouble() > 0.8) {
                if (p.getAge() >= 25 && rnd.nextDouble() > 0.4) {
                    if (p.getAge() >= 30 && rnd.nextDouble() > 0.2) {
                        if (p.getAge() >= 35 && rnd.nextDouble() > 0.1) {
                            if (p.getAge() >= 40 && rnd.nextDouble() > 0.05) {
                                p.setEducation(Education.PHD);
                            } else {
                                p.setEducation(Education.MSC);
                            }
                        } else {
                            p.setEducation(Education.BSC);
                        }
                    } else {
                        p.setEducation(Education.HSG);
                    }
                } else {
                    p.setEducation(Education.TWELVE);
                }
            } else {
                p.setEducation(Education.TEN);
            }
        } else {
            p.setEducation(Education.LTEN);
        }

        return p;
    }

    private List<String> readNames(final String url) {
        final List<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ParticipantGenerator.class.getResourceAsStream(url), "UTF-8"))) {
            String name = null;
            while (null != (name = reader.readLine())) {
                names.add(name);
                if (!StringUtil.isNullOrBlank(name)) {
                    names.add(name);
                    // System.out.println(name);
                }
            }
        } catch (final IOException e) {
            error(e);
        }
        return names;
    }

}
