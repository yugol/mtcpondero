package participants;

import java.util.List;
import java.util.Random;
import pondero.model.entities.domains.Education;
import pondero.model.entities.domains.Gender;
import pondero.model.participants.Participant;

public class ParticipantGenerator {

    public static void main(final String... args) {
        for (int id = 101; id <= 110; ++id) {
            final Participant p = new ParticipantGenerator().nextParticipant();
            p.setId(String.valueOf(id));
            System.out.println(p.toCsv());
        }
    }

    private final List<String> fnames;
    private final List<String> mnames;
    private final List<String> surnames;
    private final Random       rnd = new Random();

    public ParticipantGenerator() {
        fnames = new NameFile(NameFile.FNAMES_FILE_NAME).getNames();
        mnames = new NameFile(NameFile.MNAMES_FILE_NAME).getNames();
        surnames = new NameFile(NameFile.SURNAMES_FILE_NAME).getNames();
    }

    public Participant nextParticipant() {
        final Participant p = new Participant();
        // gender
        p.setGender(rnd.nextBoolean() ? Gender.MASCULINE : Gender.FEMININE);
        // name
        p.setSurname(surnames.get(rnd.nextInt(surnames.size())));
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

}
