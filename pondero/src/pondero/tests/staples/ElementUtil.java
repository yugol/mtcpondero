package pondero.tests.staples;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.JLabel;
import pondero.tests.elements.interfaces.HasBlockfeedback;

public class ElementUtil {

    private static final Color       DEFAULT_SCREEN_COLOR = createColor(102, 204, 255);
    private static final Color       DEFAULT_FG_COLOR     = Color.BLACK;
    private static final Color       DEFAULT_BG_COLOR     = Color.WHITE;
    private static final Font        DEFAULT_FONT         = new JLabel().getFont();
    private static final Coordinates DEFAULT_POSITION     = new Coordinates("50%", "50%");
    private static final Random      RAND                 = new Random();

    public static Color createColor(final int r, final int g, final int b) {
        final float[] color = Color.RGBtoHSB(r, g, b, null);
        return Color.getHSBColor(color[0], color[1], color[2]);
    }

    public static Font createFont(final String faceName, final int height, final boolean bold, final boolean italic, final boolean underline, final boolean strikeout) {
        int style = bold ? Font.BOLD : Font.PLAIN;
        style |= italic ? Font.ITALIC : Font.PLAIN;
        Font font = new Font(faceName, style, height);
        if (underline | strikeout) {
            final Map<TextAttribute, Object> fontAttributes = new HashMap<TextAttribute, Object>();
            if (underline) {
                fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            }
            if (strikeout) {
                fontAttributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            }
            font = font.deriveFont(fontAttributes);
        }
        return font;
    }

    public static void fillBlockfeedback(final Set<String> blockfeedback, final String[] items) {
        for (String item : items) {
            item = item.trim();
            if (HasBlockfeedback.LATENCY.equalsIgnoreCase(item) || HasBlockfeedback.MEANLATENCY.equals(item)) {
                blockfeedback.add(HasBlockfeedback.MEANLATENCY);
            } else if (HasBlockfeedback.MEDIANLATENCY.equals(item)) {
                blockfeedback.add(HasBlockfeedback.MEDIANLATENCY);
            } else if (HasBlockfeedback.WINDOW.equals(item)) {
                blockfeedback.add(HasBlockfeedback.WINDOW);
            } else if (HasBlockfeedback.CORRECT.equals(item)) {
                blockfeedback.add(HasBlockfeedback.CORRECT);
            }
        }
    }

    public static Point2D getAbsolutePosition(final double sWidth, final double sHeight, final Rectangle2D bounds, final Point2D relativePosition) {
        double x = relativePosition.getX();
        if (x < 0) {
            x = (bounds.getWidth() - sWidth) * x;
        }
        double y = relativePosition.getY();
        if (y < 0) {
            y = (bounds.getHeight() - sHeight) * y;
        }
        return new Point2D.Double(x, y);
    }

    public static Color getDefaultBackgroundColor() {
        return DEFAULT_BG_COLOR;
    }

    public static Font getDefaultFont() {
        return DEFAULT_FONT;
    }

    public static Color getDefaultForegroundColor() {
        return DEFAULT_FG_COLOR;
    }

    public static Coordinates getDefaultPosition() {
        return DEFAULT_POSITION;
    }

    public static Color getDefaultScreenColor() {
        return DEFAULT_SCREEN_COLOR;
    }

    public static int getRandomIndex(final int maxIndex) {
        return RAND.nextInt(maxIndex) + 1;
    }

}
