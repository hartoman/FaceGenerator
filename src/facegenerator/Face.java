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

import javax.swing.*;

/**
 *
 * @author chris
 */

public class Face {

    // gets ths symmentrical of x on the horizontal axis
    public int symmetricHorizonal(int x) {
        return maxwidth - x;
    }
    /*
     * public void drawBezier(int bXL1, int bY1, int bXL2, int bY2, Graphics2D g2d)
     * {
     * // draws bezier lines for reference
     * Path2D.Double bh1 = new Path2D.Double();
     * bh1.moveTo(bXL1, bY1);
     * bh1.lineTo(bXL2, bY2);
     * g2d.draw(bh1);
     * }
     */

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
    public void calcGuidingLines(int numLines, Graphics2D g2d) {

        int x1 = (int) halfFace.getX();
        int y1 = (int) halfFace.getY();
        int width1 = (int) halfFace.getWidth();
        int height1 = (int) halfFace.getHeight();

        // draws the horizontal lines
        for (int i = 0; i < numLines; i++) {
            // g2d.drawLine(x1,y1+height1*i/numLines, width1+x1,y1+ height1*i/numLines);
        }

        // draw vertical lines for the eyes
        // g2d.drawLine(x1+width1*2/6, y1, x1+width1*2/6, height1+y1);
        // g2d.drawLine(x1+width1*5/6, y1, x1+width1*5/6, height1+y1);

        eyes.setBoundRect(getBoundingBox(x1 + width1 * 2 / 6, (y1 + height1 * 3 / numLines),
                (x1 + width1 * 5 / 6),
                (y1 + height1 * 4 / numLines)));

    }

    int minHeight, height, maxwidth, midHeight, midWidth;
    Rectangle halfFace; // denominates the
    TextureHandler texture = new TextureHandler();
    final int thinness = 50; // larger number == thinner values range [0-50]

    Color makeupEye, eyeColor;

    Head head = new Head();
    Eyes eyes;//= new Eyes();


    public Face(int w, int h) {

        minHeight = 50;
        height = h - 50;
        midHeight = height / 2;
        maxwidth = w;
        midWidth = maxwidth / 2;

    }

    // puts everything together
    public void drawFace(Graphics2D g2d) {
        // calculates the basic rectangles that contain various elements
        head.calcHead();

        eyes = new Eyes();

        // these methods show the assisting rectangles and guidlines
        //toRect(halfFace, g2d);
        calcGuidingLines(8, g2d);

      
        eyes.drawEyes(g2d);

        head.drawHead(g2d);
    }

    class Head {

        int bXL1, bXR1, bY1; // bezier handle 1 (ear-eye axis) -- x for L, R and Y is the same
        int bXL2, bXR2, bY2; // bezier handle 2 (nose-mouth axis) -- x for L, R and Y is the same

        // calculates head size
        public void calcHead() {

            int mod1 = new Randomizer().randomBetween(0, thinness);
            // sum of numenators of (bXL1 + bXL2) must be in [0,5]
            bXL1 = midWidth * mod1 / 120;

            // bY1 numenator deviation must always be equal or lesser than 10% of height
            int mod2 = new Randomizer().randomBetween(0, 10);
            bY1 = (minHeight + ((height * mod2) / 120));

            // sum of numenators of (bXL1 + bXL2) must be in [0,5]
            int mod3 = thinness - mod1;
            bXL2 = midWidth * mod3 / 120;

            // bY2 numenator must always be greater than 90% of height
            int mod4 = new Randomizer().randomBetween(110, 120);
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

            // g2d.setColor(Color.pink);
            // g2d.fill(head);

        }

    }

    abstract class SymmetricalFeature {

        Hashtable<String, Integer> leftValues = new Hashtable<String, Integer>();  // has the values for the left side
        Hashtable<String, Integer> rightValues = new Hashtable<String, Integer>(); // has the values for the right side
        Hashtable<String, Integer> cacheValues = new Hashtable<String, Integer>(); // temporary hashtable

        SymmetricalFeature() {
        //    calcLeftSideValues();
        //    coverttoRightSideValues();
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
  
        public void setcacheValues(String side){
            if (side.equals("Left")){
                cacheValues=leftValues;
            }else if(side.equals("Right")){
                cacheValues=rightValues;
            }else{
                System.out.println("No side is chosen for selection of HashTable");
            }
        }
    }

    class Eyes {

        public void setBoundRect(Rectangle rect) {
            this.boundRect = rect;
        }

        Rectangle boundRect = new Rectangle();
        EyeBall eyeball;
        Pupil pupil;
        Eyelids eyelids;

        // todo: check if can be made static or even singleton
        Randomizer r = new Randomizer();

        int left, width, height, right, top, bottom, midy;

        // the further from 0, the wider the eye becomes
        int distortion1, // for upper eyeball -- [-10,10]
                distortion2, // for lower eyeball -- [-10,10]

                distortion3; // eyelid openness-- [0,75], 0=closed, 75=wide open

        public void Eye() {

            // toRect(boundRect, g2d);

            // we get what we need from the rect
            left = boundRect.x;
            width = boundRect.width;
            height = boundRect.height;
            right = width + boundRect.x;
            top = boundRect.y;
            bottom = boundRect.height + boundRect.y;
            midy = (top + bottom) / 2;

            distortion1 = 0;
            distortion2 = 0;
            distortion3 = 0;

            eyeball = new EyeBall();
            pupil = new Pupil();
            eyelids = new Eyelids();
        }

