/*
 * Made by Christos Chartomatsidis, 2022
 * This application is free to use, but it comes as-is:
 * I hold no responsibility for any damage or loss of that may arise from it's use.
 * Attribution is not required, but would be greatly appreciated.
 * For any comments, bug-reports, and ideas do not hesitate to contact me at:
 * hartoman@gmail.com
 */

package FacialFeatures;

import FacialHair.FacialHair;
import FunctionalClasses.*;

import java.awt.*;
import java.io.Serializable;

import Emotions.Emotion;
import Hair.HairCut;
import Hair.HairStylezEnum;

/**
 *
 * @author chris
 */

public class Face implements Serializable {

    private static int minHeight, height, maxwidth, midHeight, midWidth;
    private Color makeupEyeColor, eyePupilColor, eyeballColor, skinColor, hairColor, lipsColor;
    private Rectangle halfFace; // marks the area of the left half of the face

    private int age;

    private Head head;
    private Eyes eyes;
    private Nose nose;
    private Eyebrows eyebrows;
    private Mouth mouth;
    private Ears ears;
    private HairCut haircut;
    private FacialHair facialHair;

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

        facialHair = new FacialHair();

        // initializes face to blank state
        resetFace();

    }

    // puts everything together. newer calls overwrite older, so the order matters
    public void drawFace(Graphics2D g2d) {

        // on the vack there is the long hair
        haircut.drawBackHair(g2d, hairColor);

        // on the face are the main facial features
        head.drawHead(g2d, skinColor);
        eyes.drawEyes(g2d, eyeballColor, eyePupilColor, makeupEyeColor);

        mouth.drawMouth(g2d, lipsColor);

        // draw the rest of hair
        haircut.drawHairCut(g2d, hairColor, skinColor);
        // ears above hair
        ears.drawEars(g2d, skinColor);
        // facial hair above ears
        facialHair.drawFacialHair(g2d, hairColor);
        // nose above facial hair
        nose.drawNose(g2d, skinColor);
        // eyebrows above nose
        eyebrows.drawEyebrows(g2d, hairColor);

         DrawingMethods.drawGuidingLines(8, g2d,this);
    }

    // resets all parameters of the face
    public void resetFace() {

        resetColors();
        /* adjustable parameters */

        // head shape
        // thickness: [0,50]
        // shape: [0,50]
        head.setHead(0, 0);

        // eyes
        // distortion1: width for upper eyeball -- [-10,10]
        // distortion1: for lower eyeball -- [-10,10]
        // angle: [-25,25]
        // size: [10,35]
        // eyedist: [5,15]
        eyes.setEyes(10, 0, 0, 25, 10);

        // nose: [1,8]
        nose.setNoseSize(1);

        // mouth
        // mouthSize: [0,40]
        // lipSize: [0,30]
        mouth.setMouth(0, 15);

        // eyebrows
        // eyebrows size: [0,25]
        // eyebrows thiccness: [0,4]
        eyebrows.setEyebrows(0, 2);

        // ears: [0,50]
        ears.setEarSize(1);

        // facial hair
        // moustacheSize: [0,20]
        // curled: [-40,40]
        // soulpatchHeight: [0,6]
        // soulpatchWidth: [0,6]
        facialHair.setFacialHair(0, 0, 0, 0, 0, 0);

        setHairCut(2);

        setExpression(Emotion.POKERFACE);
        // alt: setExpression(0, 45, 0, 0);

        // TODO unify setAge
        facialHair.getCheeks().setAge(4);       //[0,4]

        RectComputer.calcAllFeatures(this);
    }

    // resets all colors to default
    public void resetColors() {

        eyePupilColor = Color.darkGray;
        eyeballColor = Color.white;
        skinColor = Color.white;

        // for different tones we adjust the last value: [0.2, 1]
     //   skinColor = Color.getHSBColor(0.08914729f, 0.3372549f, 0.2f);
        // to find out the right values:
        // skinColor = new Color(255, 215, 169, 255);
        // float[] values = Color.RGBtoHSB(255, 215, 169, null);
        // for (float f : values) {
        // System.out.println(f);
        // }

        makeupEyeColor = Color.DARK_GRAY;
        hairColor = Color.black;
        lipsColor = Color.lightGray;// Color.red;;
    }

    // sets the haircut
    public void setHairCut(int selection) {

        haircut = HairStylezEnum.values()[selection].makeHair();

    }

    // sets the facial expression
    public void setExpression(Emotion emotion) {
        getEyebrows().setAnger(emotion.getAnger()); // curvature: [-50,50]
        getEyes().setDistortion3(emotion.getEyeOpenness()); // eye openness: [0,75]
        getMouth().setOpenness(emotion.getMouthOpenness()); // openness: [0,40]
        getMouth().setSmile(emotion.getSmile()); // smile: [-15,15] //TODO TRY -20,20

    }

    /************ GETTERS AND SETTERS ********************/

    // getters
    public static int getMaxwidth() {
        return maxwidth;
    }

    public static int getMinHeight() {
        return minHeight;
    }

    public static int getHeight() {
        return height;
    }

    public static int getMidHeight() {
        return midHeight;
    }

    public static int getMidWidth() {
        return midWidth;
    }

    public Rectangle getHalfFace() {
        return halfFace;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Color getSkinColor() {
        return skinColor;
    }

    public Color getEyeballColor() {
        return eyeballColor;
    }

    public Color getEyePupilColor() {
        return eyePupilColor;
    }

    public Color getMakeupEyeColor() {
        return makeupEyeColor;
    }

    public Color getLipsColor() {
        return lipsColor;
    }

    public Head getHead() {
        return head;
    }

    public Eyes getEyes() {
        return eyes;
    }

    public FacialHair getFacialHair() {
        return facialHair;
    }

    public HairCut getHaircut() {
        return haircut;
    }

    public Eyebrows getEyebrows() {
        return eyebrows;
    }

    public Nose getNose() {
        return nose;
    }

    public Mouth getMouth() {
        return mouth;
    }

    public Ears getEars() {
        return ears;
    }

    // setters

    public static void setMinHeight(int minHeight) {
        Face.minHeight = minHeight;
    }

    public static void setHeight(int height) {
        Face.height = height;
    }

    public static void setMidHeight(int midHeight) {
        Face.midHeight = midHeight;
    }

    public static void setMidWidth(int midWidth) {
        Face.midWidth = midWidth;
    }

    public static void setMaxwidth(int tmp) {
        Face.maxwidth = tmp;
    }

    public void setHalfFace(Rectangle halfFace) {
        this.halfFace = halfFace;
    }

    public void setSkinColor(Color skinColor) {
        this.skinColor = skinColor;
    }

    public void setLipsColor(Color lipsColor) {
        this.lipsColor = lipsColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setMakeupEyeColor(Color makeupEyeColor) {
        this.makeupEyeColor = makeupEyeColor;
    }

    public void setEyePupilColor(Color eyePupilColor) {
        this.eyePupilColor = eyePupilColor;
    }

    public void setEyeballColor(Color eyeballColor) {
        this.eyeballColor = eyeballColor;
    }

}
