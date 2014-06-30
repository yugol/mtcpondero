package pondero.engine.test.stimuli;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import pondero.engine.elements.Element;
import pondero.engine.staples.Coordinates;

public class TextStimulus extends VisualStimulus {

    private String      text;
    private Font        font;
    private Coordinates position;
    private Coordinates size;
    private Color       fgColor;
    private Color       bgColor;

    public TextStimulus(final Element parent) {
        super(parent);
    }

    public Color getBgColor() {
        return bgColor;
    }

    public Color getFgColor() {
        return fgColor;
    }

    public Font getFont() {
        return font;
    }

    public Coordinates getPosition() {
        return position;
    }

    public Coordinates getSize() {
        return size;
    }

    public String getText() {
        return text;
    }

    @Override
    public void render(final Graphics2D g2d, final int surfaceWidth, final int surfaceHeight) {
        g2d.setFont(getFont());
        final FontMetrics fm = g2d.getFontMetrics();
        Rectangle2D bounds = null;
        if (getSize() == null) {
            bounds = fm.getStringBounds(getText(), g2d);
        } else {
            final Point2D wh = getSize().toPoint(surfaceWidth, surfaceHeight);
            bounds = new Rectangle2D.Double(0, 0, wh.getX(), wh.getY());
        }
        final Point2D pos = position.toPoint(surfaceWidth, surfaceHeight, bounds);
        final int x = (int) pos.getX();
        final int y = (int) pos.getY();
        g2d.setColor(getBgColor());
        g2d.fillRect(x, y, (int) bounds.getWidth(), (int) bounds.getHeight());
        g2d.setColor(getFgColor());
        g2d.drawString(getText(), x, y + fm.getAscent());
    }

    public void setBgColor(final Color bgColor) {
        this.bgColor = bgColor;
    }

    public void setFgColor(final Color fgColor) {
        this.fgColor = fgColor;
    }

    public void setFont(final Font font) {
        this.font = font;
    }

    public void setPosition(final Coordinates position) {
        this.position = position;
    }

    public void setSize(final Coordinates size) {
        this.size = size;
    }

    public void setText(final String text) {
        this.text = text;
    }

}
