package pondero.data.evaluation;


public class DrawingFeature {

//    protected int separator            = '\n';
//    ByteBuffer    content              = ByteBuffer.allocate(10);
//    final float   DEFAULT_BORDER_SIZE  = 1.0f;
//    final Color   DEFAULT_BORDER_COLOR = Color.BLACK;
//
//    public void circle(final float x, final float y, final float r) {
//        final float b = 0.5523f;
//        moveTo(x + r, y);
//        curveTo(x + r, y + r * b, x + r * b, y + r, x, y + r);
//        curveTo(x - r * b, y + r, x - r, y + r * b, x - r, y);
//        curveTo(x - r, y - r * b, x - r * b, y - r, x, y - r);
//        curveTo(x + r * b, y - r, x + r, y - r * b, x + r, y);
//    }
//
//    public void curveTo(final float x1, final float y1, final float x2, final float y2, final float x3, final float y3) {
//        content.append(x1).append(' ').append(y1).append(' ').append(x2).append(' ').append(y2).append(' ').append(x3).append(' ').append(y3).append(" c").append_i(separator);
//    }
//
//    public void drawCircle(final PDDocument document, final int pageNumber, final float x, final float y, final float r) throws Exception {
//        drawCircle(document, pageNumber, x, y, r, DEFAULT_BORDER_SIZE, DEFAULT_BORDER_COLOR);
//    }
//
//    public void drawCircle(final PDDocument document, final int pageNumber, final float x, final float y, final float r, final float borderSize, final Color borderColor) throws Exception {
//        final PDPage page = (PDPage) document.getDocumentCatalog().getAllPages().get(pageNumber);
//        final PDPageContentStream contentStream = new PDPageContentStream(document, page, true, false);
//        drawCircle(contentStream, x, y, r, borderSize, borderColor);
//    }
//
//    public void drawCircle(final PDPageContentStream pageContentStream, final float x, final float y, final float r, final float borderSize, final Color borderColor) throws Exception {
//        setLineWidth(borderSize);
//        setRGBColorStroke(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue());
//        circle(x, y, r);
//        stroke();
//        pageContentStream.appendRawCommands(content.getBuffer());
//        pageContentStream.close();
//    }
//
//    public void moveTo(final float x, final float y) {
//        content.append(x).append(' ').append(y).append(" m").append_i(separator);
//    }
//
//    public void setLineWidth(final float w) {
//        content.append(w).append(" w").append_i(separator);
//    }
//
//    public void setRGBColorStroke(final int red, final int green, final int blue) {
//        HelperRGB((float) (red & 0xFF) / 0xFF, (float) (green & 0xFF) / 0xFF, (float) (blue & 0xFF) / 0xFF);
//        content.append(" RG").append_i(separator);
//    }
//
//    public void stroke() {
//        content.append("S").append_i(separator);
//    }
//
//    private void HelperRGB(float red, float green, float blue) {
//        if (red < 0) {
//            red = 0.0f;
//        } else if (red > 1.0f) {
//            red = 1.0f;
//        }
//        if (green < 0) {
//            green = 0.0f;
//        } else if (green > 1.0f) {
//            green = 1.0f;
//        }
//        if (blue < 0) {
//            blue = 0.0f;
//        } else if (blue > 1.0f) {
//            blue = 1.0f;
//        }
//        content.append(red).append(' ').append(green).append(' ').append(blue);
//    }

}
