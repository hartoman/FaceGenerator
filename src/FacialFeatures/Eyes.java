package FacialFeatures;

import java.awt.*;
import java.awt.geom.*;
import FunctionalClasses.SymmetricalFeature;

public class Eyes extends SymmetricalFeature {
    // the further from 0, the wider the eye becomes
    private int distortion1, // for upper eyeball -- [-10,10]
            distortion2, // for lower eyeball -- [-10,10]
            distortion3; // eyelid openness-- [0,75], 0=closed, 75=wide open

    private int angle; // angle of the shape of the eyes
    private int size;                   // [10,35]
    private int eyedist;               // [5,15]
    

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
        eye.moveTo(left1, midy - angle);
        eye.curveTo(bhu1, top + distortion1, bhu2, top + distortion1,
                right1, midy + angle);
        eye.curveTo(bhu2, bottom + distortion2, bhu1,
                bottom + distortion2,
                left1, midy - angle);
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
        eyelidUP.moveTo(left1, midy - angle);
        eyelidUP.curveTo(bhu1, top + distortion1, bhu2, top + distortion1,
                right1,
                midy + angle);
        eyelidUP.curveTo(bhu2, bottom - distortion3, bhu1,
                bottom - distortion3,
                left1,
                midy - angle);
        eyelidUP.closePath();

        // draw bottom eyelid
        Path2D.Double eyelidBOT = new Path2D.Double();
        eyelidBOT.moveTo(right1, midy + angle);
        eyelidBOT.curveTo(bhu2, bottom + distortion2, bhu1,
                bottom + distortion2,
                left1,
                midy - angle);
        eyelidBOT.curveTo(bhu1, bottom + distortion3 / 10, bhu2,
                bottom + distortion3 / 10, right1, midy + angle);
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
        int modsize = size+70;
        int negDist = -(eyedist-10);
        
        Rectangle modRect = new Rectangle(boundRect().x+boundRect().width*negDist/100,
        boundRect().y,
        boundRect().width*(modsize)/100,
        boundRect().height*modsize/100
        );
        boundRect(modRect);
        setBoundingBoxParameters();
    }


    // sets all eye parameters at once
    public void setEyes(int distortion1,int distortion2,int angle,int size,int eyedist){
        this.distortion1=distortion1;
        this.distortion2=distortion2;
        this.angle=angle;
        this.size=size;
        this.eyedist=eyedist;
    }


public int angle() {
        return angle;
}

public void angle(int angle) {
        this.angle = angle;
}

public  int size() {
        return size;
}

public  void size(int size) {
        this.size = size;
}

public  int eyedist() {
        return eyedist;
}

public  void eyedist(int eyedist) {
        this.eyedist = eyedist;
}

public int distortion1() {
        return distortion1;
}

public void distortion1(int distortion1) {
        this.distortion1 = distortion1;
}

public int distortion2() {
        return distortion2;
}

public void distortion2(int distortion2) {
        this.distortion2 = distortion2;
}

public int distortion3() {
        return distortion3;
}

public void distortion3(int distortion3) {
        this.distortion3 = distortion3;
}





    
}
