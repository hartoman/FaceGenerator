package Hair;
import java.awt.*;

import FacialFeatures.Face;
import FunctionalClasses.RectComputer;


public class BackHair {
    
    private boolean hasHair;
    private int length;

    void drawHair(Graphics2D g2d){

        int bHairL = Face.maxwidth()/9-Face.maxwidth()*length/50/9;
        int bHairR = RectComputer.symmetricHorizonal(bHairL);
        int bHairT = Face.minHeight();
        int bHairB = Face.height()+Face.height()*length/50;
        g2d.setColor(Face.hairColor());
        g2d.fillArc(bHairL,bHairT,bHairR-bHairL,bHairB, 0, 180);
        g2d.setColor(Color.black);
        
        
    }

    public boolean hasHair() {
        return hasHair;
    }

    public void hasHair(boolean hasHair) {
        this.hasHair = hasHair;
    }

    public int length() {
        return length;
    }

    public void length(int length) {
        this.length = length;
    }


    
}
