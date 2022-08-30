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
import java.awt.*;
import java.awt.geom.*;
import java.util.Hashtable;
//import java.util.Enumeration;

/**
 *
 * @author chris
 */

public class Face {

    // gets ths symmentrical of x on the horizontal axis
    public int symmetricHorizonal(int x) {
        return maxwidth - x;
    }

    // gets the approximate bounding box of a shape
    public Rectangle getBoundingBox(int leftMostX, int topMostY, int rightMostX, int bottomMostY) {
        return new Rectangle(leftMostX, topMostY, rightMostX - leftMostX, bottomMostY - topMostY);
    }

    // directly draws inserted rect
    public void toRect(Rectangle rect, Graphics2D g2d) {

        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();

        g2d.drawRect(x, y, width, height);

    }

    // divides the face to calculate bounding rectangles for facial features
    public void calcGuidingLines(int numLines) {

        int x1 = (int) halfFace.getX();
        int y1 = (int) halfFace.getY();
        int width1 = (int) halfFace.getWidth();
        int height1 = (int) halfFace.getHeight();

        /* we get the bounding boxes for all facial features */
        eyes.boundRect = getBoundingBox(x1 + width1 * 2 / 6, (y1 + height1 * 3 / numLines),
                (x1 + width1 * 5 / 6),
                (y1 + height1 * 4 / numLines));

        eyebrows.boundRect = getBoundingBox(x1 + width1 * 2 / 6, (y1 + height1 * 2 / numLines),
                (x1 + width1),
                (y1 + height1 * 3 / numLines));

        nose.boundRect = getBoundingBox(x1 + width1 * 4 / 6, (y1 + height1 * 3 / numLines),
                (x1 + width1),
                (y1 + height1 * 11 / 2 / numLines));

        moustache.boundRect = getBoundingBox(x1 + width1 * 3 / 6, (y1 + height1 * 11 / 2 / numLines),
                (x1 + width1),
                (y1 + height1 * 6 / numLines));

        mouth.boundRect = getBoundingBox(x1 + width1 * 3 / 6, (y1 + height1 * 6 / numLines),
                symmetricHorizonal(x1 + width1 * 3 / 6),
                (y1 + height1 * 7 / numLines));

        chin.boundRect = getBoundingBox(x1 + width1 * 3 / 6, (y1 + height1 * 7 / numLines),
                (x1 + width1),
                (y1 + height1));

        ears.boundRect = getBoundingBox(x1 - width1 / 6, (y1 + height1 * 5 / 2 / numLines),
                (x1 + width1 * 3 / 12),
                (y1 + height1 * 5 / numLines));

        topOfHead.boundRect = getBoundingBox(x1 + width1 * 3 / 2 / 12, (y1),
                (x1 + width1),
                (y1 + height1 * 5 / 2 / numLines));

        temples.boundRect = getBoundingBox(x1 + width1 * 1 / 12, (y1 + height1 * 3 / 2 / numLines),
                (x1 + width1 * 3 / 6),
                (y1 + height1 * 3 / numLines));

    }

    public void drawGuidingLines(int numLines, Graphics2D g2d) {

        int x1 = (int) halfFace.getX();
        int y1 = (int) halfFace.getY();
        int width1 = (int) halfFace.getWidth();
        int height1 = (int) halfFace.getHeight();

        // draws the horizontal lines
        for (int i = 0; i < numLines; i++) {
            // g2d.drawLine(x1, y1 + height1 * i / numLines, width1 + x1, y1 + height1 * i /
            // numLines);
        }

        // draw vertical lines for the eyes
        // g2d.drawLine(x1 + width1 * 2 / 6, y1, x1 + width1 * 2 / 6, height1 + y1);
        // g2d.drawLine(x1 + width1 * 5 / 6, y1, x1 + width1 * 5 / 6, height1 + y1);

        // toRect(nose.boundRect, g2d);
        // toRect(eyebrows.boundRect, g2d);

        // toRect(moustache.boundRect, g2d);
        // toRect(mouth.boundRect, g2d);
        // toRect(moustache.boundRect, g2d);
        // toRect(mouth.boundRect, g2d);

        // toRect(chin.boundRect, g2d);
        // toRect(ears.boundRect, g2d);
        // toRect(topOfHead.boundRect, g2d);
        // toRect(temples.boundRect, g2d);
    }

    // calculates coordinates for facial features
    void calcAllFeatures() {

        // these two first
        head.calcHead(); // gets ratios for the rest
        calcGuidingLines(8); // calculates assisting rectangles

        // the rest
        eyes.setBoundingBoxParameters();
        nose.setBoundingBoxParameters();
        eyebrows.setBoundingBoxParameters();
        ears.setBoundingBoxParameters();
        mouth.setBoundingBoxParameters();

    }

