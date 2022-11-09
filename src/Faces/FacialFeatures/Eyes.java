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

public class Eyes extends SymmetricalFeature {
    // the further from 0, the wider the eye becomes
    private int upperEyelidSize, // for upper eyeball -- [-10,10]
            lowerEyelidSize, // for lower eyeball -- [-10,10]
            eyeOpenness; // eyelid openness-- [0,75], 0=closed, 75=wide open

    private int eyeAngle; // angle of the shape of the eyes
    private int eyeSize;                   // [10,35]
    private int eyeDist;               // [5,15]
    

    void drawEyes(Graphics2D g2d,Color ... colors) {

        drawEyeball(g2d,colors[0]);
        drawPupil(g2d,colors[1]);
        drawEyelid(g2d,colors[2]);

    }

    void drawEyeball(Graphics2D g2d,Color eyeballcolor) {

        int left1 = left;
        int right1 = (left + width);
        int bhu1 = left + (width / 3);
        int bhu2 = left + (width * 2 / 3);

        Path2D.Double eye = new Path2D.Double();
        eye.moveTo(left1, midy - eyeAngle);
        eye.curveTo(bhu1, top + upperEyelidSize, bhu2, top + upperEyelidSize,
                right1, midy + eyeAngle);
        eye.curveTo(bhu2, bottom + lowerEyelidSize, bhu1,
                bottom + lowerEyelidSize,
                left1, midy - eyeAngle);
        eye.closePath();

        Shape eye2 = drawMirrored(eye);

        // paint eyeball
        g2d.draw(eye);
        g2d.draw(eye2);
        g2d.setColor(eyeballcolor);
        g2d.fill(eye);
        g2d.fill(eye2);

        g2d.setColor(Color.black);
    }

    void drawPupil(Graphics2D g2d,Color irisColor) {

        Ellipse2D.Double iris = new Ellipse2D.Double();
        Ellipse2D.Double pupil = new Ellipse2D.Double();
        iris = new Ellipse2D.Double(left + width / 3, top + height / 4, width / 3, height * 4 / 6);
        pupil = new Ellipse2D.Double(left + width * 4 / 9, top + height / 2, width / 9, width / 9);

        // create iris
        Shape iris2 = drawMirrored(iris);
        Shape pupil2 = drawMirrored(pupil);

        g2d.draw(iris);
        g2d.draw(iris2);
        g2d.setColor(irisColor);
        g2d.fill(iris);
        g2d.fill(iris2);
        g2d.setColor(Color.black);

        // draws the pupis
        g2d.draw(pupil);
        g2d.draw(pupil2);
        g2d.fill(pupil2);
        g2d.fill(pupil);

    }

    void drawEyelid(Graphics2D g2d,Color eyelidcolor) {

        int left1 = left;
        int right1 = (left + width);
        int bhu1 = left + (width / 3);
        int bhu2 = left + (width * 2 / 3);

        // draw upper eyelid
        Path2D.Double eyelidUP = new Path2D.Double();
        eyelidUP.moveTo(left1, midy - eyeAngle);
        eyelidUP.curveTo(bhu1, top + upperEyelidSize, bhu2, top + upperEyelidSize,
                right1,
                midy + eyeAngle);
        eyelidUP.curveTo(bhu2, bottom - eyeOpenness, bhu1,
                bottom - eyeOpenness,
                left1,
                midy - eyeAngle);
        eyelidUP.closePath();

        // draw bottom eyelid
        Path2D.Double eyelidBOT = new Path2D.Double();
        eyelidBOT.moveTo(right1, midy + eyeAngle);
        eyelidBOT.curveTo(bhu2, bottom + lowerEyelidSize, bhu1,
                bottom + lowerEyelidSize,
                left1,
                midy - eyeAngle);
        eyelidBOT.curveTo(bhu1, bottom + eyeOpenness / 10, bhu2,
                bottom + eyeOpenness / 10, right1, midy + eyeAngle);
        eyelidBOT.closePath();

        Shape eyelidUP2 = drawMirrored(eyelidUP);
        Shape eyelidBOT2 = drawMirrored(eyelidBOT);

        g2d.setColor(Color.BLACK);
        g2d.draw(eyelidUP);
        g2d.draw(eyelidBOT);
        g2d.draw(eyelidUP2);
        g2d.draw(eyelidBOT2);

        // can add eyeshadow
        g2d.setColor(eyelidcolor);
        g2d.fill(eyelidUP);
        g2d.fill(eyelidBOT);
        g2d.fill(eyelidUP2);
        g2d.fill(eyelidBOT2);
        g2d.setColor(Color.BLACK);
    }


    // readjusts bounding rectangle based on eyeSize, 
    public void adjustBoundRect(){
        int modsize = eyeSize+70;
        int negDist = -(eyeDist-10);
        
        /* 
        Rectangle modRect = new Rectangle(getBoundRect().x+getBoundRect().width*negDist/100,
        getBoundRect().y,
        getBoundRect().width*(modsize)/100,
        getBoundRect().height*modsize/100
        );
*/

        Rectangle modRect = new Rectangle(getBoundRect().x+getBoundRect().width*-negDist/100,
        getBoundRect().y,
        getBoundRect().width*(modsize)/100,
        getBoundRect().height*modsize/100
        );
        setBoundRect(modRect);
        setBoundingBoxParameters();
    }


    // sets all eye parameters at once
    public void setEyes(int distortion1,int distortion2,int angle,int size,int eyedist){
        this.upperEyelidSize=distortion1;
        this.lowerEyelidSize=distortion2;
        this.eyeAngle=angle;
        this.eyeSize=size;
        this.eyeDist=eyedist;
    }


public int getAngle() {
        return eyeAngle;
}

public void setAngle(int angle) {
        this.eyeAngle = angle;
}

public  int getSize() {
        return eyeSize;
}

public  void setSize(int size) {
        this.eyeSize = size;
}

public  int getEyedist() {
        return eyeDist;
}

public  void setEyedist(int eyedist) {
        this.eyeDist = eyedist;
}

public int getDistortion1() {
        return upperEyelidSize;
}

public void setDistortion1(int distortion1) {
        this.upperEyelidSize = distortion1;
}

public int getDistortion2() {
        return lowerEyelidSize;
}

public void setDistortion2(int distortion2) {
        this.lowerEyelidSize = distortion2;
}

public int getDistortion3() {
        return eyeOpenness;
}

public void setDistortion3(int distortion3) {
        this.eyeOpenness = distortion3;
}





    
}
