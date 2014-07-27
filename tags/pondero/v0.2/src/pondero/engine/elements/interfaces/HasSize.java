package pondero.engine.elements.interfaces;

import pondero.engine.staples.Coordinate;
import pondero.engine.staples.Coordinates;

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
