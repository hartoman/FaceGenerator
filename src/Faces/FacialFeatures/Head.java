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
import  Faces.FunctionalClasses.AssistingMethods;
import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

// defines the main features-outline of the head.
// The rest of the features are drawn on basis of these
public class Head implements Serializable{

   private int thiccness; // larger number == fatter, values range [0-50]
   private int headShape;// ,mod2,mod4,mod3;

   private int bXL1, bXR1, bY1; // bezier handle 1 (ear-eye axis) -- x for L, R and Y is the same
   private int bXL2, bXR2, bY2; // bezier handle 2 (nose-mouth axis) -- x for L, R and Y is the same

   
    // calculates head size
    public void calcHead(Face face) {

        int mod2 = 5; // [0,10]
        int mod4 = 115; // [110,120]
        int mod3 = 50 - (thiccness + headShape);// / 2;

        bXL1 = Face.getMidWidth() * headShape / 120;
        bY1 = (Face.getMinHeight() + ((Face.getHeight() * mod2) / 120));

        bXL2 = Face.getMidWidth() * mod3 / 120;
        bY2 = (Face.getHeight() - (Face.getMinHeight() * mod4) / 120);

        // calculates the other half of the head
        bXR1 = AssistingMethods.symmetricHorizonal(bXL1);
        bXR2 = AssistingMethods.symmetricHorizonal(bXL2);

        // sets the rectangle boundary of the left half of the face
        face.setHalfFace( AssistingMethods.getBoundingBox(Math.max(bXL1, bXL2), Face.getMinHeight(), Face.getMidWidth(), Face.getHeight()));

    }

    // draws the overall head shape
    public void drawHead(Graphics2D g2d,Color skincColor) {

        Path2D.Double head = new Path2D.Double();

        // draws face:
        head.moveTo(Face.getMidWidth(), Face.getMinHeight());
        // left side
        head.curveTo(bXL1, bY1,
                bXL2, bY2,
                Face.getMidWidth(), Face.getHeight());

        // right side
        head.curveTo(bXR2, bY2,
                bXR1, bY1,
                Face.getMidWidth(), Face.getMinHeight());

        // finish off head
        head.closePath();

        g2d.draw(head);
        g2d.setColor(skincColor);
        g2d.fill(head); // with skin color

   //     g2d.setColor(Color.red);
   //     g2d.drawLine(bXL1,bY1,bXR1,bY1);

        g2d.setColor(Color.black);

    }

    public void setHead(int thiccness,int mod1){
        this.thiccness=thiccness;
        this.headShape=mod1;
    }

    //getters
    public int getBXL1() {
        return bXL1;
    }

    public int getBY1() {
        return bY1;
    }

    public int getBXL2() {
        return bXL2;
    }

    public int getBY2() {
        return bY2;
    }



    //more getters

    public int getThiccness() {
        return thiccness;
    }

    public int getHeadShape() {
        return headShape;
    }
    
    public int[] getBezhandles(){

        int bezHandles[] = new int[4];
        bezHandles[0] = bXL1;
        bezHandles[1] = bY1;
        bezHandles[2] = bXL2;
        bezHandles[3] = bY2;

        return bezHandles;
    }

    //setters

    public void setThiccness(int thiccness) {
        this.thiccness = thiccness;
    }

    public void setHeadShape(int headShape) {
        this.headShape = headShape;
    }
    
}