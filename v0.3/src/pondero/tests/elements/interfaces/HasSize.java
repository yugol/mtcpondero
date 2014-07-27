package pondero.tests.elements.interfaces;

import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;

public interface HasSize {

    public Coordinates _getSize();

    public void _setSize(Coordinates size);

    public Coordinate $height();

    public Coordinate $width();

    public void height(double height);

    public void height(String height);

    public void size(double width, double height);

    public void size(String width, String height);

    public void width(double width);

    public void width(String width);
}
