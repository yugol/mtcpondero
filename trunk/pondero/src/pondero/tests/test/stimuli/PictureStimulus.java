package pondero.tests.test.stimuli;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import pondero.tests.elements.stimulus.Picture;
import pondero.tests.staples.Coordinates;

public class PictureStimulus extends VisualStimulus {

    private BufferedImage imgData;
    private Rectangle2D   imgBounds;
    private Coordinates   position;
    private Coordinates   size;

    public PictureStimulus(final Picture parent) {
        super(parent);
    }

    public BufferedImage getImgData() {
        return imgData;
    }

    @Override
    public Picture getParent() {
        return (Picture) super.getParent();
    }

    @Override
    public void render(final Graphics2D g2d, final int surfaceWidth, final int surfaceHeight) {
        Rectangle2D bounds = null;
        if (size == null) {
            bounds = imgBounds;
        } else {
            final Point2D wh = size.toPoint(surfaceWidth, surfaceHeight);
            bounds = new Rectangle2D.Double(0, 0, wh.getX(), wh.getY());
        }
        final Point2D pos = position.toPoint(surfaceWidth, surfaceHeight, bounds);
        final int x = (int) pos.getX();
        final int y = (int) pos.getY();
        g2d.drawImage(imgData, x, y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
    }

    public void setPath(final String path) throws IOException {
        final File imgFile = new File(path);
        if (imgFile.exists()) {
            // try to open the file directly
            imgData = ImageIO.read(imgFile);
        } else {
            final String folder = "/" + getParent().getTest().getClass().getPackage().getName().replace(".", "/") + "/";
            final String source = folder + path;
            final InputStream imageStream = getParent().getTest().getClass().getResourceAsStream(source);
            imgData = ImageIO.read(imageStream);
            imageStream.close();
        }

        imgBounds = new Rectangle2D.Double(0, 0, imgData.getWidth(), imgData.getHeight());
    }

    public void setPosition(final Coordinates position) {
        this.position = position;
    }

    public void setSize(final Coordinates size) {
        this.size = size;
    }

}
