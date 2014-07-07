package pondero.model.wb.basic;

import static pondero.Logger.error;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pondero.model.entities.domains.Education;
import pondero.model.entities.domains.Gender;
import pondero.model.participants.ParticipantGenerator;
import pondero.model.wb.PRow;
import pondero.model.wb.PSheet;
import pondero.util.StringUtil;

public class Participant extends PRow {

    private static final Random       rnd = new Random();
    private static final List<String> fnames;
    private static final List<String> mnames;
    private static final List<String> snames;

    static {
        fnames = readNames("/pondero/res/fnames.txt");
        mnames = readNames("/pondero/res/mnames.txt");
        snames = readNames("/pondero/res/snames.txt");
    }

    private static List<String> readNames(final String url) {
        final List<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ParticipantGenerator.class.getResourceAsStream(url), "UTF-8"))) {
            String name = null;
            while (null != (name = reader.readLine())) {
                names.add(name);
                if (!StringUtil.isNullOrBlank(name)) {
                    names.add(name);
                }
            }
        } catch (final IOException e) {
            error(e);
        }
        return names;
    }

    Participant(final PSheet sheet) {
        super(sheet);
    }

    public void randomize() {
        // gender
        setGender(rnd.nextBoolean() ? Gender.MASCULINE : Gender.FEMININE);

        // name
        setSurname(snames.get(rnd.nextInt(snames.size())));
        if (getGender() == Gender.FEMININE) {
            setName(fnames.get(rnd.nextInt(fnames.size())));
        } else {
            setName(mnames.get(rnd.nextInt(mnames.size())));
        }

        // age
        setAge(rnd.nextInt(70 - 20) + 20);

        // education
        if (getAge() >= 18 && rnd.nextDouble() > 0.05) {
            if (getAge() >= 20 && rnd.nextDouble() < 0.9) {
                if (getAge() >= 25 && rnd.nextDouble() < 0.5) {
                    if (getAge() >= 30 && rnd.nextDouble() < 0.2) {
                        if (getAge() >= 35 && rnd.nextDouble() < 0.1) {
                            if (getAge() >= 40 && rnd.nextDouble() < 0.05) {
                                setEducation(Education.PHD);
                            } else {
                                setEducation(Education.MSC);
                            }
                        } else {
                            setEducation(Education.BSC);
                        }
                    } else {
                        setEducation(Education.HSG);
                    }
                } else {
                    setEducation(Education.TWELVE);
                }
            } else {
                setEducation(Education.TEN);
            }
        } else {
            setEducation(Education.LTEN);
        }

        // driving age
        int drivingAge = getAge() - 18;
        if (drivingAge > 0) {
            drivingAge = rnd.nextInt(drivingAge);
        } else {
            drivingAge = 0;
        }
        setDrivingAge(drivingAge);

        // mileage
        int mileage = drivingAge * 100000;
        if (mileage > 0) {
            mileage = rnd.nextInt(mileage);
        }
        setMileage(mileage);
    }

    private int getAge() {
        // TODO Auto-generated method stub
        return 0;
    }

    private Gender getGender() {
        // TODO Auto-generated method stub
        return null;
    }

    private void setAge(final int i) {
        // TODO Auto-generated method stub

    }

    private void setDrivingAge(final int drivingAge) {
        // TODO Auto-generated method stub

    }

    private void setEducation(final Education phd) {
        // TODO Auto-generated method stub

    }

    private void setGender(final Gender gender) {
        set(Participants.ATTR_GENDER, gender.code);
    }

    private void setMileage(final int mileage) {
        // TODO Auto-generated method stub

    }

    private void setName(final String string) {
        // TODO Auto-generated method stub

    }

    private void setSurname(final String string) {
        // TODO Auto-generated method stub

    }

}
