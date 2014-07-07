package pondero.model.domains;

import pondero.L10n;

public enum Gender {

    UNSPECIFIED (""),
    FEMININE ("F"),
    MASCULINE ("M"),

    ;

    public static Gender parse(String str) {
        if (str == null) {
            str = "";
        }
        str = str.trim().toUpperCase();
        if (str.equals(FEMININE.code)) { return FEMININE; }
        if (str.equals(MASCULINE.code)) { return MASCULINE; }
        return UNSPECIFIED;
    }

    public final String code;

    private Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        switch (this) {
            case FEMININE:
                return L10n.getString("lbl.gender.feminine");
            case MASCULINE:
                return L10n.getString("lbl.gender.masculine");
            default:
                return "";
        }
    }

}
