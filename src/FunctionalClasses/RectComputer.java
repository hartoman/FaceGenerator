package FunctionalClasses;
import FacialFeatures.Face;
import FrameFiles.FaceFrame;
import java.util.Random;
import Hair.HairStylezEnum;

//import Toolz.LineHandler.*;
import java.awt.*;

// contains all methods used for the creation of the facial features
public class RectComputer {

    // calculates coordinates for all facial features
    public static void calcAllFeatures(Face face) {

        // these two first
        face.getHead().calcHead(face); // gets ratios for the rest
        calcGuidingLines(8, face); // calculates assisting rectangles

        // the rest
        face.getEyes().setBoundingBoxParameters();
        face.getNose().setBoundingBoxParameters();
        face.getEyebrows().setBoundingBoxParameters();
        face.getEars().setBoundingBoxParameters();
        face.getMouth().setBoundingBoxParameters();

        face.getHaircut().getTemples().setBoundingBoxParameters();
        face.getHaircut().getTopOfHead().setBoundingBoxParameters();

        face.getFacialHair().getMoustache().setBoundingBoxParameters();
        face.getFacialHair().getChinArea().setBoundingBoxParameters();
        face.getFacialHair().getCheeks().setBoundingBoxParameters();

    }

    // divides the face to calculate bounding rectangles for facial features
    public static void calcGuidingLines(int numLines, Face face) {

        int bezHandles[] = face.getHead().getBezhandles();

        int x1 = (int) face.getHalfFace().getX();
        int y1 = (int) face.getHalfFace().getY();
        int width1 = (int) face.getHalfFace().getWidth();
        int height1 = (int) face.getHalfFace().getHeight();

        /* we get the bounding boxes for all facial features */
        int eyex = x1 + width1 * 2 / 6;
        int eyey = (y1 + height1 * 3 / numLines);
        int eyewidth = (x1 + width1 * 5 / 6);
        int eyeheight = (y1 + height1 * 4 / numLines);
        
        // eyes
        face.getEyes().boundRect = getBoundingBox(eyex, eyey, eyewidth, eyeheight);
        face.getEyes().adjustBoundRect(); // adjusts bounding box for eye size and distance

        // eyebrows
        face.getEyebrows().boundRect = getBoundingBox(x1 + width1 * 2 / 6, (y1 + height1 * 5 / 2 / numLines),
                (x1 + width1),
                (y1 + height1 * 3 / numLines));
        // nose
        face.getNose().boundRect = getBoundingBox(x1 + width1 * 4 / 6, (y1 + height1 * 3 / numLines),
                (x1 + width1),
                (y1 + height1 * 11 / 2 / numLines));
        //mouth
        face.getMouth().boundRect = getBoundingBox(x1 + width1 * 3 / 6, (y1 + height1 * 6 / numLines),
                symmetricHorizonal(x1 + width1 * 3 / 6),
                (y1 + height1 * 7 / numLines));

        // facial hair - moustache
        face.getFacialHair().getMoustache().boundRect = getBoundingBox(x1 + width1 * 3 / 6,
                (y1 + height1 * 11 / 2 / numLines),
                (x1 + width1),
                (y1 + height1 * 13 / 2 / numLines));

        // facial hair - chin
        face.getFacialHair().getChinArea().boundRect = getBoundingBox(x1 + width1 * 2 / 6, (y1 + height1 * 6 / numLines),
                (x1 + width1),
                (y1 + height1));

  
        // ears  (we need to calculate the exact X of the bezier for given Ys)
        int earsLeftY = (y1 + height1 * 5 / 2 / numLines);
        int earsRightY = (y1 + height1 * 5 / numLines);
        Point bezPoint1 = headPointOnYpos(earsLeftY, bezHandles);
        Point bezPoint2 = headPointOnYpos(earsRightY, bezHandles);
        int earsRightX = (int) Math.max(bezPoint1.x, bezPoint2.x);
        face.getEars().boundRect = getBoundingBox(earsRightX - width1 * 3 / 6, earsLeftY, earsRightX, earsRightY);

        // for the top of the head  (same)
        int topOfHeadLeftY = y1 + height1 * 5 / 2 / numLines;
        Point leftPoint = headPointOnYpos(topOfHeadLeftY, bezHandles);
        int topOfHeadLeftX = leftPoint.x * 9 / 10;
        Rectangle tmptoprect = getBoundingBox(topOfHeadLeftX, (y1),
                RectComputer.symmetricHorizonal(topOfHeadLeftX),
                (topOfHeadLeftY));
        face.getHaircut().getTopOfHead().setBoundRect(tmptoprect);

        // temples
        Point templeProbableRightY1 = headPointOnYpos((y1 + height1 * 6 / 5 / numLines), bezHandles);
        Point templeProbableRightY2 = headPointOnYpos((y1 + height1 * 3 / numLines), bezHandles);
        int maxleftTemple = (Math.max(templeProbableRightY1.x, templeProbableRightY2.x));
        Rectangle tmptmplerect = getBoundingBox(topOfHeadLeftX, (y1 + height1 * 6 / 5 / numLines),
                maxleftTemple,
                (y1 + height1 * 3 / numLines));
        face.getHaircut().getTemples().setBoundRect(tmptmplerect);

        // cheeks
        int cheeksLefty=(y1 + height1 * 4 / numLines);
        int cheeksLeftX=earsRightX;

       // int brY=y1 + height1 * 60/61;
        int cheeksRightY=y1 + height1;// * 59/61;
        int cheeksRightX=x1 + width1 * 4 / 6;
        
        Rectangle tmpbeard = getBoundingBox(cheeksLeftX, cheeksLefty, cheeksRightX, cheeksRightY);
        face.getFacialHair().getCheeks().setBoundRect(tmpbeard);

    }

