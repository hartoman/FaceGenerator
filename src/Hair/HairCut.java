package Hair;

import java.awt.*;
import java.io.Serializable;


public abstract class HairCut implements Serializable {

    TopOfHead topOfHead;
    Temples temples;
    BackHair backhair;
 //   Color hairColor;

    // creates the haircut out of two parts: hair on the head, and hair in the back
    HairCut() {
        temples = new Temples();
        backhair = new BackHair();

    }

    // draws the part of the hair that is on the head
    public void drawHairCut(Graphics2D g2d,Color hairColor,Color skinColor) {

        
        // templs above top of head
        if (temples.getHasHair()) {
            temples.drawTemples(g2d,hairColor);
        }
        topOfHead.setHaircutColors(hairColor,skinColor);

        topOfHead.drawTopOfHead(g2d);
    }

    // draws the part of the hair that is on the back (longer hair)
    public void drawBackHair(Graphics2D g2d,Color hairColor) {

        if (backhair.getHasHair()) {
            backhair.drawHair(g2d,hairColor);
        }
    }

    //getters
    public Temples getTemples(){return this.temples;}
    public TopOfHead getTopOfHead(){return this.topOfHead;}
    public BackHair getBackHair(){return this.backhair;}
    //setters
    public void setTemples(Temples temples){this.temples=temples;}
    public void setTopOfHead(TopOfHead topOfHead){this.topOfHead=topOfHead;}
    public void setBackHair(BackHair backhair){this.backhair=backhair;}
    

}






/* ******************************* VARIOUS HAIRSTYLES ***************************** */

// length: [0,50]

// old males
class Bald extends HairCut {

    Bald() {

        temples.setHasHair(true)  ;
        topOfHead = new BaldTop();
        backhair.setHasHair(false);
    }
}

// old males
class BaldLongHair extends HairCut {

    BaldLongHair() {

        temples.setHasHair(true)  ;
        topOfHead = new BaldTop();
        backhair.setHasHair(true);
        backhair.setLength(0);
    }
}

// all
class SkinHead extends HairCut {

    SkinHead() {

        super();
        temples.setHasHair(false)  ;
        topOfHead = new BaldTop();
        backhair.setHasHair(false);
    }

}

// all
class Mohawk extends HairCut {

    Mohawk() {
        temples.setHasHair(false)  ;
        topOfHead = new MohawkTop();
        backhair.setHasHair(false);
    }

}

// all
class PartMiddle extends HairCut {

    PartMiddle() {
        temples.setHasHair(true)  ;
        topOfHead = new PartMiddleTop();
        backhair.setHasHair(false) ;
    }
}

// all
class PartSide extends HairCut {

    PartSide() {
        temples.setHasHair(true)  ;
        topOfHead = new PartSideTop();
        backhair.setHasHair(false) ;
    }
}

// all
class LowFringe extends HairCut {

    LowFringe() {
        temples.setHasHair(true)  ;
        topOfHead = new LowFringeTop();
        backhair.setHasHair(false);
    }
}

// all
class HighFringe extends HairCut {

    HighFringe() {
        temples.setHasHair(true) ;
        topOfHead = new HighFringeTop();
        backhair.setHasHair(false);
    }
}

class MiaWallace extends HairCut{
    
    MiaWallace(){
        temples.setHasHair(true)  ;
        topOfHead = new LowFringeTop();
        backhair.setHasHair(true);
        backhair.setLength(0);
    }
}

class PartSideLongHair extends HairCut{

    PartSideLongHair(){
        temples.setHasHair(true)  ;
        topOfHead = new PartSideTop();
        backhair.setHasHair(true) ;
        backhair.setLength(0);
    }
}

class PartMiddleLongHair extends HairCut{

    PartMiddleLongHair(){
        temples.setHasHair(true)  ;
        topOfHead = new PartMiddleTop();
        backhair.setHasHair(true) ;
        backhair.setLength(0);
    }
}

class HighFringeLongHair extends HairCut{
    HighFringeLongHair(){
        temples.setHasHair(true) ;
        topOfHead = new HighFringeTop();
        backhair.setHasHair(true) ;
        backhair.setLength(0);

    }




}

class HightTempleHairCut extends HairCut{

    HightTempleHairCut(){
        temples.setHasHair(false)  ;
        topOfHead = new HighTemples();
        backhair.setHasHair(false) ;
        backhair.setLength(0);
    }
}

class HightTempleLongHair extends HairCut{

    HightTempleLongHair(){
        temples.setHasHair(false) ;
        topOfHead = new HighTemples();
        backhair.setHasHair(true) ;
        backhair.setLength(0);
    }
}