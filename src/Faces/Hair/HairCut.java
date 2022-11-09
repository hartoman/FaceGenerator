// Copyright © 2022 Christos Chartomatsidis

/*
 This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version. This program is distributed in the hope that it will be
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
    General Public License for more details. You should have received a copy of the GNU 
    General Public License along with this program. If not, see http://www.gnu.org/licenses/. 

 */

 package  Faces.Hair;

import java.awt.*;
import java.io.Serializable;


public abstract class HairCut implements Serializable {

    protected TopOfHead topOfHead;
    protected Temples temples;
    protected BackHair backhair;

 //   protected Color hairColor, skinColor;
 //   protected int age;

    // creates the haircut out of two parts: hair on the head, and hair in the back
    HairCut() {
        temples = new Temples();
        backhair = new BackHair();

/*
        Color hairColor, Color skinColor,int age
        this.hairColor=hairColor();
        this.skinColor=skinColor();
        this.age=age;
 */

    }

    

    // draws the part of the hair that is on the head
    public void drawHairCut(Graphics2D g2d, Color hairColor, Color skinColor, int age) {

        
        // templs above top of head
        if (temples.getHasHair()) {
            temples.drawTemples(g2d,hairColor);
        }
        topOfHead.setHaircutColors(hairColor,skinColor);
        topOfHead.setAge(age);

        topOfHead.drawTopOfHead(g2d);
        topOfHead.drawForeheadWrinkes(g2d);




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

// all
class SpikyHair extends HairCut {

    SpikyHair() {
        temples.setHasHair(false) ;
        topOfHead = new Spikes();
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