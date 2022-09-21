package facegenerator.Hair;

import java.awt.*;

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
    public void drawHairCut(Graphics2D g2d) {

        if (temples.hasHair) {
            temples.drawTemples(g2d);
        }
        topOfHead.drawTopOfHead(g2d);

    }

    // draws the part of the hair that is on the back (longer hair)
    public void drawBackHair(Graphics2D g2d) {

        if (backhair.hasHair) {
            backhair.drawHair(g2d);
        }
    }

    public Temples getTemples(){return this.temples;}
    public TopOfHead getTopOfHead(){return this.topOfHead;}
    public BackHair getBackHair(){return this.backhair;}
    public void setTemples(Temples temples){this.temples=temples;}
    public void setTopOfHead(TopOfHead topOfHead){this.topOfHead=topOfHead;}
    public void setBackHair(BackHair backhair){this.backhair=backhair;}
    

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