    int minHeight, height, maxwidth, midHeight, midWidth;
    Rectangle halfFace; // denominates the
    TextureHandler texture = new TextureHandler();

    Color makeupEyeColor, eyePupilColor, eyeballColor, skinColor, hairColor, lipsColor;

    Head head;
    Eyes eyes;
    Nose nose;
    Eyebrows eyebrows;
    Mouth mouth;
    Ears ears;
    TopOfHead topOfHead;
    Temples temples;
    MoustacheArea moustache;
    ChinArea chin;

    Randomizer r = new Randomizer();

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
        topOfHead = new TopOfHead();
        temples = new Temples();
        moustache = new MoustacheArea();

        chin = new ChinArea();
        /* adjustable parameters */
        // face
        head.thiccness = 0;// r.randomBetween(0, 50); // thiccness of the face
        skinColor = Color.pink;

        // eyes
        eyeballColor = Color.white;
        eyePupilColor = Color.darkGray;
        // makeupEyeColor=skinColor; // ==skincolor if no make-up
        eyes.distortion1 = 0;// r.randomBetween(-10, 10); // width for upper eyeball -- [-10,10]
        eyes.distortion2 = 0;// r.randomBetween(-10, 10); // for lower eyeball -- [-10,10]
        eyes.distortion3 = 50;// r.randomBetween(25, 75); // eyelid openness-- [0,75], 0=closed, 75=wide open
        eyes.angle = 0;// r.randomBetween(-25, 25); // angle of the eyes 0=straight, 20= inward

        // nose
        nose.noseSize = 1;// r.randomBetween(1, 8); // -- [1,8] greatness of noseSIze;

        // mouth
        lipsColor = Color.red;
        mouth.lipSize = 10;  // r.randomBetween(0,30)    // [0,30]   ?can also 40, but will be caricature
        mouth.mouthSize = 20;// r.randomBetween(0, 40); // [0,50] ??40?
        mouth.smile =0;// r.randomBetween(-20, 20); // [-15,15]
        mouth.openness = 40;// r.randomBetween(0,40)   // [0,40]

        // eyebrows
        eyebrows.eyebrowSize = 0;// r.randomBetween(0,25); // [0,25] min-max
        eyebrows.anger = 0;// r.randomBetween(-50, 50);//eyes.angle*2; // [-50,50] angle to determine
                           // expression, 0= neutral

