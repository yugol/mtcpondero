package pondero.engine.staples;


public class Coordinate {

    private double      value = 0;
    private MeasureUnit unit  = MeasureUnit.PIXEL;

    public Coordinate(final double value, final MeasureUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Coordinate(String expr) {
        if (expr != null) {
            expr = expr.trim().toLowerCase();
            if (expr.lastIndexOf('%') >= 0) {
                expr = expr.substring(0, expr.length() - 1);
                unit = MeasureUnit.PERCENTAGE;
                value = Double.parseDouble(expr) / 100;
            } else if (expr.lastIndexOf("pt") >= 0) {
                expr = expr.substring(0, expr.length() - 2);
                unit = MeasureUnit.POINT;
                value = Double.parseDouble(expr);
            } else {
                if (expr.lastIndexOf("px") >= 0) {
                    expr = expr.substring(0, expr.length() - 2);
                }
                value = Double.parseDouble(expr);
            }
        }
    }

    public double getAbsoluteValue(final double maxVal, final double size) {
        switch (unit) {
            case PERCENTAGE:
                return (maxVal - size) * value;
            case PIXEL:
                return value;
            default:
        }
        throw new UnsupportedOperationException();
    }

    public MeasureUnit getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

}
