package pondero.tests.elements.interfaces;

import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;

public interface HasPosition {

    public Coordinate getHPosition();

    public Coordinates getPosition();

    public Coordinate getVPosition();

    public void setPosition(Coordinates position);

    public void setPosition(final double x, final double y);

    public void setPosition(final String xExpr, final String yExpr);

}
