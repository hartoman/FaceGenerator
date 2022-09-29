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
        if (temples.hasHair) {
            temples.drawTemples(g2d,hairColor);
        }
        topOfHead.hairColor(hairColor,skinColor);

        topOfHead.drawTopOfHead(g2d);
    }

    // draws the part of the hair that is on the back (longer hair)
    public void drawBackHair(Graphics2D g2d,Color hairColor) {

        if (backhair.hasHair()) {
            backhair.drawHair(g2d,hairColor);
        }
    }

    //getters
    public Temples Temples(){return this.temples;}
    public TopOfHead TopOfHead(){return this.topOfHead;}
    public BackHair BackHair(){return this.backhair;}
    //setters
    public void Temples(Temples temples){this.temples=temples;}
    public void TopOfHead(TopOfHead topOfHead){this.topOfHead=topOfHead;}
    public void BackHair(BackHair backhair){this.backhair=backhair;}
    

}






/* ******************************* VARIOUS HAIRSTYLES ***************************** */

// length: [0,50]

// old males
class Bald extends HairCut {

    Bald() {

        temples.hasHair = true;
        topOfHead = new BaldTop();
        backhair.hasHair(false);
    }
}

// old males
class BaldLongHair extends HairCut {

    BaldLongHair() {

        temples.hasHair = true;
        topOfHead = new BaldTop();
        backhair.hasHair(true);
        backhair.length(0);
    }
}

// all
class SkinHead extends HairCut {

    SkinHead() {

        super();
        temples.hasHair = false;
        topOfHead = new BaldTop();
        backhair.hasHair(false);
    }

}

// all
class Mohawk extends HairCut {

    Mohawk() {
        temples.hasHair = false;
        topOfHead = new MohawkTop();
        backhair.hasHair(false);
    }

}

// all
class PartMiddle extends HairCut {

    PartMiddle() {
        temples.hasHair = true;
        topOfHead = new PartMiddleTop();
        backhair.hasHair(false) ;
    }
}

// all
class PartSide extends HairCut {

    PartSide() {
        temples.hasHair = true;
        topOfHead = new PartSideTop();
        backhair.hasHair(false) ;
    }
}

// all
class LowFringe extends HairCut {

    LowFringe() {
        temples.hasHair = true;
        topOfHead = new LowFringeTop();
        backhair.hasHair(false);
    }
}

// all
class HighFringe extends HairCut {

    HighFringe() {
        temples.hasHair = true;
        topOfHead = new HighFringeTop();
        backhair.hasHair(false);
    }
}

class MiaWallace extends HairCut{
    
    MiaWallace(){
        temples.hasHair = true;
        topOfHead = new LowFringeTop();
        backhair.hasHair(true);
        backhair.length(0);
    }
}

class PartSideLongHair extends HairCut{

    PartSideLongHair(){
        temples.hasHair = true;
        topOfHead = new PartSideTop();
        backhair.hasHair(true) ;
        backhair.length(0);
    }
}

class PartMiddleLongHair extends HairCut{

    PartMiddleLongHair(){
        temples.hasHair = true;
        topOfHead = new PartMiddleTop();
        backhair.hasHair(true) ;
        backhair.length(0);
    }
}

class HighFringeLongHair extends HairCut{
    HighFringeLongHair(){
        temples.hasHair = true;
        topOfHead = new HighFringeTop();
        backhair.hasHair(true) ;
        backhair.length(0);

    }




}

class HightTempleHairCut extends HairCut{

    HightTempleHairCut(){
        temples.hasHair = false;
        topOfHead = new HighTemples();
        backhair.hasHair(false) ;
        backhair.length(0);
    }
}

class HightTempleLongHair extends HairCut{

    HightTempleLongHair(){
        temples.hasHair = false;
        topOfHead = new HighTemples();
        backhair.hasHair(true) ;
        backhair.length(0);
    }
}