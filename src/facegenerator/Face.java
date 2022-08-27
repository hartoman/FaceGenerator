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
import java.util.Enumeration;

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
                (x1 + width1),
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
        eyes.setup();
        nose.setup();
        eyebrows.setup();
    }

    // draws the features -- most important method!!!
    void drawAllFeatures(Graphics2D g2d) {

        drawGuidingLines(8, g2d);
        head.drawHead(g2d);
        eyes.drawEyes(g2d);
        nose.drawNose(g2d);
        eyebrows.drawEyebrows(g2d);
    }

    int minHeight, height, maxwidth, midHeight, midWidth;
    Rectangle halfFace; // denominates the
    TextureHandler texture = new TextureHandler();

    Color makeupEyeColor, eyePupilColor, eyeballColor, skinColor;

    Head head = new Head();
    Eyes eyes = new Eyes();
    Nose nose = new Nose();
    Eyebrows eyebrows = new Eyebrows();
    Mouth mouth = new Mouth();
    Ears ears = new Ears();
    TopOfHead topOfHead = new TopOfHead();
    Temples temples = new Temples();
    MoustacheArea moustache = new MoustacheArea();
    ChinArea chin = new ChinArea();

    Randomizer r = new Randomizer();

    public Face(int w, int h) {

        minHeight = 50;
        height = h - 50;
        midHeight = height / 2;
        maxwidth = w;
        midWidth = maxwidth / 2;

        /* adjustable parameters */

        // face
        head.thiccness = 0;//r.randomBetween(0, 50); // thiccness of the face
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
        nose.noseSize = 1;//r.randomBetween(1, 8); // -- [1,8] greatness of noseSIze;

        // mouth
        // mouthSize=

        // eyebrows
        eyebrows.eyebrowSize =  0;// r.randomBetween(0,25); // [0,25] min-max
        eyebrows.anger =  0;// r.randomBetween(-50, 50);//eyes.angle*2;  // [-50,50] angle to determine expression, 0= neutral
    }

    // puts everything together
    public void drawFace(Graphics2D g2d) {
        // calculates the basic rectangles that contain various elements

        calcAllFeatures();
        drawAllFeatures(g2d);

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

    // conserns facial parts that are symmetrical --practically everything
    abstract class SymmetricalFeature {

        int left, width, height, right, top, bottom, midy;
        Rectangle boundRect = new Rectangle();

        Hashtable<String, Integer> leftValues;
        Hashtable<String, Integer> rightValues;
        Hashtable<String, Integer> cacheValues;

        SymmetricalFeature() {
            leftValues = new Hashtable<>(); // has the values for the left side
            rightValues = new Hashtable<>(); // has the values for the right side
            cacheValues = new Hashtable<>(); // temporary hashtable
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

        // used to calculate the values of the left side
        public abstract void calcLeftSideValues();

        // determines the values of the hashtable of the right-side based on the ones on
        // the left
        public void coverttoRightSideValues() {

            String valueName = new String();
            int rightSidenum = 0;
            Enumeration<String> values = leftValues.keys();

            while (values.hasMoreElements()) {

                // Getting the key of a particular entry
                valueName = values.nextElement();
                rightSidenum = symmetricHorizonal(leftValues.get(valueName));
                rightValues.put(valueName, rightSidenum);

            }
        }

        public void setcacheValues(String side) {
            if (side.equals("Left")) {
                cacheValues.clear();
                cacheValues.putAll(leftValues);
            } else if (side.equals("Right")) {
                cacheValues.clear();
                cacheValues.putAll(rightValues);
            } else {
                System.out.println("No side is chosen for selection of HashTable");
            }
        }
    }

    // responsible for drawing the components of the eyes
    class Eyes extends SymmetricalFeature {

        // calculates values of left eye
        @Override
        public void calcLeftSideValues() {

            int left1 = left;
            int right1 = (left + width);
            int bhu1 = left + (width / 3);
            int bhu2 = left + (width * 2 / 3);

            // WHY DOES IT CALCULATE EVERYTHING TWICE????
            System.out.println(width);

            leftValues.put("left", left1);
            leftValues.put("right", right1);
            leftValues.put("bhU1", bhu1);
            leftValues.put("bhU2", bhu2);
        }

        EyeBall eyeball;
        Pupil pupil;
        Eyelids eyelids;

        // the further from 0, the wider the eye becomes
        int distortion1, // for upper eyeball -- [-10,10]
                distortion2, // for lower eyeball -- [-10,10]
                distortion3; // eyelid openness-- [0,75], 0=closed, 75=wide open

        int angle; // angle of the shape of the eyes

        // sets up the parameters
        void setup() {

            // always must be at begging of setup
            setBoundingBoxParameters();

            // sets up the hashtables of the points that will be needed
            calcLeftSideValues();
            coverttoRightSideValues();

            eyeball = new EyeBall();
            pupil = new Pupil();
            eyelids = new Eyelids();
        }

        void drawEyes(Graphics2D g2d) {

            // setup();

            // draw left eye
            String side = "Left";
            setcacheValues(side);

            eyeball.drawEyeball(side, g2d);
            pupil.drawPupil(side, g2d);
            eyelids.drawEyelid(side, g2d);

            // draw right eye
            side = "Right";
            setcacheValues(side);
            eyeball.drawEyeball(side, g2d);
            pupil.drawPupil(side, g2d);
            eyelids.drawEyelid(side, g2d);
        }

        class EyeBall {

            void drawEyeball(String side, Graphics2D g2d) {

                // draw eyeball shape
                Path2D.Double eye = new Path2D.Double();
                eye.moveTo(cacheValues.get("left"), midy - angle);
                eye.curveTo(cacheValues.get("bhU1"), top + distortion1, cacheValues.get("bhU2"), top + distortion1,
                        cacheValues.get("right"), midy + angle);
                eye.curveTo(cacheValues.get("bhU2"), bottom + distortion2, cacheValues.get("bhU1"),
                        bottom + distortion2,
                        cacheValues.get("left"), midy - angle);
                eye.closePath();
                g2d.draw(eye);
                // paint eyeball
                g2d.setColor(eyeballColor);
                g2d.fill(eye);
                g2d.setColor(Color.black);

            }

        }

        class Pupil {

            void drawPupil(String side, Graphics2D g2d) {

                Ellipse2D.Double iris = new Ellipse2D.Double();
                Ellipse2D.Double pupil = new Ellipse2D.Double();

                if (side.equals("Left")) {
                    iris = new Ellipse2D.Double(left + width / 3, top + height / 4, width / 3, height * 4 / 6);
                    pupil = new Ellipse2D.Double(left + width * 4 / 9, top + height / 2, width / 9, width / 9);
                } else if (side.equals("Right")) {
                    iris = new Ellipse2D.Double((symmetricHorizonal(left + width / 3) - (width / 3)), top + height / 4,
                            width / 3, height * 4 / 6);
                    pupil = new Ellipse2D.Double((symmetricHorizonal(left + width * 4 / 9) - (width / 9)),
                            top + height / 2, width / 9, width / 9);
                } else {
                    System.out.println("Pupils: mistyped side choice");
                }

                // create iris
                g2d.draw(iris);
                g2d.setColor(eyePupilColor);
                g2d.fill(iris);
                g2d.setColor(Color.black);

                // draws the pupis
                g2d.draw(pupil);
                g2d.fill(pupil);
            }
        }

        class Eyelids {

            void drawEyelid(String side, Graphics2D g2d) {

                // draw eyelid
                Path2D.Double eyelidUP = new Path2D.Double();
                eyelidUP.moveTo(cacheValues.get("left"), midy - angle);
                eyelidUP.curveTo(cacheValues.get("bhU1"), top + distortion1, cacheValues.get("bhU2"), top + distortion1,
                        cacheValues.get("right"),
                        midy + angle);
                eyelidUP.curveTo(cacheValues.get("bhU2"), bottom - distortion3, cacheValues.get("bhU1"),
                        bottom - distortion3,
                        cacheValues.get("left"),
                        midy - angle);
                eyelidUP.closePath();
                g2d.draw(eyelidUP);

                // draw bottom eyelid
                Path2D.Double eyelidBOT = new Path2D.Double();
                eyelidBOT.moveTo(cacheValues.get("right"), midy + angle);
                eyelidBOT.curveTo(cacheValues.get("bhU2"), bottom + distortion2, cacheValues.get("bhU1"),
                        bottom + distortion2,
                        cacheValues.get("left"),
                        midy - angle);
                eyelidBOT.curveTo(cacheValues.get("bhU1"), bottom + distortion3 / 10, cacheValues.get("bhU2"),
                        bottom + distortion3 / 10, cacheValues.get("right"), midy + angle);
                eyelidBOT.closePath();
                g2d.draw(eyelidBOT);

                // can add eyeshadow
                g2d.setColor(makeupEyeColor);
                g2d.fill(eyelidUP);
                g2d.fill(eyelidBOT);
                g2d.setColor(Color.BLACK);
            }

        }
    }

    class Eyebrows extends SymmetricalFeature {

        int eyebrowSize;
        int anger;

        int angerpointL, angerpointR, widthEB;
        // beginX always on left, beginY based on anger

        // calculates values of left side
        @Override
        public void calcLeftSideValues() {

            // in hash

            int lengthEB = (width * (eyebrowSize + 75) / 100); // keep in calcs
            int bh1x = left + lengthEB * 1 / 3;
            int bh2x = left + lengthEB * 2 / 3;

            int bh3x = left + lengthEB;

            // leftValues.put("angerpointL", angerpointL);
            // leftValues.put("angerpointR", angerpointR );
            leftValues.put("bh1x", bh1x);
            leftValues.put("bh2x", bh2x);
            // leftValues.put("lengthEB",lengthEB );
            leftValues.put("left", left);
            leftValues.put("bh3x", bh3x);
        }

        void setup() {
            // always must be at begging of setup
            setBoundingBoxParameters();

            angerpointL = midy + height * (-anger) / 100;
            angerpointR = midy + height * (+anger) / 100;
            widthEB = (width * (eyebrowSize + 75) / 100) / 9;

            // sets up the hashtables of the points that will be needed
            calcLeftSideValues();
            coverttoRightSideValues();

        }

        void drawEyebrow(String side, Graphics2D g2d) {

            Path2D.Double eyebrow = new Path2D.Double();
            eyebrow.moveTo(cacheValues.get("left"), angerpointL);
            eyebrow.curveTo(cacheValues.get("bh1x"), angerpointL - widthEB / 2, cacheValues.get("bh2x"),
                    angerpointL - widthEB / 2, cacheValues.get("bh3x"), angerpointR);
            eyebrow.lineTo(cacheValues.get("bh3x"), angerpointR + widthEB);
            eyebrow.curveTo(cacheValues.get("bh2x"), angerpointL, cacheValues.get("bh1x"), angerpointL,
                    cacheValues.get("left"), angerpointL + widthEB);
            eyebrow.closePath();

            g2d.draw(eyebrow);
            // g2d.fill(eyebrow);
            g2d.setColor(Color.black);
        }

        void drawEyebrows(Graphics2D g2d) {

            String side = "Left";
            setcacheValues(side);
            drawEyebrow(side, g2d);

            side = "Right";
            setcacheValues(side);
            drawEyebrow(side, g2d);
        }

    }

    class Nose extends SymmetricalFeature {

        int noseSize;

        // calculates values of left side
        @Override
        public void calcLeftSideValues() {

            int pos2 = 9 - noseSize;
            int bridgeBeginx = left + width * noseSize / 9;
            int bh1x = right;
            int bh2x = left + width / 2;
            int bridgeEndx = left + width * pos2 / 9;
            int bh3x = bridgeEndx - width * pos2 / 2 / 9;
            int bh4x = left + width * 4 / 5;
            int bh5x = left + width * 8 / 9;

            leftValues.put("pos2", pos2);
            leftValues.put("bridgeBeginx", bridgeBeginx);
            leftValues.put("bh1x", bh1x);
            leftValues.put("bh2x", bh2x);
            leftValues.put("bridgeEndx", bridgeEndx);
            leftValues.put("bh3x", bh3x);
            leftValues.put("bh4x", bh4x);
            leftValues.put("bh5x", bh5x);

        }

        // sets up the parameters
        void setup() {

            // always must be at begging of setup
            setBoundingBoxParameters();

            // sets up the hashtables of the points that will be needed
            calcLeftSideValues();
            coverttoRightSideValues();

        }

        void drawNose(Graphics2D g2d) {

            int bridgeBeginy = top;
            int bh1y = top + height * 1 / 5;
            int bh2y = top + height * 3 / 5;
            int bridgeEndy = top + height * 4 / 5;
            int bh3y = top + height * 8 / 9;
            int bh4y = top + height * 18 / 19;// bh3y+20;
            int bh5y = top + height * 11 / 10;

            String side = "Left";
            setcacheValues(side);
            Path2D.Double nose = new Path2D.Double();
            nose.moveTo(cacheValues.get("bridgeBeginx"), bridgeBeginy);

            nose.curveTo(cacheValues.get("bh1x"), bh1y, cacheValues.get("bh2x"),
                    bh2y,
                    cacheValues.get("bridgeEndx"), bridgeEndy);

            nose.curveTo(cacheValues.get("bh3x"), bh3y, cacheValues.get("bh4x"),
                    bh4y,
                    cacheValues.get("bh4x"), bh4y);

            nose.curveTo(cacheValues.get("bh5x"), bh5y, right, bh5y, left + width,
                    bh5y);

            g2d.draw(nose);

            side = "Right";
            setcacheValues(side);

            nose.moveTo(cacheValues.get("bridgeBeginx"), bridgeBeginy);

            nose.curveTo(cacheValues.get("bh1x"), bh1y, cacheValues.get("bh2x"),
                    bh2y,
                    cacheValues.get("bridgeEndx"), bridgeEndy);

            nose.curveTo(cacheValues.get("bh3x"), bh3y, cacheValues.get("bh4x"),
                    bh4y,
                    cacheValues.get("bh4x"), bh4y);

            nose.curveTo(cacheValues.get("bh5x"), bh5y, right, bh5y, left + width,
                    bh5y);

            g2d.draw(nose);

        }

    }

    class Mouth extends SymmetricalFeature {

        // calculates values of left side
        @Override
        public void calcLeftSideValues() {
        }
    }

    class Ears extends SymmetricalFeature {

        // calculates values of left side
        @Override
        public void calcLeftSideValues() {
        }
    }

    class TopOfHead extends SymmetricalFeature {
        // calculates values of left side
        @Override
        public void calcLeftSideValues() {
        }
    }

    class Temples extends SymmetricalFeature {

        // calculates values of left side
        @Override
        public void calcLeftSideValues() {
        }
    }

    class MoustacheArea extends SymmetricalFeature {
        // calculates values of left side
        @Override
        public void calcLeftSideValues() {
        }
    }

    class ChinArea extends SymmetricalFeature {
        // calculates values of left side
        @Override
        public void calcLeftSideValues() {
        }
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
