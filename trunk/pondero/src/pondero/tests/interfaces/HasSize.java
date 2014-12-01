package pondero.tests.interfaces;

import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;

public interface HasSize {

    public Coordinate getHeight();

    public Coordinates getSize();

    public Coordinate getWidth();

    public void setHeight(double height);

    public void setHeight(String height);

    public void setSize(Coordinates size);

    public void setSize(double width, double height);

    public void setSize(String width, String height);

    public void setWidth(double width);

    public void setWidth(String width);

}
