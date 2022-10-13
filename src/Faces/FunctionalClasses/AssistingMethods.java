package FunctionalClasses;
import FacialFeatures.Face;

import java.util.Random;


//import Toolz.LineHandler.*;
import java.awt.*;

// contains all methods used for the creation of the facial features
public class AssistingMethods {

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
                AssistingMethods.symmetricHorizonal(topOfHeadLeftX),
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

    // returns a random number between including min and max
    public static int randomBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

    // returns random number between min and max, but focused around the middle
    public static int randBtwnNormalized(int min, int max){
        int x=randomBetween(min,max);
        int y=randomBetween(min,max);
        int z=randomBetween(min,max);

        int result=Math.min(Math.max(x,y), z) ;


        return result;
    }

    // randomly gives true or false
    public static boolean randomBool() {
        if (randomBetween(0, 10000000) % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    





}
