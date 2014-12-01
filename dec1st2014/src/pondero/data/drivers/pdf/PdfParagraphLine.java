package pondero.data.drivers.pdf;

class PdfParagraphLine {

    private String text;
    private float  xDelta;
    private float  yDelta;

    public String getText() {
        return text;
    }

    public float getxDelta() {
        return xDelta;
    }

    public float getyDelta() {
        return yDelta;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public void setxDelta(final float xDelta) {
        this.xDelta = xDelta;
    }

    public void setyDelta(final float yDelta) {
        this.yDelta = yDelta;
    }

}
