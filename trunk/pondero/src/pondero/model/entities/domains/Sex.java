package pondero.model.entities.domains;

import pondero.ui.Messages;

public enum Sex {

    UNSPECIFIED (""),
    FEMININE ("F"),
    MASCULINE ("M"),

    ;

    public static Sex parse(String str) {
        if (str == null) {
            str = "";
        }
        str = str.trim().toUpperCase();
        if (str.equals(FEMININE.code)) { return FEMININE; }
        if (str.equals(MASCULINE.code)) { return MASCULINE; }
        return UNSPECIFIED;
    }

    public final String code;

    private Sex(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        switch (this) {
            case FEMININE:
                return Messages.getString("lbl.sex.feminine");
            case MASCULINE:
                return Messages.getString("lbl.sex.masculine");
            default:
                return "";
        }
    }

}
