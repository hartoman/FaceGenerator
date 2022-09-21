/*
 * Made by Christos Chartomatsidis, 2022
 * This application is free to use, but it comes as-is:
 * I hold no responsibility for any damage or loss of that may arise from it's use.
 * Attribution is not required, but would be greatly appreciated.
 * For any comments, bug-reports, and ideas do not hesitate to contact me at:
 * hartoman@gmail.com
 */
package facegenerator;

import Toolz.*;
import facegenerator.Hair.HairCut;
import facegenerator.Hair.HairStylezEnum;

import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author chris
 */

public class Face {

    public static int minHeight, height, maxwidth, midHeight, midWidth;
    public static Color makeupEyeColor, eyePupilColor, eyeballColor, skinColor, hairColor, lipsColor;

    static Rectangle halfFace; // marks the area of the left half of the face
    static Randomizer r = new Randomizer();
    static Head head;
    static Eyes eyes;
    static Nose nose;
    static Eyebrows eyebrows;
    static Mouth mouth;
    static Ears ears;

    static HairCut haircut;
    static FacialHair facialHair;

    // in the constructor all important parameters can be calibrated
    public Face(int w, int h) {

        minHeight = 50;
        height = h - 50;
        midHeight = height / 2;
        maxwidth = w;
        midWidth = maxwidth / 2;

        head = new Head();
        eyes = new Eyes();
        nose = new Nose();
        eyebrows = new Eyebrows();
        mouth = new Mouth();
        ears = new Ears();

        setHairCut(7);
        facialHair = new FacialHair();

        /* adjustable parameters */
        // face
        head.thiccness = 0;// r.randomBetween(0, 50); // thiccness of the face
        head.mod1 = 0;// r.randomBetween(0, 50 - head.thiccness); // head shape [0,50]: 0 is sharp, 50
                      // is potatohead

        skinColor = new Color(255,255,255,0); //Color.white;
        hairColor = Color.black;

        // eyes
        eyeballColor = Color.white;
        eyePupilColor = Color.darkGray;
        makeupEyeColor = Color.DARK_GRAY;// skinColor; // ==skincolor if no make-up
        eyes.distortion1 = 0;// r.randomBetween(-10, 10); // width for upper eyeball -- [-10,10]
        eyes.distortion2 = 0;// r.randomBetween(-10, 10); // for lower eyeball -- [-10,10]
        eyes.distortion3 = 40;// r.randomBetween(25, 75); // eyelid openness-- [0,75], 0=closed, 75=wide open
        eyes.angle = 0;// r.randomBetween(-25, 25); // angle of the eyes 0=straight, 20= inward

        // nose
        nose.noseSize =1;// r.randomBetween(1, 8); // -- [1,8] greatness of noseSIze;

        // mouth
        lipsColor = Color.red;
        mouth.lipSize =0; // r.randomBetween(0,30) // [0,30] ?can also 40, but will be caricature
        mouth.mouthSize = 0;// r.randomBetween(0, 40); // [0,50] ??40?
        mouth.smile = 0;// r.randomBetween(-20, 20); // [-15,15]
        mouth.openness = 0;// r.randomBetween(0,40) // [0,40]

        // eyebrows
        eyebrows.eyebrowSize = 0;// r.randomBetween(0,25); // [0,25] min-max
        eyebrows.anger = 0;// r.randomBetween(-50, 50);//eyes.angle*2; // [-50,50] angle to determine
                           // expression, 0= neutral

        eyebrows.eyebrowThiccness = 2;// [0,4]

        // ears
        ears.earSize = 0; // r.randomBetween(0,50); // [0,50]

        // facial hair

        facialHair.moustache.moustacheSize = 0; // [0,20]
        facialHair.moustache.curled = 0; // [-40,40]

        facialHair.chin.soulpatchHeight=0;            // [0,6]
        facialHair.chin.soulpatchWidth=0;             // 0,6]  

        RectComputer.calcAllFeatures();

        
    }

    // puts everything together. newer calls overwrite older, so the order matters
    public void drawFace(Graphics2D g2d) {

        // assisting method so that we see what we are doing, while writing the calculations
        drawGuidingLines(8, g2d);

        // on the vack there is the long hair
        haircut.drawBackHair(g2d);

        // on the face are the main facial features
        head.drawHead(g2d);
        eyes.drawEyes(g2d);
        eyebrows.drawEyebrows(g2d);
        mouth.drawMouth(g2d);

        // draw the rest of hair, also facial hair
        haircut.drawHairCut(g2d);
        facialHair.drawFacialHair(g2d);

        // ears and nose should appear on top of hair
        ears.drawEars(g2d);
        nose.drawNose(g2d);
        
    }

