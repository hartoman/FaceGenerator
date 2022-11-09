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
package  Faces.FacialHair;

import java.awt.*;
import java.io.Serializable;

public class FacialHair implements Serializable {

    private MoustacheArea moustache;
    private ChinArea chin;
    private Cheeks cheeks;

    public FacialHair() {

        moustache = new MoustacheArea();
        chin = new ChinArea();
        cheeks= new Cheeks();
    }

    public void drawFacialHair(Graphics2D g2d, Color hairColor) {
        
        cheeks.drawBeardArea(g2d, hairColor);
        chin.drawChinArea(g2d, hairColor);
        moustache.drawMoustache(g2d, hairColor);

    }

    public void setFacialHair(int moustacheSize, int curled,
            int soulpatchHeight, int soulpatchWidth,
            int beardLength, int beardWidth) {

        this.getMoustache().setMoustacheSize(moustacheSize);
        this.getMoustache().setCurled(curled);
        this.getChinArea().setSoulpatchHeight(soulpatchHeight);
        this.getChinArea().setSoulpatchWidth(soulpatchWidth);
        this.getCheeks().setBeardLength(beardLength);
        this.getCheeks().setBeardWidth(beardWidth);

    }

    public Cheeks getCheeks() {
        return cheeks;
    }

    public void setCheeks(Cheeks cheeks) {
        this.cheeks = cheeks;
    }

    public MoustacheArea getMoustache() {
        return moustache;
    }

    public void setMoustache(MoustacheArea moustache) {
        this.moustache = moustache;
    }

    public ChinArea getChinArea() {
        return chin;
    }

    public void setChinArea(ChinArea chin) {
        this.chin = chin;
    }

}
