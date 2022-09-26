package FacialFeatures;
import java.awt.*;
import java.awt.geom.*;
import FunctionalClasses.SymmetricalFeature;

public class Ears extends SymmetricalFeature {

    private int earSize;

    void drawEars(Graphics2D g2d) {

        int earMidpoint = ((left + width / 2) - width * earSize / 100);

        int bh1x = right;// earMidpoint+width/2;//+width*2/3;
        int bh1y = top;
        int bh2x = left;// +width/5;

        int bh3x = bh2x + width * 2 / 3;
        int bh3y = bottom;
        int bh4x = left + width;
        int bh4y = bottom + height / 3;


        Path2D.Double ear = new Path2D.Double();
        ear.moveTo(right, top + height / 3);
        ear.curveTo(bh1x - earSize, bh1y, bh2x - earSize, bh1y, earMidpoint + width / 4, midy + height / 3);
        ear.curveTo(bh3x - earSize, bh3y, bh4x - earSize, bh4y, right, top + height * 4 / 5);
        ear.closePath();

        Shape ear2 = drawMirrored(ear);
        g2d.draw(ear);
        g2d.draw(ear2);
        g2d.setColor(Face.skinColor());
        g2d.fill(ear);
        g2d.fill(ear2);
        g2d.setColor(Color.black);

        Path2D.Double inEar = new Path2D.Double();
        inEar.moveTo(right - width / 9, top + height / 3);
        inEar.curveTo(bh1x - earSize, bh1y + width / 9, bh2x - earSize, bh1y + width / 9, earMidpoint + width / 3,
                midy + height / 3);
        g2d.draw(inEar);
        g2d.draw(drawMirrored(inEar));

        Path2D.Double earPore = new Path2D.Double();
        earPore.moveTo(right - width / 8, midy + height / 3);
        earPore.lineTo(right - width / 8, top + height * 3 / 4);
        earPore.curveTo(bh1x - width / 8 + earSize / 8, midy, bh2x + height * 2 / 5 + earSize / 8, midy,
                right - width / 8, midy + height / 3);
        earPore.closePath();

        Shape earPore2 = drawMirrored(earPore);
        g2d.draw(earPore);
        g2d.draw(earPore2);
        g2d.fill(earPore);
        g2d.fill(earPore2);

    }

    public int earSize() {
        return earSize;
    }

    public void earSize(int earSize) {
        this.earSize = earSize;
    }




}