    /* convenience methods for drawing the framing lines */

    // draws the assisting guiding lines
    public void drawGuidingLines(int numLines, Graphics2D g2d) {

        // int x1 = (int) halfFace.getX();
        // int y1 = (int) halfFace.getY();
        // int width1 = (int) halfFace.getWidth();
        // int height1 = (int) halfFace.getHeight();

        // blue color for assisting lines
        g2d.setColor(Color.blue);

        // draws the horizontal lines
        for (int i = 0; i < numLines; i++) {
            // g2d.drawLine(x1, y1 + height1 * i / numLines, width1 + x1, y1 + height1 * i /
            // numLines);
        }

        // draw vertical lines for the eyes
        // g2d.drawLine(x1 + width1 * 2 / 6, y1, x1 + width1 * 2 / 6, height1 + y1);
        // g2d.drawLine(x1 + width1 * 5 / 6, y1, x1 + width1 * 5 / 6, height1 + y1);

        // show bezier handles (endpoints of the line)
        // Path2D.Double bez = new Path2D.Double();
        // bez.moveTo(head.bXL1, head.bY1);
        // bez.lineTo(head.bXL2, head.bY2);
        // g2d.draw(bez);

        // gray color for bounding rectangles
        g2d.setColor(Color.gray);

        // toRect(halfFace, g2d);
        // toRect(nose.boundRect, g2d);
        // toRect(eyebrows.boundRect, g2d);

        // toRect(haircut.topOfHead.boundRect, g2d);
        // toRect(haircut.temples.boundRect, g2d);
        // toRect(mouth.boundRect, g2d);
        // toRect(ears.boundRect, g2d);

        // toRect(facialHair.moustache.boundRect, g2d);
        toRect(facialHair.chin.boundRect, g2d);

        // line starting from middle of face and ending on outline at the given height
        // drawBezHeight(height/2,g2d);

        // back to default
        g2d.setColor(Color.black);
    }

    // directly draws inserted rect
    public void toRect(Rectangle rect, Graphics2D g2d) {

        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();

        g2d.drawRect(x, y, width, height);
    }

    // draws a single horizontal line from middle of screen up to bezier curve of
    // face
    void drawBezHeight(int yPos1, Graphics2D g2d) {

        Path2D.Double bezheight1 = new Path2D.Double();
        Point bezPoint1 = RectComputer.headPointOnYpos(yPos1);

        bezheight1.moveTo(maxwidth - midWidth, yPos1);
        bezheight1.lineTo(bezPoint1.x, yPos1);
        g2d.setColor(Color.red);
        g2d.draw(bezheight1);
        g2d.setColor(Color.black);
    }

    // sets the current haircut
    void setHairCut(int selection) {

        // TODO limit selection range
        haircut = HairStylezEnum.values()[selection].makeHair();

    }

    /************************* HEAD FEATURES *******************************/

    // defines the main features-outline of the head.
    // The rest of the features are drawn on basis of these
    class Head {

        int thiccness; // larger number == fatter, values range [0-50]
        int mod1;// ,mod2,mod4,mod3;

        int bXL1, bXR1, bY1; // bezier handle 1 (ear-eye axis) -- x for L, R and Y is the same
        int bXL2, bXR2, bY2; // bezier handle 2 (nose-mouth axis) -- x for L, R and Y is the same

        // calculates head size
        public void calcHead() {

            int mod2 = r.randomBetween(0, 10); // [0,10]
            int mod4 = r.randomBetween(110, 120); // [110,120]
            int mod3 = 50 - (thiccness + mod1) / 2;

            bXL1 = midWidth * mod1 / 120;
            bY1 = (minHeight + ((height * mod2) / 120));

            bXL2 = midWidth * mod3 / 120;
            bY2 = (height - (minHeight * mod4) / 120);

            // calculates the other half of the head
            bXR1 = RectComputer.symmetricHorizonal(bXL1);
            bXR2 = RectComputer.symmetricHorizonal(bXL2);

            // sets the rectangle boundary of the left half of the face
            halfFace = RectComputer.getBoundingBox(Math.max(bXL1, bXL2), minHeight, midWidth, height);

        }

        // draws the overall head shape
        public void drawHead(Graphics2D g2d) {

            Path2D.Double head = new Path2D.Double();

            // draws face:
            head.moveTo(midWidth, minHeight);
            // left side
            head.curveTo(bXL1, bY1,
                    bXL2, bY2,
                    midWidth, height);

            // right side
            head.curveTo(bXR2, bY2,
                    bXR1, bY1,
                    midWidth, minHeight);

            // finish off head
            head.closePath();

            g2d.draw(head);
            g2d.setColor(skinColor);
            g2d.fill(head); // with skin color

        }

    }

