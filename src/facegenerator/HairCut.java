package facegenerator;

import java.awt.*;
import java.awt.geom.*;

public abstract class HairCut {

    TopOfHead topOfHead;
    Temples temples;
    BackHair backhair;

    // creates the haircut out of two parts: hair on the head, and hair in the back
    HairCut() {
        temples = new Temples();
        backhair = new BackHair();
    }

    // draws the part of the hair that is on the head
    void drawHairCut(Graphics2D g2d) {

        if (temples.hasHair) {
            temples.drawTemples(g2d);
        }
        topOfHead.drawTopOfHead(g2d);

    }

    // draws the part of the hair that is on the back (longer hair)
    void drawBackHair(Graphics2D g2d) {

        if (backhair.hasHair) {
            backhair.drawHair(g2d);
        }
    }

    
    // hair of the temple area -- exists in all hairstyles
    class Temples extends SymmetricalFeature {

        boolean hasHair;

        void drawTemples(Graphics2D g2d) {

            Path2D.Double temple = new Path2D.Double();
            temple.moveTo(left * 10 / 10, bottom);
            temple.curveTo(left * 9 / 10, top + height * 2 / 3, left, top + height / 3, left + width / 2, top);
            temple.curveTo(left + width / 2, top + height / 10, left + width / 2, top + height / 10,
                    left + width * 11 / 10, top + height / 10);
            temple.curveTo(left, bottom * 11 / 10, right, bottom * 11 / 10, left, bottom);
            temple.closePath();

            Shape temple2 = drawMirrored(temple);
            g2d.draw(temple);
            g2d.draw(temple2);
            g2d.setColor(Face.hairColor);
            g2d.fill(temple);
            g2d.fill(temple2);
            g2d.setColor(Color.black);

        }
    }


    /************************************************************** */

// the hair on the back of the head
    class BackHair {
    
        boolean hasHair;
        int length;                 // [0,50]
    
        void drawHair(Graphics2D g2d){
    
            int bHairL = Face.maxwidth/9-Face.maxwidth*length/50/9;
            int bHairR = RectComputer.symmetricHorizonal(bHairL);
            int bHairT = Face.minHeight;
            int bHairB = Face.height+Face.height*length/50;
            
            g2d.drawRect(bHairL, bHairT, bHairR-bHairL,bHairB);
            g2d.setColor(Face.hairColor);
            g2d.fillArc(bHairL,bHairT,bHairR-bHairL,bHairB, 0, 180);
            g2d.setColor(Color.black);
        }
    
    }



}






/* ******************************* VARIOUS HAIRSTYLES ***************************** */

// old males
class Bald extends HairCut {

    Bald() {

        temples.hasHair = true;
        topOfHead = new BaldTop();
        backhair.hasHair=false;
    }
}

// old males
class BaldLongHair extends HairCut {

    BaldLongHair() {

        temples.hasHair = true;
        topOfHead = new BaldTop();
        backhair.hasHair=true;
        backhair.length=45;
    }
}

// all
class SkinHead extends HairCut {

    SkinHead() {

        super();
        temples.hasHair = false;
        topOfHead = new BaldTop();
        backhair.hasHair= false;
    }

}

// all
class Mohawk extends HairCut {

    Mohawk() {
        temples.hasHair = false;
        topOfHead = new MohawkTop();
        backhair.hasHair= false;
    }

}

// all
class PartMiddle extends HairCut {

    PartMiddle() {
        temples.hasHair = true;
        topOfHead = new PartMiddleTop();
        backhair.hasHair= false;
    }
}

// all
class PartSide extends HairCut {

    PartSide() {
        temples.hasHair = true;
        topOfHead = new PartSideTop();
        backhair.hasHair= false;
    }
}

// all
class LowFringe extends HairCut {

    LowFringe() {
        temples.hasHair = true;
        topOfHead = new LowFringeTop();
        backhair.hasHair= false;
    }
}

// all
class HighFringe extends HairCut {

    HighFringe() {
        temples.hasHair = true;
        topOfHead = new HighFringeTop();
        backhair.hasHair= false;
    }
}

class MiaWallace extends HairCut{
    
    MiaWallace(){
        temples.hasHair = true;
        topOfHead = new LowFringeTop();
        backhair.hasHair= true;
        backhair.length=40;
    }
}