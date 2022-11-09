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
package  Faces.FacialFeatures;
import java.awt.*;
import java.awt.geom.*;
import  Faces.FunctionalClasses.SymmetricalFeature;

public     class Eyebrows extends SymmetricalFeature {

    private int eyebrowSize;    //  [0,25]
    private int anger;          //  []
    private int eyebrowThiccness;   //  [0,4]
    private int angerpointL, angerpointR, widthEB;
    // beginX always on left, beginY based on anger
    

    void drawEyebrows(Graphics2D g2d,Color ...colors) {

        angerpointL = midy + height * (-anger) / 100;
        angerpointR = midy + height * (+anger) / 100;
        widthEB = eyebrowThiccness * (width * (eyebrowSize + 75) / 100) / 9;

        int lengthEB = (width * (eyebrowSize + 75) / 100);
        int bh1x = left + lengthEB * 1 / 3;
        int bh2x = left + lengthEB * 2 / 3;
        int bh3x = left + lengthEB;

        Path2D.Double eyebrow = new Path2D.Double();
        eyebrow.moveTo(left, angerpointL);
        eyebrow.curveTo(bh1x, angerpointL - widthEB / 2, bh2x,
                angerpointL - widthEB / 2, bh3x, angerpointR);
        eyebrow.lineTo(bh3x, angerpointR + widthEB);
        eyebrow.curveTo(bh2x, angerpointL, bh1x, angerpointL,
                left, angerpointL + widthEB);
        eyebrow.closePath();

        Shape eyebrow2 = drawMirrored(eyebrow);

        g2d.draw(eyebrow);
        g2d.draw(eyebrow2);
        g2d.setColor(colors[0]);
        g2d.fill(eyebrow2);
        g2d.fill(eyebrow);
        g2d.setColor(Color.black);
    }



    public int getEyebrowSize() {
        return eyebrowSize;
    }

    public void setEyebrowSize(int eyebrowSize) {
        this.eyebrowSize = eyebrowSize;
    }

    public int getAnger() {
        return anger;
    }

    public void setAnger(int anger) {
        this.anger = anger;
    }

    public int getEyebrowThiccness() {
        return eyebrowThiccness;
    }

    public void setEyebrowThiccness(int eyebrowThiccness) {
        this.eyebrowThiccness = eyebrowThiccness;
    }


    public void setEyebrows(int eyebrowSize,int eyebrowThiccness){
        this.eyebrowSize=eyebrowSize;
        this.eyebrowThiccness=eyebrowThiccness;

    }



    
}