    // done
    class Eyes extends SymmetricalFeature {

        // the further from 0, the wider the eye becomes
        int distortion1, // for upper eyeball -- [-10,10]
                distortion2, // for lower eyeball -- [-10,10]
                distortion3; // eyelid openness-- [0,75], 0=closed, 75=wide open

        int angle; // angle of the shape of the eyes
        int distance; // how far apart are the eyes


        void drawEyes(Graphics2D g2d) {

            // TODO Move this away from here, it causes problems because it is constantly recalculated
       //     left = left + distance;
      //      right = right + distance;

            drawEyeball(g2d);
            drawPupil(g2d);
            drawEyelid(g2d);

        }

        void drawEyeball(Graphics2D g2d) {

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
            g2d.setColor(eyeballColor);
            g2d.fill(eye);
            g2d.fill(eye2);

            g2d.setColor(Color.black);
        }

        void drawPupil(Graphics2D g2d) {

            Ellipse2D.Double iris = new Ellipse2D.Double();
            Ellipse2D.Double pupil = new Ellipse2D.Double();
            iris = new Ellipse2D.Double(left + width / 3, top + height / 4, width / 3, height * 4 / 6);
            pupil = new Ellipse2D.Double(left + width * 4 / 9, top + height / 2, width / 9, width / 9);

            // create iris
            Shape iris2 = drawMirrored(iris);
            Shape pupil2 = drawMirrored(pupil);

            g2d.draw(iris);
            g2d.draw(iris2);
            g2d.setColor(eyePupilColor);
            g2d.fill(iris);
            g2d.fill(iris2);
            g2d.setColor(Color.black);

            // draws the pupis
            g2d.draw(pupil);
            g2d.draw(pupil2);
            g2d.fill(pupil2);
            g2d.fill(pupil);

        }

        void drawEyelid(Graphics2D g2d) {

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
            g2d.setColor(makeupEyeColor);
            g2d.fill(eyelidUP);
            g2d.fill(eyelidBOT);
            g2d.fill(eyelidUP2);
            g2d.fill(eyelidBOT2);
            g2d.setColor(Color.BLACK);
        }

    }

    // done
    class Eyebrows extends SymmetricalFeature {

        int eyebrowSize;
        int anger;
        int eyebrowThiccness;
        int angerpointL, angerpointR, widthEB;
        // beginX always on left, beginY based on anger

        void drawEyebrows(Graphics2D g2d) {

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
            g2d.setColor(hairColor);
            g2d.fill(eyebrow2);
            g2d.fill(eyebrow);
            g2d.setColor(Color.black);
        }

    }

    // done
    class Nose extends SymmetricalFeature {

        int noseSize;

        void drawNose(Graphics2D g2d) {

            int bridgeBeginy = top;
            int bh1y = top + height * 1 / 5;
            int bh2y = top + height * 3 / 5;
            int bridgeEndy = top + height * 4 / 5;
            int bh3y = top + height * 8 / 9;
            int bh4y = top + height * 18 / 19;// bh3y+20;
            int bh5y = top + height * 11 / 10;

            int pos2 = 9 - noseSize;
            int bridgeBeginx = left + width * noseSize / 9;
            int bh1x = right;
            int bh2x = left + width / 2;
            int bridgeEndx = left + width * pos2 / 9;
            int bh3x = bridgeEndx - width * pos2 / 2 / 9;
            int bh4x = left + width * 4 / 5;
            int bh5x = left + width * 8 / 9;

            Path2D.Double nose = new Path2D.Double();
            nose.moveTo(bridgeBeginx, bridgeBeginy);

            nose.curveTo(bh1x, bh1y, bh2x,
                    bh2y,
                    bridgeEndx, bridgeEndy);

            nose.curveTo(bh3x, bh3y, bh4x,
                    bh4y,
                    bh4x, bh4y);

            nose.curveTo(bh5x, bh5y, right, bh5y, left + width,
                    bh5y);

            // so that it gets drawn OVER the moustache
            g2d.draw(nose);
            g2d.setColor(skinColor);
            nose.lineTo(right, top);
            nose.closePath();
            g2d.setColor(Color.black);

            Shape nose2 = drawMirrored(nose);
            g2d.draw(nose2);

            g2d.setColor(skinColor);
            g2d.fill(nose);
            g2d.fill(nose2);
            g2d.setColor(Color.BLACK);
        }

    }

    // done
    class Mouth extends SymmetricalFeature {
        int mouthSize;
        int smile;
        int openness;
        int lipSize;