        // always last
        // calculates all the permanent numbers for the features
        calcAllFeatures();
    }

    // puts everything together
    public void drawFace(Graphics2D g2d) {

        drawGuidingLines(8, g2d);
        head.drawHead(g2d);
        eyes.drawEyes(g2d);
        nose.drawNose(g2d);
        eyebrows.drawEyebrows(g2d);
        ears.drawEars(g2d);
        mouth.drawMouth(g2d);

    }

    // encompasses all features of the head
    class Head {

        int thiccness; // larger number == fatter, values range [0-50]
        int bXL1, bXR1, bY1; // bezier handle 1 (ear-eye axis) -- x for L, R and Y is the same
        int bXL2, bXR2, bY2; // bezier handle 2 (nose-mouth axis) -- x for L, R and Y is the same

        // calculates head size
        public void calcHead() {

            int thinness = 50 - thiccness;
            int mod1 = r.randomBetween(0, thinness);
            // sum of numenators of (bXL1 + bXL2) must be in [0,5]
            bXL1 = midWidth * mod1 / 120;

            // bY1 numenator deviation must always be equal or lesser than 10% of height
            int mod2 = r.randomBetween(0, 10);
            bY1 = (minHeight + ((height * mod2) / 120));

            // sum of numenators of (bXL1 + bXL2) must be in [0,5]
            int mod3 = thinness - mod1;
            bXL2 = midWidth * mod3 / 120;

            // bY2 numenator must always be greater than 90% of height
            int mod4 = r.randomBetween(110, 120);
            bY2 = (height - (minHeight * mod4) / 120);

            // calculates the other half of the head
            bXR1 = symmetricHorizonal(bXL1);
            bXR2 = symmetricHorizonal(bXL2);

            // sets the rectangle boundary of the left half of the face
            halfFace = getBoundingBox(Math.max(bXL1, bXL2), minHeight, midWidth, height);

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
            // g2d.fill(head);

        }

    }

    /************************* HEAD FEATURES *******************************/

    abstract class SymmetricalFeature {

        int left, width, height, right, top, bottom, midy;
        Rectangle boundRect = new Rectangle();

        // for every feature,draws its mirrored equivalent on the right
        Shape drawMirrored(Shape leftFeature, Graphics2D g2d) {

            // set up for right feature
            Shape rightFeature = leftFeature;
            AffineTransform at = new AffineTransform();

            // move to new position around center Y axis
            int newMaxX = (int) (rightFeature.getBounds().getX());

            at.translate(symmetricHorizonal(newMaxX), 0);

            // mirror it (around axis x=0)
            at.scale(-1, 1);

            // return it to its original (new) position
            at.translate(-newMaxX, 0);

            return at.createTransformedShape(rightFeature);
        }

        // convenience method to have on hand the necessary numbers for drawing
        public void setBoundingBoxParameters() {
            left = boundRect.x; // leftmost x of box
            width = boundRect.width; // box width
            height = boundRect.height; // box height
            right = width + boundRect.x; // rightmost x
            top = boundRect.y; // topmost y
            bottom = boundRect.height + boundRect.y; // bottomost y
            midy = (top + bottom) / 2; // middle of height
        }
    }

    // done
    class Eyes extends SymmetricalFeature {

        // the further from 0, the wider the eye becomes
        int distortion1, // for upper eyeball -- [-10,10]
                distortion2, // for lower eyeball -- [-10,10]
                distortion3; // eyelid openness-- [0,75], 0=closed, 75=wide open

        int angle; // angle of the shape of the eyes

        void drawEyes(Graphics2D g2d) {

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

            Shape eye2 = drawMirrored(eye, g2d);

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
            Shape iris2 = drawMirrored(iris, g2d);
            Shape pupil2 = drawMirrored(pupil, g2d);

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

            Shape eyelidUP2 = drawMirrored(eyelidUP, g2d);
            Shape eyelidBOT2 = drawMirrored(eyelidBOT, g2d);

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

        int angerpointL, angerpointR, widthEB;
        // beginX always on left, beginY based on anger

        void drawEyebrows(Graphics2D g2d) {

            angerpointL = midy + height * (-anger) / 100;
            angerpointR = midy + height * (+anger) / 100;
            widthEB = (width * (eyebrowSize + 75) / 100) / 9;

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

            Shape eyebrow2 = drawMirrored(eyebrow, g2d);

            g2d.draw(eyebrow);
            g2d.draw(eyebrow2);
            // g2d.setColor(hairColor);
            // g2d.fill(eyebrow2);
            // g2d.fill(eyebrow);
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

            g2d.draw(nose);

            Shape nose2 = drawMirrored(nose, g2d);
            g2d.draw(nose2);

        }

    }

    class Mouth extends SymmetricalFeature {
        int mouthSize;
        int smile;
        int openness;
        int lipSize;

        void drawMouth(Graphics2D g2d) {

      //      mouthSize = 0;
       //     smile = 0;
        //    openness = 0;

            int midx = (left + width / 2);
            int mouthpointUL1 = midx - width / 5 - mouthSize - 0;
            int mouthpointUR1 = midx + width / 5 + mouthSize + 0;
            int mouthULY = midy - ((height * smile) / 100);
            int mouthLLY = midy + (height * smile / 100);
            int bh1X = left + (width * 2 / 5);
            int bh1Y = mouthULY -openness+smile;
            int bh2X = symmetricHorizonal(bh1X);
            int bh2Y = mouthLLY + openness+smile;

            
            // lips
            Path2D.Double lips = new Path2D.Double();
            lips.moveTo(mouthpointUL1, mouthULY);
            lips.curveTo(bh1X, bh1Y-lipSize, bh2X, bh1Y-lipSize, mouthpointUR1, mouthULY);
            lips.curveTo(bh2X, bh2Y+lipSize, bh1X, bh2Y+lipSize, mouthpointUL1, mouthULY);
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
           
        }
    }

    //
    class Ears extends SymmetricalFeature {

        Shape leftFeature() {
            Path2D.Double ear = new Path2D.Double();

            ear.moveTo(100, 200);
            ear.lineTo(150, 300);
            ear.closePath();

            return ear;
        }

        void drawEars(Graphics2D g2d) {

            g2d.draw(leftFeature());

            Shape rightFeature = leftFeature();

            AffineTransform at = new AffineTransform();

            // first move to new position around center Y axis
            int newMaxX = (int) (rightFeature.getBounds().getX());

            at.translate(symmetricHorizonal(newMaxX), 0);

            // we mirror it (around axis x=0)
            at.scale(-1, 1);

            // return it to its original (new) position
            at.translate(-newMaxX, 0);

            g2d.draw(at.createTransformedShape(rightFeature));

        }

    }

    class TopOfHead extends SymmetricalFeature {

    }

    class Temples extends SymmetricalFeature {

    }

    class MoustacheArea extends SymmetricalFeature {

    }

    class ChinArea extends SymmetricalFeature {

    }

    class TextureHandler {
        String skinTexture;
        String pupilTexture;
        String hairTexture;

        // set the color schemes
        public void setTextures(String skinTexture, String pupilTexture, String hairTexture) {
            this.skinTexture = skinTexture;
            this.pupilTexture = pupilTexture;
            this.hairTexture = hairTexture;
        }
    }

}
