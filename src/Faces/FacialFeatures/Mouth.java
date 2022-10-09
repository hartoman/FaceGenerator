package FacialFeatures;
import java.awt.*;
import java.awt.geom.*;
import FunctionalClasses.*;


public  class Mouth extends SymmetricalFeature {
    
    private int mouthSize, // [0,40]
    smile,                  // [-15,15]
    openness,               // [0,40]
    lipSize;                // [0,30]

    
    void drawMouth(Graphics2D g2d,Color lipscColor) {

      
        int midx = (left + width / 2);
        int mouthpointUL1 = midx - width / 5 - mouthSize;
        int mouthpointUR1 = midx + width / 5 + mouthSize;
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

        g2d.setColor(lipscColor);
        g2d.draw(lips);
        g2d.fill(lips);
        g2d.setColor(Color.black);
        g2d.draw(mouth);
      //  g2d.setColor(Color.white);
        g2d.fill(mouth);
      //  g2d.setColor(Color.red);

        //TODO FIX THE Ys

    //    int mouthsize = mouthpointUR1-mouthpointUL1;
    //    g2d.drawLine(mouthpointUL1+mouthsize/4, bh1Y, mouthpointUL1+mouthsize/4, bh1Y);
   //     g2d.drawLine(mouthpointUL1+mouthsize/2, bh1Y, mouthpointUL1+mouthsize/2, bh2Y);
   //     g2d.drawLine(mouthpointUL1+mouthsize*3/4, bh1Y, mouthpointUL1+mouthsize*3/4, bh2Y);
   
   g2d.setColor(Color.black);
   
        

    
    }

    public int getMouthSize() {
        return mouthSize;
    }

    public void setMouthSize(int mouthSize) {
        this.mouthSize = mouthSize;
    }

    public int getSmile() {
        return smile;
    }

    public void setSmile(int smile) {
        this.smile = smile;
    }

    public int getOpenness() {
        return openness;
    }

    public void setOpenness(int openness) {
        this.openness = openness;
    }

    public int getLipSize() {
        return lipSize;
    }

    public void setLipSize(int lipSize) {
        this.lipSize = lipSize;
    }

    public void setMouth(int mouthSize,int lipSize){
        this.mouthSize=mouthSize;
        this.lipSize=lipSize;
    }
    
}
