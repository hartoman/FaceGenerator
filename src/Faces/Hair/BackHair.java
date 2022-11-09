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

import  Faces.FacialFeatures.Face;
import  Faces.FunctionalClasses.AssistingMethods;
import java.io.Serializable;



public class BackHair implements Serializable{
    
    private boolean hasHair;
    private int length;     //  [0,50]

    void drawHair(Graphics2D g2d,Color hairColor){

        int bHairL = Face.getMaxwidth()/9-Face.getMaxwidth()*length/50/9;
        int bHairR = AssistingMethods.symmetricHorizonal(bHairL);
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