        public void drawEyes(Graphics2D g2d) {
            eyeball.calcLeftSideValues();
            eyeball.coverttoRightSideValues();
            System.out.println(eyeball.leftValues.size());
          //  eyeball.drawEyeballs(g2d);
          //  pupil.drawPupils(g2d);
          //  eyelids.drawEyelids(g2d);
        }

        class EyeBall extends SymmetricalFeature {

            EyeBall(){
                
            }

            @Override
            public void calcLeftSideValues() {

                leftValues.put("left", left);
                leftValues.put("right", left + width * 2 / 3);
                leftValues.put("bhU1", left + width / 3);
                leftValues.put("bhU2", left + width * 2 / 3);
  
            }

            void drawEyeball(String side, Graphics2D g2d) {

                //paints left or right eye
                setcacheValues(side);

                // draw eyeball shape
                Path2D.Double eye = new Path2D.Double();
                eye.moveTo(cacheValues.get("left"), midy);
                eye.curveTo(cacheValues.get("bhU1"), top + distortion1, cacheValues.get("bhU2"), top + distortion1,
                cacheValues.get("right"), midy);
                eye.curveTo(cacheValues.get("bhU2"), bottom + distortion2, cacheValues.get("bhU1"), bottom + distortion2,
                cacheValues.get("left"), midy);
                eye.closePath();
                g2d.draw(eye);

                // paint eyeball -- TODO: add various tones of red
                g2d.setColor(makeupEye);
                g2d.fill(eye);
                g2d.setColor(Color.black);
            }

            public void drawEyeballs(Graphics2D g2d){

                System.out.println("enters");

                calcLeftSideValues();
                coverttoRightSideValues();

                drawEyeball("Left",g2d);
                drawEyeball("Right",g2d);
            }
        }

        class Pupil extends SymmetricalFeature {

            @Override
            public void calcLeftSideValues() {
                // unfortunately we cannot use the mirrored hashtable here
                // the reason is that ellipse is created at point x,y and always goes to the right
                // if we did that, the right eye would be ..'loose'

            }

            void drawPupil(String side, Graphics2D g2d) {

                Ellipse2D.Double iris=new Ellipse2D.Double();
                Ellipse2D.Double pupil = new Ellipse2D.Double();

                if (side.equals("Left")){
                    iris=new Ellipse2D.Double(left + width / 3, top, width / 3, height);
                    pupil = new Ellipse2D.Double(left + width * 4 / 9, top + height / 2, width / 9,width / 9);
                }else if(side.equals("Right")){
                    iris=new Ellipse2D.Double((symmetricHorizonal(left + width / 3)-(width / 3)), top, width / 3, height);
                    pupil = new Ellipse2D.Double((symmetricHorizonal(left + width * 4 / 9)-(width / 9)), top + height / 2, width / 9,width / 9);
                }else{
                    System.out.println("Pupils: mistyped side choice");
                }

                // create iris
                g2d.draw(iris);
                g2d.setColor(eyeColor);
                g2d.fill(iris);
                g2d.setColor(Color.black);

                // draws the pupis
                g2d.draw(pupil);
                g2d.fill(pupil);
            }


            void drawPupils(Graphics2D g2d){
                drawPupil("Left",g2d);
                drawPupil("Right",g2d);
            }
        }

        class Eyelids extends SymmetricalFeature {

            @Override
            public void calcLeftSideValues() {
                leftValues.put("left",left);
                leftValues.put("bh1",left + width / 3);
                leftValues.put("bh2",left + width * 2 / 3);
                leftValues.put("right", right);
            }

            void drawEyelid(String side,Graphics2D g2d){
                setcacheValues(side);

                // draw eyelid

                Path2D.Double eyelidUP = new Path2D.Double();
                eyelidUP.moveTo(cacheValues.get("left"), midy);
                eyelidUP.curveTo(cacheValues.get("bh1"), top + distortion1, cacheValues.get("bh2"), top + distortion1, cacheValues.get("right"),
                        midy);
                eyelidUP.curveTo(cacheValues.get("bh2"), bottom - distortion3, cacheValues.get("bh1"), bottom - distortion3,
                cacheValues.get("left"),
                        midy);
                eyelidUP.closePath();
                g2d.draw(eyelidUP);
                // can add eyeshadow
                // g2d.fill(eyelidUP);

                Path2D.Double eyelidBOT = new Path2D.Double();
                eyelidBOT.moveTo(cacheValues.get("right"), midy);
                eyelidBOT.curveTo(cacheValues.get("bh2"), bottom + distortion2,cacheValues.get("bh1"), bottom + distortion2,
                cacheValues.get("left"),
                        midy);
                eyelidBOT.curveTo(cacheValues.get("bh1"), bottom + distortion3 / 5, cacheValues.get("bh2"),
                        bottom + distortion3 / 5, cacheValues.get("right"), midy);
                eyelidBOT.closePath();
                g2d.draw(eyelidBOT);
                // can add eyeshadow
                // g2d.fill(eyelidBOT);
            }

            void drawEyelids(Graphics2D g2d) {
                drawEyelid("Left",g2d);
                drawEyelid("Right",g2d);

            }
        }
    }

    class eyebrows {

        public eyebrows() {

        }
    }

    class nose {

        public nose() {

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
