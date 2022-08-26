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
    public void calcGuidingLines(int numLines, Graphics2D g2d) {

        int x1 = (int) halfFace.getX();
        int y1 = (int) halfFace.getY();
        int width1 = (int) halfFace.getWidth();
        int height1 = (int) halfFace.getHeight();

        // draws the horizontal lines
        for (int i = 0; i < numLines; i++) {
             g2d.drawLine(x1,y1+height1*i/numLines, width1+x1,y1+ height1*i/numLines);
        }

        // draw vertical lines for the eyes
         g2d.drawLine(x1+width1*2/6, y1, x1+width1*2/6, height1+y1);
     g2d.drawLine(x1+width1*5/6, y1, x1+width1*5/6, height1+y1);

        eyes.setBoundRect(getBoundingBox(x1 + width1 * 2 / 6, (y1 + height1 * 3 / numLines),
                (x1 + width1 * 5 / 6),
                (y1 + height1 * 4 / numLines)));

    }

    int minHeight, height, maxwidth, midHeight, midWidth;
    Rectangle halfFace; // denominates the
    TextureHandler texture = new TextureHandler();
    final int thinness = 50; // larger number == thinner values range [0-50]

    Color makeupEyeColor, eyePupilColor,eyeballColor;

    Head head = new Head();
    Eyes eyes= new Eyes();


    public Face(int w, int h) {

        minHeight = 50;
        height = h - 50;
        midHeight = height / 2;
        maxwidth = w;
        midWidth = maxwidth / 2;

        //  eyeballColor= Color.pink;
        eyeballColor= Color.white;
        //makeupEyeColor=Color.white;
        makeupEyeColor=Color.darkGray;
        eyePupilColor=Color.darkGray;
    }



    // puts everything together
    public void drawFace(Graphics2D g2d) {
        // calculates the basic rectangles that contain various elements
        head.calcHead();

        // these methods show the assisting rectangles and guidlines
        toRect(halfFace, g2d);

        calcGuidingLines(8, g2d);

        head.drawHead(g2d);           
        eyes.drawEyes(g2d);

        
    }

    // encompasses all features of the head
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

    // conserns facial parts that are symmetrical
    abstract class SymmetricalFeature {

        Hashtable<String, Integer> leftValues ;
        Hashtable<String, Integer> rightValues ;
        Hashtable<String, Integer> cacheValues ;

        SymmetricalFeature() {
            leftValues = new Hashtable<>();  // has the values for the left side
            rightValues = new Hashtable<>(); // has the values for the right side
            cacheValues = new Hashtable<>(); // temporary hashtable
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
                cacheValues.clear();
                cacheValues.putAll(leftValues);
            }else if(side.equals("Right")){
                cacheValues.clear();
                cacheValues.putAll(rightValues);
            }else{
                System.out.println("No side is chosen for selection of HashTable");
            }
        }
    }

    // responsible for drawing the components of the eyes
    class Eyes extends SymmetricalFeature{

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

        // sets up the parameters        
        void setup() {

            left = boundRect.x;
            width = boundRect.width;
            height = boundRect.height;
            right = width + boundRect.x;
            top = boundRect.y;
            bottom = boundRect.height + boundRect.y;
            midy = (top + bottom) / 2;

            distortion1 = r.randomBetween(-10, 10);
            distortion2 = r.randomBetween(-10, 10);
            distortion3 = r.randomBetween(0, 75);

            //sets up the hashtables of the points that will be needed
            calcLeftSideValues();
            coverttoRightSideValues();

            eyeball = new EyeBall();
            pupil = new Pupil();
            eyelids = new Eyelids();

            
        }

        void drawEyes(Graphics2D g2d) {

            setup();

            // draw left eye
            String side ="Left";
            setcacheValues(side);
            eyeball.drawEyeball(side, g2d);
            pupil.drawPupil(side,g2d);
            eyelids.drawEyelid(side,g2d);

            // draw right eye
            side ="Right";
            setcacheValues(side);
            eyeball.drawEyeball(side, g2d);
            pupil.drawPupil(side,g2d);
            eyelids.drawEyelid(side,g2d);
        }

        class EyeBall {

            void drawEyeball(String side, Graphics2D g2d) {

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
                g2d.setColor(eyeballColor);
                g2d.fill(eye);
                g2d.setColor(Color.black);
            }

        }

        class Pupil  {

            void drawPupil(String side, Graphics2D g2d) {

                Ellipse2D.Double iris=new Ellipse2D.Double();
                Ellipse2D.Double pupil = new Ellipse2D.Double();

                if (side.equals("Left")){
                    iris=new Ellipse2D.Double(left + width / 3, top+height/4, width / 3, height*4/6);
                    pupil = new Ellipse2D.Double(left + width * 4 / 9, top + height / 2, width / 9,width / 9);
                }else if(side.equals("Right")){
                    iris=new Ellipse2D.Double((symmetricHorizonal(left + width / 3)-(width / 3)), top+height/4, width / 3, height*4/6);
                    pupil = new Ellipse2D.Double((symmetricHorizonal(left + width * 4 / 9)-(width / 9)), top + height / 2, width / 9,width / 9);
                }else{
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

        class Eyelids{

            void drawEyelid(String side,Graphics2D g2d){

                // draw eyelid
                Path2D.Double eyelidUP = new Path2D.Double();
                eyelidUP.moveTo(cacheValues.get("left"), midy);
                eyelidUP.curveTo(cacheValues.get("bhU1"), top + distortion1, cacheValues.get("bhU2"), top + distortion1, cacheValues.get("right"),
                        midy);
                eyelidUP.curveTo(cacheValues.get("bhU2"), bottom - distortion3, cacheValues.get("bhU1"), bottom - distortion3,
                cacheValues.get("left"),
                        midy);
                eyelidUP.closePath();
                g2d.draw(eyelidUP);
                
                // draw bottom eyelid
                Path2D.Double eyelidBOT = new Path2D.Double();
                eyelidBOT.moveTo(cacheValues.get("right"), midy);
                eyelidBOT.curveTo(cacheValues.get("bhU2"), bottom + distortion2,cacheValues.get("bhU1"), bottom + distortion2,
                cacheValues.get("left"),
                        midy);
                eyelidBOT.curveTo(cacheValues.get("bhU1"), bottom + distortion3 / 5, cacheValues.get("bhU2"),
                        bottom + distortion3 / 5, cacheValues.get("right"), midy);
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
