package pondero.model.foundation.basic;

import static pondero.Logger.error;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import pondero.model.domains.Education;
import pondero.model.domains.Gender;
import pondero.model.foundation.PRow;
import pondero.model.foundation.PSheet;
import pondero.model.participants.ParticipantGenerator;
import pondero.util.DateUtil;
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
        setId(UUID.randomUUID().toString());
    }

    public Integer getAge() {
        final Calendar dob = getDob();
        if (dob != null) {
            final int dobYear = dob.get(Calendar.YEAR);
            return DateUtil.getCurrentYear() - dobYear;
        }
        return null;
    }

    public Calendar getDob() {
        return getCalendar(Participants.ATTR_DOB);
    }

    public Integer getDrivingAge() {
        return getInteger(Participants.ATTR_DRIVING_AGE);
    }

    public Education getEducation() {
        final String education = (String) get(Participants.ATTR_EDUCATION);
        return Education.parse(education);
    }

    public Gender getGender() {
        final String gender = (String) get(Participants.ATTR_GENDER);
        return Gender.parse(gender);
    }

    public String getId() {
        return (String) get(Participants.ATTR_ID);
    }

    public Integer getMileage() {
        return getInteger(Participants.ATTR_MILEAGE);
    }

    public String getName() {
        return (String) get(Participants.ATTR_NAME);
    }

    public String getSurname() {
        return (String) get(Participants.ATTR_SURNAME);
    }

    @Override
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

    public void setAge(final Integer value) {
        setDob(new GregorianCalendar(DateUtil.getCurrentYear() - value, Calendar.JANUARY, 1));
    }

    public void setDob(final Calendar value) {
        set(Participants.ATTR_DOB, value);
    }

    public void setDrivingAge(final Integer value) {
        set(Participants.ATTR_DRIVING_AGE, value);
    }

    public void setEducation(final Education value) {
        set(Participants.ATTR_EDUCATION, value.code);
    }

    public void setGender(final Gender value) {
        set(Participants.ATTR_GENDER, value.code);
    }

    public void setId(final int id) {
        setId(String.valueOf(id));
    }

    public void setId(final String value) {
        set(Participants.ATTR_ID, value);
    }

    public void setMileage(final Integer value) {
        set(Participants.ATTR_MILEAGE, value);
    }

    public void setName(final String value) {
        set(Participants.ATTR_NAME, value);
    }

    public void setSurname(final String value) {
        set(Participants.ATTR_SURNAME, value);
    }

    @Override
    public String toCsv() {
        final StringBuilder csv = new StringBuilder();
        csv.append(getId()).append(", ");
        csv.append(getSurname()).append(", ");
        csv.append(getName()).append(", ");
        csv.append(getDob()).append(", ");
        csv.append(getAge()).append(", ");
        csv.append(getGender()).append(", ");
        csv.append(getEducation()).append(", ");
        csv.append(getDrivingAge()).append(", ");
        csv.append(getMileage());
        return csv.toString();
    }

    @Override
    public String toString() {
        return getSurname() + ", " + getName() + " (" + getId() + ")";
    }

}