        void drawMouth(Graphics2D g2d) {

            int midx = (left + width / 2);
            int mouthpointUL1 = midx - width / 5 - mouthSize - 0;
            int mouthpointUR1 = midx + width / 5 + mouthSize + 0;
            int mouthULY = midy - ((height * smile) / 100);
            int mouthLLY = midy + (height * smile / 100);
            int bh1X = left + (width * 2 / 5);
            int bh1Y = mouthULY - openness + smile;
            int bh2X = RectComputer.symmetricHorizonal(bh1X);
            int bh2Y = mouthLLY + openness + smile;

            // lips
            Path2D.Double lips = new Path2D.Double();
            lips.moveTo(mouthpointUL1, mouthULY);
            lips.curveTo(bh1X, bh1Y - lipSize, bh2X, bh1Y - lipSize, mouthpointUR1, mouthULY);
            lips.curveTo(bh2X, bh2Y + lipSize, bh1X, bh2Y + lipSize, mouthpointUL1, mouthULY);
            lips.closePath();

            // mouth
            Path2D.Double mouth = new Path2D.Double();
            mouth.moveTo(mouthpointUL1, mouthULY);
            mouth.curveTo(bh1X, bh1Y, bh2X, bh1Y, mouthpointUR1, mouthULY);
            mouth.curveTo(bh2X, bh2Y, bh1X, bh2Y, mouthpointUL1, mouthULY);
            mouth.closePath();

            g2d.setColor(lipsColor);
            g2d.draw(lips);
            g2d.fill(lips);
            g2d.setColor(Color.black);
            g2d.draw(mouth);
            g2d.fill(mouth);

            // TODO: teeth
            /*
             * g2d.setColor(Color.white);
             * Path2D.Double teeth1 = new Path2D.Double();
             * teeth1.moveTo(mouthpointUL1, mouthULY);
             * teeth1.curveTo(bh1X, bh1Y, bh2X, bh1Y, mouthpointUR1, mouthULY);
             * teeth1.curveTo(bh1X, bh1Y, bh2X, bh1Y, mouthpointUL1, mouthULY);
             * teeth1.closePath();
             * g2d.draw(teeth1);
             * g2d.fill(teeth1);
             * 
             * g2d.setColor(Color.black);
             */

        }
    }

    // done
    class Ears extends SymmetricalFeature {

        int earSize;

        void drawEars(Graphics2D g2d) {

            int earMidpoint = ((left + width / 2) - width * earSize / 100);

            int bh1x = right;// earMidpoint+width/2;//+width*2/3;
            int bh1y = top;
            int bh2x = left;// +width/5;
            int bh2y = top;

            int bh3x = bh2x + width * 2 / 3;
            int bh3y = bottom;
            int bh4x = left + width;
            int bh4y = bottom + height / 3;

            /*
             * Path2D.Double bh1line = new Path2D.Double();
             * bh1line.moveTo(bh3x, bh3y);
             * bh1line.lineTo(bh4x, bh4y);
             * g2d.draw(bh1line);
             */

            Path2D.Double ear = new Path2D.Double();
            ear.moveTo(right, top + height / 3);
            ear.curveTo(bh1x - earSize, bh1y, bh2x - earSize, bh1y, earMidpoint + width / 4, midy + height / 3);
            ear.curveTo(bh3x - earSize, bh3y, bh4x - earSize, bh4y, right, top + height * 4 / 5);
            ear.closePath();

            Shape ear2 = drawMirrored(ear);
            g2d.draw(ear);
            g2d.draw(ear2);
            g2d.setColor(skinColor);
            g2d.fill(ear);
            g2d.fill(ear2);
            g2d.setColor(Color.black);

            Path2D.Double inEar = new Path2D.Double();
            inEar.moveTo(right - width / 9, top + height / 3);
            inEar.curveTo(bh1x - earSize, bh1y + width / 9, bh2x - earSize, bh1y + width / 9, earMidpoint + width / 3,
                    midy + height / 3);
            g2d.draw(inEar);
            g2d.draw(drawMirrored(inEar));

            Path2D.Double earPore = new Path2D.Double();
            earPore.moveTo(right - width / 8, midy + height / 3);
            earPore.lineTo(right - width / 8, top + height * 3 / 4);
            earPore.curveTo(bh1x - width / 8 + earSize / 8, midy, bh2x + height * 2 / 5 + earSize / 8, midy,
                    right - width / 8, midy + height / 3);
            earPore.closePath();

            Shape earPore2 = drawMirrored(earPore);
            g2d.draw(earPore);
            g2d.draw(earPore2);
            g2d.fill(earPore);
            g2d.fill(earPore2);

        }

    }



}
