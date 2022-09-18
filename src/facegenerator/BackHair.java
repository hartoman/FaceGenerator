package facegenerator;
import java.awt.*;


public class BackHair {
    
    boolean hasHair;
    int length;

    void drawHair(Graphics2D g2d){

        int bHairL = Face.maxwidth/9-Face.maxwidth*length/50/9;
        int bHairR = RectComputer.symmetricHorizonal(bHairL);
        int bHairT = Face.minHeight;
        int bHairB = Face.height+Face.height*length/50;
        
        g2d.drawRect(bHairL, bHairT, bHairR-bHairL,bHairB);
        g2d.setColor(Color.black);
        g2d.fillArc(bHairL,bHairT,bHairR-bHairL,bHairB, 0, 180);
        

    }

}
