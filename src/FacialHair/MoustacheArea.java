package FacialHair;
import FunctionalClasses.*;
import java.awt.*;
import java.awt.geom.*;


public class MoustacheArea extends SymmetricalFeature {

    private int moustacheSize; // [0,30]
    private int curled; // [0,50]

    public void drawMoustache(Graphics2D g2d,Color hairColor) {

        if (moustacheSize>0) {

            if (curled < 0) {
                moustacheSize = Math.max(3, moustacheSize);
            }

            int moustacheEnd = right - width * (1 + moustacheSize) / 10;
            int angleModifier;
            angleModifier = -height * curled / 10;
            Path2D.Double moust = new Path2D.Double();
            moust.moveTo(right, top);

            moust.curveTo(moustacheEnd + (moustacheSize * 9 / 10), top, moustacheEnd + moustacheSize * 1 / 10, top,
                    moustacheEnd, top + angleModifier * 2 / 3);
            moust.curveTo(moustacheEnd + (moustacheSize * 1 / 10), top + height * 2 / 3,
                    moustacheEnd + (moustacheSize * 9 / 10), top + height / 3, right, top + height / 2);

            Shape moust2 = drawMirrored(moust);
            g2d.draw(moust);
            g2d.draw(moust2);
            g2d.setColor(hairColor);
            g2d.fill(moust);
            g2d.fill(moust2);
            g2d.setColor(Color.black);

        }

    }

    public void setUpMoustache(int size,int curled){
        this.moustacheSize=size;
        this.curled=curled;
    }

    public int getMoustacheSize() {
        return moustacheSize;
    }

    public void setMoustacheSize(int moustacheSize) {
        this.moustacheSize = moustacheSize;
    }

    public int getCurled() {
        return curled;
    }

    public void setCurled(int curled) {
        this.curled = curled;
    }

    
}