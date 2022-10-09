package Hair;
import java.awt.*;

import FacialFeatures.Face;
import FunctionalClasses.RectComputer;
import java.io.Serializable;



public class BackHair implements Serializable{
    
    private boolean hasHair;
    private int length;     //  [0,50]

    void drawHair(Graphics2D g2d,Color hairColor){

        int bHairL = Face.getMaxwidth()/9-Face.getMaxwidth()*length/50/9;
        int bHairR = RectComputer.symmetricHorizonal(bHairL);
        int bHairT = Face.getMinHeight();
        int bHairB = Face.getHeight()+Face.getHeight()*length/50;
        g2d.setColor(hairColor);
        g2d.fillArc(bHairL,bHairT,bHairR-bHairL,bHairB, 0, 180);
        g2d.setColor(Color.black);
        
        
    }

    public boolean getHasHair() {
        return hasHair;
    }

    public void setHasHair(boolean hasHair) {
        this.hasHair = hasHair;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    
}
