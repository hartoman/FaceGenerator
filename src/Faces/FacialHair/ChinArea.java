package  Faces.FacialHair;
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
import  Faces.FunctionalClasses.SymmetricalFeature;
import java.awt.*;



public class ChinArea extends SymmetricalFeature {

    private int soulpatchHeight;          // [0,6]
    private int soulpatchWidth;           // [0,6]


    void drawChinArea(Graphics2D g2d,Color hairColor){

        if ((soulpatchWidth>0)||(soulpatchHeight>0)){

            g2d.setColor(hairColor);

            int soulPatchWidth = width*(1+soulpatchWidth)/7; 
            int soulPatchHeight = height*(1+soulpatchHeight)/6;

            int soulLeftX = left+width/2-soulPatchWidth/2;


            g2d.fillOval(soulLeftX,midy,soulPatchWidth, soulPatchHeight);
            g2d.setColor(Color.black);

        }
        
    }

    void setUpChin(int soulpatchHeight, int soulpatchWidth){
        this.soulpatchHeight=soulpatchHeight;
        this.soulpatchWidth=soulpatchWidth;
    }



    public int getSoulpatchHeight() {
        return soulpatchHeight;
    }

    public void setSoulpatchHeight(int soulpatchHeight) {
        this.soulpatchHeight = soulpatchHeight;
    }

    public int getSoulpatchWidth() {
        return soulpatchWidth;
    }

    public void setSoulpatchWidth(int soulpatchWidth) {
        this.soulpatchWidth = soulpatchWidth;
    }
}

