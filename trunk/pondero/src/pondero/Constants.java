package pondero;

import java.awt.Color;
import java.awt.Font;
import pondero.tests.staples.ElementUtil;

public final class Constants {

    public static final String    PURL_HOME                     = "http://www.purl.org";
    public static final String    HOME_PAGE_ADDRESS             = PURL_HOME + "/net/pondero/home";
    public static final String    UPDATE_REGISTRY_ADDRESS       = PURL_HOME + "/net/pondero/update/registry.xml";
    public static final String    CONTACT_MAIL_ADDRESS          = "mindtrips.communications@gmail.com";

    public static final String    NA                            = "N/A";

    public static final Color     DEFAULT_PAGE_SCREEN_COLOR     = ElementUtil.createColor(0x94, 0x92, 0x88);                      // 949288
    public static final Color     DEFAULT_TRIAL_SCREEN_COLOR    = ElementUtil.createColor(200, 200, 200);
    public static final Color     DEFAULT_ANSWERS_BG_COLOR      = ElementUtil.createColor(0xd6, 0xd9, 0xdf);                      // d6d9df
    public static final Color     DEFAULT_ANSWERS_FG_COLOR      = ElementUtil.createColor(0x00, 0x00, 0x00);
    public static final Color     DEFAULT_INFO_BG_COLOR         = ElementUtil.createColor(0x43, 0x55, 0x78);
    public static final Color     DEFAULT_INFO_FG_COLOR         = ElementUtil.createColor(0xFF, 0xFF, 0xFF);
    public static final Color     DEFAULT_QUESTION_BG_COLOR     = ElementUtil.createColor(0x43, 0x78, 0x66);
    public static final Color     DEFAULT_QUESTION_FG_COLOR     = ElementUtil.createColor(0xFF, 0xFF, 0xFF);

    public static final Font      DEFAULT_INSTRUCT_FONT         = ElementUtil.createFont("Dialog", 24, true, false, false, false);
    public static final Character DEFAULT_INSTRUCT_NEXT_KEY     = ' ';
    public static final Character DEFAULT_INSTRUCT_PREV_KEY     = ',';
    public static final Color     DEFAULT_INSTRUCT_SCREEN_COLOR = DEFAULT_ANSWERS_BG_COLOR;
    public static final Color     DEFAULT_INSTRUCT_TEXT_COLOR   = DEFAULT_ANSWERS_FG_COLOR;

}
