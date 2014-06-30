package pondero.model.entities.domains;

import pondero.ui.Messages;

public enum Education {

    UNKNOWN (""),
    NONE ("---"),
    PRI ("GEN"),
    SEC ("LIC"),
    BSC ("BSC"),
    MSC ("MSC"),
    PHD ("PHD"),

    ;

    public static Education parse(String str) {
        if (str == null) {
            str = "";
        }
        str = str.trim().toUpperCase();
        if (str.equals(NONE.code)) { return NONE; }
        if (str.equals(PRI.code)) { return PRI; }
        if (str.equals(SEC.code)) { return SEC; }
        if (str.equals(BSC.code)) { return BSC; }
        if (str.equals(MSC.code)) { return MSC; }
        if (str.equals(PHD.code)) { return PHD; }
        return UNKNOWN;
    }

    public final String code;

    private Education(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        switch (this) {
            case BSC:
                return Messages.getString("lbl.education.bsc");
            case MSC:
                return Messages.getString("lbl.education.msc");
            case NONE:
                return Messages.getString("lbl.education.none");
            case PHD:
                return Messages.getString("lbl.education.phd");
            case PRI:
                return Messages.getString("lbl.education.pri");
            case SEC:
                return Messages.getString("lbl.education.sec");
            default:
                return "";
        }
    }

}
