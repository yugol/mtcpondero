package pondero.tests.elements.interfaces;

import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;

public interface HasPosition {

    public Coordinates _getPosition();

    public void _setPosition(Coordinates position);

    public Coordinate $hposition();

    public Coordinate $vposition();

    public void position(final double x, final double y);

    public void position(final String xExpr, final String yExpr);

}
