package pondero.model.entities.domains;

import pondero.L10n;

public enum Education {

    UNKNOWN (""),
    LTEN ("-"),
    TEN ("X"),
    TWELVE ("XII"),
    HSG ("BAC"),
    BSC ("BSC"),
    MSC ("MSC"),
    PHD ("PHD"),

    ;

    public static Education parse(String str) {
        if (str == null) {
            str = "";
        }
        str = str.trim().toUpperCase();
        if (str.equals(LTEN.code)) { return LTEN; }
        if (str.equals(TEN.code)) { return TEN; }
        if (str.equals(TWELVE.code)) { return TWELVE; }
        if (str.equals(BSC.code)) { return BSC; }
        if (str.equals(MSC.code)) { return MSC; }
        if (str.equals(PHD.code)) { return PHD; }
        return UNKNOWN;
    }

    public final String code;

    private Education(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        switch (this) {
            case LTEN:
                return L10n.getString("lbl.education.lten");
            case TEN:
                return L10n.getString("lbl.education.ten");
            case TWELVE:
                return L10n.getString("lbl.education.twelve");
            case HSG:
                return L10n.getString("lbl.education.hsg");
            case BSC:
                return L10n.getString("lbl.education.bsc");
            case MSC:
                return L10n.getString("lbl.education.msc");
            case PHD:
                return L10n.getString("lbl.education.phd");
            default:
                return L10n.getString("lbl.education.na");
        }
    }

}
