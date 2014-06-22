package pondero.engine.staples;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Coordinates {

    private Coordinate x;
    private Coordinate y;

    public Coordinates(final double x, final double y) {
        setX(x);
        setY(y);
    }

    public Coordinates(final String x, final String y) {
        setX(x);
        setY(y);
    }

    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
    }

    public void setX(final double x) {
        this.x = new Coordinate(x, MeasureUnit.PIXEL);
    }

    public void setX(final String x) {
        this.x = new Coordinate(x);
    }

    public void setY(final double y) {
        this.y = new Coordinate(y, MeasureUnit.PIXEL);
    }

    public void setY(final String y) {
        this.y = new Coordinate(y);
    }

    public Point2D.Double toPoint(final double surfaceWidth, final double surfaceHeight) {
        return toPoint(surfaceWidth, surfaceHeight, 0, 0);
    }

    public Point2D.Double toPoint(final double surfaceWidth, final double surfaceHeight, final double stimulusWidth, final double stimulusHeight) {
        final double absX = x.getAbsoluteValue(surfaceWidth, stimulusWidth);
        final double absY = y.getAbsoluteValue(surfaceHeight, stimulusHeight);
        return new Point2D.Double(absX, absY);
    }

    public Point2D.Double toPoint(final double surfaceWidth, final double surfaceHeight, final Rectangle2D stimulusBounds) {
        return toPoint(surfaceWidth, surfaceHeight, stimulusBounds.getWidth(), stimulusBounds.getHeight());
    }

}
