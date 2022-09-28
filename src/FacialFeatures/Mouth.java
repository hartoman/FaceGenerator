package FacialFeatures;
import java.awt.*;
import java.awt.geom.*;
import FunctionalClasses.*;


public  class Mouth extends SymmetricalFeature {
    
    private int mouthSize, // [0,40]
    smile,                  // [-15,15]
    openness,               // [0,40]
    lipSize;                // [0,30]

    void drawMouth(Graphics2D g2d) {

      
        int midx = (left + width / 2);
        int mouthpointUL1 = midx - width / 5 - mouthSize - 0;
        int mouthpointUR1 = midx + width / 5 + mouthSize + 0;
        int mouthULY = midy - ((height * smile) / 100);
        int mouthLLY = midy + (height * smile / 100);
        int bh1X = left + (width * 2 / 5);
        int bh1Y = mouthULY - openness + smile;
        int bh2X = RectComputer.symmetricHorizonal(bh1X);
        int bh2Y = mouthLLY + openness + smile;

        // lips
        Path2D.Double lips = new Path2D.Double();
        lips.moveTo(mouthpointUL1, mouthULY);
        lips.curveTo(bh1X, bh1Y - lipSize, bh2X, bh1Y - lipSize, mouthpointUR1, mouthULY);
        lips.curveTo(bh2X, bh2Y + lipSize, bh1X, bh2Y + lipSize, mouthpointUL1, mouthULY);
        lips.closePath();

        // mouth
        Path2D.Double mouth = new Path2D.Double();
        mouth.moveTo(mouthpointUL1, mouthULY);
        mouth.curveTo(bh1X, bh1Y, bh2X, bh1Y, mouthpointUR1, mouthULY);
        mouth.curveTo(bh2X, bh2Y, bh1X, bh2Y, mouthpointUL1, mouthULY);
        mouth.closePath();

        g2d.setColor(Face.lipsColor());
        g2d.draw(lips);
        g2d.fill(lips);
        g2d.setColor(Color.black);
        g2d.draw(mouth);
        g2d.fill(mouth);

        // TODO: teeth
        /*
         * g2d.setColor(Color.white);
         * Path2D.Double teeth1 = new Path2D.Double();
         * teeth1.moveTo(mouthpointUL1, mouthULY);
         * teeth1.curveTo(bh1X, bh1Y, bh2X, bh1Y, mouthpointUR1, mouthULY);
         * teeth1.curveTo(bh1X, bh1Y, bh2X, bh1Y, mouthpointUL1, mouthULY);
         * teeth1.closePath();
         * g2d.draw(teeth1);
         * g2d.fill(teeth1);
         * 
         * g2d.setColor(Color.black);
         */
    
    }

    public int mouthSize() {
        return mouthSize;
    }

    public void mouthSize(int mouthSize) {
        this.mouthSize = mouthSize;
    }

    public int smile() {
        return smile;
    }

    public void smile(int smile) {
        this.smile = smile;
    }

    public int openness() {
        return openness;
    }

    public void openness(int openness) {
        this.openness = openness;
    }

    public int lipSize() {
        return lipSize;
    }

    public void lipSize(int lipSize) {
        this.lipSize = lipSize;
    }

    public void setMouth(int mouthSize,int lipSize){
        this.mouthSize=mouthSize;
        this.lipSize=lipSize;
    }
    
}
