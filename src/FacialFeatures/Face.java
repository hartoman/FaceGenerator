/*
 * Made by Christos Chartomatsidis, 2022
 * This application is free to use, but it comes as-is:
 * I hold no responsibility for any damage or loss of that may arise from it's use.
 * Attribution is not required, but would be greatly appreciated.
 * For any comments, bug-reports, and ideas do not hesitate to contact me at:
 * hartoman@gmail.com
 */

package FacialFeatures;
import Toolz.*;
import FacialHair.FacialHair;
import FunctionalClasses.RectComputer;
import FunctionalClasses.SymmetricalFeature;
import FacialHair.*;

import java.awt.*;
import java.awt.geom.*;

import Hair.HairCut;
import Hair.HairStylezEnum;

/**
 *
 * @author chris
 */

public class Face {

    private static int minHeight, height, maxwidth, midHeight, midWidth;
    private static Color makeupEyeColor, eyePupilColor, eyeballColor, skinColor, hairColor, lipsColor;

    private static Rectangle halfFace; // marks the area of the left half of the face
    
    private static Head head;
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

        Randomizer r = new Randomizer();

        /* adjustable parameters */
        // face
        head.thiccness = 0;// r.randomBetween(0, 50); // thiccness of the face
        head.mod1 = 0;// r.randomBetween(0, 50 - head.thiccness); // head shape [0,50]: 0 is sharp, 50
                      // is potatohead

        skinColor = new Color(255, 255, 255, 0); // Color.white;
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
        nose.noseSize = 1;// r.randomBetween(1, 8); // -- [1,8] greatness of noseSIze;

        // mouth
        lipsColor = Color.red;
        mouth.lipSize = 0; // r.randomBetween(0,30) // [0,30] ?can also 40, but will be caricature
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

        facialHair.moustache().moustacheSize(0);//; // [0,20]
        facialHair.moustache().curled(0); // [-40,40]

        facialHair.chin().soulpatchHeight(0); // [0,6]
        facialHair.chin().soulpatchWidth(0); // 0,6]

        RectComputer.calcAllFeatures();

    }





    // puts everything together. newer calls overwrite older, so the order matters
    public void drawFace(Graphics2D g2d) {

        // assisting method so that we see what we are doing, while writing the
        // calculations
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
        toRect(facialHair.chin().boundRect(), g2d);

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


    /************GETTERS AND SETTERS********************/
 // int  height, maxwidth, midHeight, midWidth;
 // static Color makeupEyeColor, eyePupilColor, eyeballColor, skinColor, hairColor, lipsColor;

// getters
public static int maxwidth() {return maxwidth;}
public static int minHeight() {return minHeight;}
public static int height() {return height;}
public static int midHeight() {return midHeight;}
public static int midWidth() {return midWidth;}
public static Rectangle halfFace(){return halfFace;}

public static Color hairColor(){return hairColor;}
public static Color skinColor() {return skinColor;}
public static Color eyeballColor(){return eyeballColor;}
public static Color eyePupilColor(){return eyePupilColor;}
public static Color makeupEyeColor(){return makeupEyeColor;}
public static Color lipsColor(){return lipsColor;}

public static Head head() {return head;}
public static Eyes eyes() {return eyes;}
public static FacialHair facialHair() {return facialHair;}
public static HairCut haircut() {return haircut;}
public static Eyebrows eyebrows() {return eyebrows;}
public static Nose nose() {return nose;}
public static Mouth mouth() {return mouth;}
public static Ears ears() {return ears;}


// setters
public static void minHeight(int minHeight) {Face.minHeight = minHeight;}
public static void height(int height) {Face.height = height;}
public static void midHeight(int midHeight) {Face.midHeight = midHeight;}
public static void midWidth(int midWidth) {Face.midWidth = midWidth;}
public static void maxwidth(int tmp) {Face.maxwidth = tmp;}
public static void halfFace(Rectangle halfFace){Face.halfFace=halfFace;}


    

}
