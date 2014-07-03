package pondero.model.entities;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;
import pondero.engine.staples.DateUtil;
import pondero.engine.staples.StringUtil;
import pondero.model.entities.base.Column;
import pondero.model.entities.base.Record;
import pondero.model.entities.base.Sheet;
import pondero.model.entities.domains.Education;
import pondero.model.entities.domains.Gender;

@Sheet(name = "PARTICIPANTS")
public class Participant extends Record {

    @Column
    private String    id         = UUID.randomUUID().toString();
    @Column
    private String    surname    = "";
    @Column
    private String    name       = "";
    @Column
    private Calendar  dob;
    @Column
    private Gender    gender     = Gender.UNSPECIFIED;
    @Column
    private Education education  = Education.UNKNOWN;
    @Column
    private int       drivingAge = 0;
    @Column
    private int       mileage    = 0;

    public int getAge() {
        int dobYear = dob.get(Calendar.YEAR);
        return DateUtil.getCurrentYear() - dobYear;
    }

    public String getAgeString() {
        return String.valueOf(getAge());
    }

    public Calendar getDob() {
        return dob;
    }

    public String getDobString() {
        if (dob == null) { return ""; }
        return DateUtil.toIsoDate(dob);
    }

    public int getDrivingAge() {
        return drivingAge;
    }

    public String getDrivingAgeString() {
        return String.valueOf(drivingAge);
    }

    public Education getEducation() {
        return education;
    }

    public String getEducationString() {
        return education.code;
    }

    public String getFootprint() {
        final StringBuilder fp = new StringBuilder();
        fp.append(getSurname().toLowerCase());
        fp.append(getId().toLowerCase());
        fp.append(getName().toLowerCase());
        return StringUtil.normalizeForSearch(fp.toString());
    }

    public Gender getGender() {
        return gender;
    }

    public String getGenderString() {
        return gender.code;
    }

    public String getId() {
        return id;
    }

    public String getIdString() {
        return id;
    }

    public int getMileage() {
        return mileage;
    }

    public String getMileageString() {
        return String.valueOf(mileage);
    }

    public String getName() {
        return name;
    }

    public String getNameString() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSurnameString() {
        return surname;
    }

    public void setAge(int age) {
        dob = new GregorianCalendar(DateUtil.getCurrentYear() - age, Calendar.JANUARY, 1);
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public void setDob(String dob) {
        this.dob = DateUtil.parseIsoDate(dob);
    }

    public void setDrivingAge(int drivingAge) {
        this.drivingAge = drivingAge;
    }

    public void setDrivingAge(String drivingAge) {
        this.drivingAge = Integer.parseInt(drivingAge);
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public void setEducation(String education) {
        this.education = Education.parse(education);
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setGender(String gender) {
        this.gender = Gender.parse(gender);
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = Integer.parseInt(mileage);
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    @Override
    public String toCsv() {
        StringBuilder csv = new StringBuilder();
        csv.append(getIdString()).append(", ");
        csv.append(getSurnameString()).append(", ");
        csv.append(getNameString()).append(", ");
        csv.append(getDobString()).append(", ");
        csv.append(getAgeString()).append(", ");
        csv.append(getGenderString()).append(", ");
        csv.append(getEducationString()).append(", ");
        csv.append(getDrivingAgeString()).append(", ");
        csv.append(getMileageString());
        return csv.toString();
    }

    @Override
    public String toString() {
        return surname + ", " + name + " (" + id + ")";
    }

}
