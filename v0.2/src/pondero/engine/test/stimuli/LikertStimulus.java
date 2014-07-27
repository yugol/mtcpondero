package pondero.engine.test.stimuli;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.UIManager;
import pondero.engine.elements.trial.Likert;
import pondero.engine.staples.ElementUtil;

@Deprecated
public class LikertStimulus extends VisualStimulus {

    private static final int LIKERT_MARGIN  = 10;
    private static final int LIKERT_HEIGHT  = 100;
    private static final int BUTTON_VMARGIN = 15;
    private static final int TEXT_VMARGIN   = 4;

    public LikertStimulus(final Likert parent) {
        super(parent);
    }

    @Override
    public Likert getParent() {
        return (Likert) super.getParent();
    }

    @Override
    public void render(final Graphics2D g2d, final int surfaceWidth, final int surfaceHeight) {
        final int pointCnt = getParent().numpoints();
        int buttonWidth = getParent().anchorwidth();
        if (buttonWidth <= 0) {
            buttonWidth = 80;
        }
        g2d.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
        final FontMetrics fm = g2d.getFontMetrics();
        final int buttonHeight = TEXT_VMARGIN + fm.getHeight() + TEXT_VMARGIN;

        final Rectangle2D likertBounds = new Rectangle2D.Double(
                LIKERT_MARGIN,
                surfaceHeight - LIKERT_HEIGHT - LIKERT_MARGIN,
                surfaceWidth - LIKERT_MARGIN * 2,
                LIKERT_HEIGHT);
        g2d.setColor(ElementUtil.getDefaultScreenColor());
        g2d.fill3DRect((int) likertBounds.getX(), (int) likertBounds.getY(), (int) likertBounds.getWidth(), (int) likertBounds.getHeight(), false);

        final int buttonHMargin = (int) Math.rint((likertBounds.getWidth() - 2 - pointCnt * buttonWidth) / (pointCnt * 2));
        for (int i = 0; i < pointCnt; ++i) {

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fill3DRect(
                    LIKERT_MARGIN + buttonHMargin + i * (buttonHMargin + buttonWidth + buttonHMargin),
                    surfaceHeight - LIKERT_MARGIN - BUTTON_VMARGIN - buttonHeight,
                    buttonWidth,
                    buttonHeight,
                    true);

            g2d.setColor(Color.WHITE);
            String message = getParent()._getAnchor(i);
            Rectangle2D textBounds = fm.getStringBounds(message, g2d);
            int textHMargin = (int) ((buttonWidth - textBounds.getWidth()) / 2);
            g2d.drawString(message,
                    LIKERT_MARGIN + buttonHMargin + i * (buttonHMargin + buttonWidth + buttonHMargin) + textHMargin,
                    surfaceHeight - LIKERT_MARGIN - buttonHeight * 2 - fm.getHeight() - 3);

            g2d.setColor(Color.BLACK);
            message = "[" + String.valueOf(i + 1) + "]";
            textBounds = fm.getStringBounds(message, g2d);
            textHMargin = (int) ((buttonWidth - textBounds.getWidth()) / 2);
            g2d.drawString(message,
                    LIKERT_MARGIN + buttonHMargin + i * (buttonHMargin + buttonWidth + buttonHMargin) + textHMargin,
                    surfaceHeight - LIKERT_MARGIN - BUTTON_VMARGIN - TEXT_VMARGIN - 3);

            if (i > 0) {
                g2d.setColor(Color.GRAY);
                g2d.drawRect(
                        LIKERT_MARGIN + buttonHMargin + buttonWidth + (i - 1) * (buttonHMargin + buttonWidth + buttonHMargin),
                        surfaceHeight - LIKERT_MARGIN - BUTTON_VMARGIN - buttonHeight / 2 - 2,
                        buttonHMargin * 2 - 1,
                        2);
            }
        }

    }
}
