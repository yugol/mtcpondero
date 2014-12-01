package pondero.data.drivers.pdf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PdfParagraph implements Iterable<PdfParagraphLine> {

    public static enum HAlign {
        LEFT, CENTER, RIGHT
    }

    public static enum VAlign {
        TOP, MIDDLE, BOTTOM
    }

    private final PdfPageCanvas canvas;
    private final String        text;
    private Float               width;
    private Float               height;
    private HAlign              hAlign = HAlign.CENTER;
    private VAlign              vAlign = VAlign.MIDDLE;
    private boolean             wrap   = false;

    PdfParagraph(final PdfPageCanvas canvas, final String text) {
        this.canvas = canvas;
        this.text = PdfUtil.ro(text);
    }

    public HAlign gethAlign() {
        return hAlign;
    }

    public Float getHeight() {
        return height;
    }

    public VAlign getvAlign() {
        return vAlign;
    }

    public Float getWidth() {
        return width;
    }

    public boolean isWrap() {
        return wrap;
    }

    @Override
    public Iterator<PdfParagraphLine> iterator() {
        try {
            final List<PdfParagraphLine> lines = new ArrayList<>();
            final PdfParagraphLine line = new PdfParagraphLine();
            line.setText(text);
            if (width != null && hAlign != HAlign.LEFT) {
                final float lineWidth = canvas.getStringWidth(line.getText());
                if (hAlign == HAlign.CENTER) {
                    line.setxDelta((width - lineWidth) / 2);
                } else if (hAlign == HAlign.RIGHT) {
                    line.setxDelta(width - lineWidth);
                }
            }
            if (height != null && vAlign != VAlign.BOTTOM) {
                final float lineHeight = canvas.getStringHeight() * 0.75f;
                if (vAlign == VAlign.MIDDLE) {
                    line.setyDelta((height - lineHeight) / 2);
                } else if (vAlign == VAlign.TOP) {
                    line.setyDelta(height - lineHeight);
                }
            }
            lines.add(line);
            return lines.iterator();
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void sethAlign(final HAlign hAlign) {
        this.hAlign = hAlign;
    }

    public void setHeight(final Float height) {
        this.height = height;
    }

    public void setvAlign(final VAlign vAlign) {
        this.vAlign = vAlign;
    }

    public void setWidth(final Float width) {
        this.width = width;
    }

    public void setWrap(final boolean wrap) {
        this.wrap = wrap;
    }

}