    // returns the point of the face outline corresponding to height = yPos
    public static Point headPointOnYpos(int yPos, int[] bezHandles) {

        Point x = new Point(Face.getMidWidth(), yPos);
        Point ptA = new Point(Face.getMidWidth(), Face.getMinHeight());
        Point ptB = new Point(Face.getMidWidth(), Face.getHeight());
        Point cp0 = new Point(bezHandles[0], bezHandles[1]);
        Point cp1 = new Point(bezHandles[2], bezHandles[3]);
        LineHandler lh = new LineHandler();
        return lh.bezierPointAt(x, ptA, ptB, cp0, cp1);
    }

    // gets ths symmentrical of x on the horizontal axis
    public static int symmetricHorizonal(int x) {
        return Face.getMaxwidth() - x;
    }

    // gets the approximate bounding box of a shape
    public static Rectangle getBoundingBox(int leftMostX, int topMostY, int rightMostX, int bottomMostY) {
        return new Rectangle(leftMostX, topMostY, rightMostX - leftMostX, bottomMostY - topMostY);
    }

    public static int randomBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

    public static boolean randomBool() {
        if (randomBetween(0, 10000000) % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Face createRandomFace(String gender) {

        int moustSize, moustCurl, chinH, chinW, beardL, beardW,hairNum;

        boolean hasMoust = RectComputer.randomBool();
        boolean hasBeard = RectComputer.randomBool();


        Face face= new Face(FaceFrame.w(),FaceFrame.h());

        // for different tones we adjust the last value: [0.2, 1]
        float toneV = randomBetween(5, 10)*0.1f;
        float toneH = randomBetween(6, 10)*0.01f;
      //  toneH=0.08914729f;
        
        face.setSkinColor(Color.getHSBColor(toneH, 0.3372549f, toneV));
        face.setMakeupEyeColor(Color.getHSBColor(toneH, 0.3372549f, toneV));

        // the lips are just a little bit darker
        face.setLipsColor(Color.getHSBColor(toneH, 0.3372549f, toneV-0.1f));

        int hairR=255;
        int hairG=randomBetween(0, 255);
        int hairB=randomBetween(0, 51);
        face.setHairColor(new Color(hairR,hairG,hairB));
        

                // found out the right values by doing:
                // skinColor = new Color(255, 215, 169, 255);
                // float[] values = Color.RGBtoHSB(255, 215, 169, null);
                // for (float f : values) {
                // System.out.println(f);
                // }

        switch (gender) {

            case "guy":
                if (hasMoust) {
                    moustSize = RectComputer.randomBetween(0, 20);
                    moustCurl = RectComputer.randomBetween(-40, 40);
                } else {
                    moustSize = 0;
                    moustCurl = 0;
                }

                if (hasBeard) {
                    chinH = RectComputer.randomBetween(0, 6);
                    chinW = RectComputer.randomBetween(0, 6);
                    //TODO DEFINE MAXNUMBERS
                    beardL = RectComputer.randomBetween(0, 5);
                    beardW = RectComputer.randomBetween(0, 10);

                } else {
                    chinH = 0;
                    chinW = 0;
                    beardL = 0;
                    beardW = 0;
                }
                hairNum = RectComputer.randomBetween(0, 7);

                break;

            case "gal":


                face.setMakeupEyeColor(new Color(randomBetween(0,255),randomBetween(0,255),randomBetween(0,255)));
                face.setLipsColor(new Color(randomBetween(0,255),randomBetween(0,255),randomBetween(0,255)));

                moustSize = 0;
                moustCurl = 0;
                chinH = 0;
                chinW = 0;
                beardL = 0;
                beardW = 0;
                hairNum = RectComputer.randomBetween(9, (HairStylezEnum.values().length - 1));
                break;

            default:

                face.setMakeupEyeColor(new Color(randomBetween(0,255),randomBetween(0,255),randomBetween(0,255)));
                face.setLipsColor(new Color(randomBetween(0,255),randomBetween(0,255),randomBetween(0,255)));
                moustSize = RectComputer.randomBetween(0, 20);
                moustCurl = RectComputer.randomBetween(-40, 40);
                chinH = RectComputer.randomBetween(0, 6);
                chinW = RectComputer.randomBetween(0, 6);
                beardL = RectComputer.randomBetween(0, 5);
                beardW = RectComputer.randomBetween(0, 10);
                hairNum = RectComputer.randomBetween(0, (HairStylezEnum.values().length - 1));
                break;
        }

        face.setHairCut(hairNum);
        face.getHaircut().getBackHair().setLength(RectComputer.randomBetween(0, 50));

        face.getFacialHair().setFacialHair(moustSize, moustCurl, chinH, chinW,beardL,beardW);

        face.getHead().setHead(RectComputer.randomBetween(0, 50), RectComputer.randomBetween(0, 50));

        face.getEyes().setEyes(RectComputer.randomBetween(-10, 10),
                RectComputer.randomBetween(-10, 10),
                RectComputer.randomBetween(-25, 25),
                RectComputer.randomBetween(10, 35),
                RectComputer.randomBetween(5, 15));

        face.getNose().setNoseSize(RectComputer.randomBetween(1, 8));

        face.getMouth().setMouth(RectComputer.randomBetween(0, 40),
                RectComputer.randomBetween(0, 30));

        face.getEyebrows().setEyebrows(RectComputer.randomBetween(0, 25),
                RectComputer.randomBetween(0, 4));

        face.getEars().setEarSize(RectComputer.randomBetween(1, 50));

        calcAllFeatures(face);

        return face;
    }


}
