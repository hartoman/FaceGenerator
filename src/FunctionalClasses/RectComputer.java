package FunctionalClasses;
import FacialFeatures.*;
import java.util.Random;
import Hair.HairStylezEnum;

//import Toolz.LineHandler.*;
import java.awt.*;

// contains all methods used for the creation of the facial features
public class RectComputer {

    // calculates coordinates for all facial features
    public static void calcAllFeatures() {

        // these two first
        Face.head().calcHead(); // gets ratios for the rest
        calcGuidingLines(8); // calculates assisting rectangles

        // the rest
        Face.eyes().setBoundingBoxParameters();
        Face.nose().setBoundingBoxParameters();
        Face.eyebrows().setBoundingBoxParameters();
        Face.ears().setBoundingBoxParameters();
        Face.mouth().setBoundingBoxParameters();

        Face.haircut().Temples().setBoundingBoxParameters();
        Face.haircut().TopOfHead().setBoundingBoxParameters();

        Face.facialHair().moustache().setBoundingBoxParameters();
        Face.facialHair().chin().setBoundingBoxParameters();

    }

    // divides the face to calculate bounding rectangles for facial features
    public static void calcGuidingLines(int numLines) {

        int x1 = (int) Face.halfFace().getX();
        int y1 = (int) Face.halfFace().getY();
        int width1 = (int) Face.halfFace().getWidth();
        int height1 = (int) Face.halfFace().getHeight();

        /* we get the bounding boxes for all facial features */
        int eyex=x1 + width1 * 2 / 6;
        int eyey=(y1 + height1 * 3 / numLines);
        int eyewidth=(x1 + width1 * 5 / 6);
        int eyeheight=(y1 + height1 * 4 / numLines);

                Face.eyes().boundRect = getBoundingBox(eyex,eyey ,eyewidth,eyeheight);
                Face.eyes().adjustBoundRect();  // adjusts bounding box for eye size and distance

                Face.eyebrows().boundRect = getBoundingBox(x1 + width1 * 2 / 6, (y1 + height1 * 5 / 2 / numLines),
                (x1 + width1),
                (y1 + height1 * 3 / numLines));

                Face.nose().boundRect = getBoundingBox(x1 + width1 * 4 / 6, (y1 + height1 * 3 / numLines),
                (x1 + width1),
                (y1 + height1 * 11 / 2 / numLines));

                Face.mouth().boundRect = getBoundingBox(x1 + width1 * 3 / 6, (y1 + height1 * 6 / numLines),
                symmetricHorizonal(x1 + width1 * 3 / 6),
                (y1 + height1 * 7 / numLines));

        Face.facialHair().moustache().boundRect = getBoundingBox(x1 + width1 * 3 / 6,
                (y1 + height1 * 11 / 2 / numLines),
                (x1 + width1),
                (y1 + height1 * 13 / 2 / numLines));

        Face.facialHair().chin().boundRect = getBoundingBox(x1 + width1 * 2 / 6, (y1 + height1 * 6 / numLines),
                (x1 + width1),
                (y1 + height1));

        // the box for the ears is a bit trickier.. we need to calculate the exact X of
        // the bezier for given Ys
        int yPos1 = (y1 + height1 * 5 / 2 / numLines);
        int yPos2 = (y1 + height1 * 5 / numLines);
        Point bezPoint1 = headPointOnYpos(yPos1);
        Point bezPoint2 = headPointOnYpos(yPos2);
        int rightbound = (int) Math.max(bezPoint1.x, bezPoint2.x);
        Face.ears().boundRect = getBoundingBox(rightbound - width1 * 3 / 6, yPos1, rightbound, yPos2);

        // for the top of the head we follow the same
        int widestTop = y1 + height1 * 5 / 2 / numLines;
        Point leftPoint = headPointOnYpos(widestTop);
        int leftLimit = leftPoint.x*9/10;
        Rectangle tmptoprect  = getBoundingBox(leftLimit, (y1),
                RectComputer.symmetricHorizonal(leftLimit),
                (widestTop));
        Face.haircut().TopOfHead().boundRect(tmptoprect);

   
        // for the temples
        Point temple1 = headPointOnYpos((y1 + height1 * 6 / 5 / numLines));
        Point temple2 = headPointOnYpos((y1 + height1 * 3 / numLines));
        int maxleftTemple = (Math.max(temple1.x,temple2.x));
        Rectangle tmptmplerect = getBoundingBox(leftLimit, (y1 + height1 * 6 / 5 / numLines),
        maxleftTemple,
                (y1 + height1 * 3 / numLines));
        Face.haircut().Temples().boundRect(tmptmplerect);


    }

    // returns the point of the face outline corresponding to height = yPos
    public static Point headPointOnYpos(int yPos) {

        Point x = new Point(Face.midWidth(), yPos);
        Point ptA = new Point(Face.midWidth(), Face.minHeight());
        Point ptB = new Point(Face.midWidth(), Face.height());
        Point cp0 = new Point(Face.head().bXL1(), Face.head().bY1());
        Point cp1 = new Point(Face.head().bXL2(), Face.head().bY2());
        LineHandler lh = new LineHandler();
        return lh.bezierPointAt(x, ptA, ptB, cp0, cp1);
    }

    // gets ths symmentrical of x on the horizontal axis
    public static int symmetricHorizonal(int x) {
        return Face.maxwidth() - x;
    }

    // gets the approximate bounding box of a shape
    public static Rectangle getBoundingBox(int leftMostX, int topMostY, int rightMostX, int bottomMostY) {
        return new Rectangle(leftMostX, topMostY, rightMostX - leftMostX, bottomMostY - topMostY);
    }

public static int randomBetween(int min, int max){
    Random random=new Random();
    return random.nextInt(max +1- min) + min;
}

public static boolean randomBool() {
    if (randomBetween(0, 10000000) % 2 == 0) {
        return true;
    } else {
        return false;
    }
}

public static void createRandomFace(String gender) {

    int moustSize, moustCurl, chinH, chinW, hairNum;

    boolean hasMoust = RectComputer.randomBool();
    boolean hasBeard = RectComputer.randomBool();

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

            } else {
                chinH = 0;
                chinW = 0;
            }
            hairNum = RectComputer.randomBetween(0, 7);
            break;

        case "gal":
            moustSize = 0;
            moustCurl = 0;
            chinH = 0;
            chinW = 0;
            hairNum = RectComputer.randomBetween(3, (HairStylezEnum.values().length - 1));
            break;

        default:
            moustSize = RectComputer.randomBetween(0, 20);
            moustCurl = RectComputer.randomBetween(-40, 40);
            chinH = RectComputer.randomBetween(0, 6);
            chinW = RectComputer.randomBetween(0, 6);
            hairNum = RectComputer.randomBetween(0, (HairStylezEnum.values().length - 1));
            break;
    }


    Face.setHairCut(hairNum);

    Face.facialHair().setFacialHair(moustSize, moustCurl, chinH, chinW);

    Face.head().setHead(RectComputer.randomBetween(0, 50), RectComputer.randomBetween(0, 50));

    Face.eyes().setEyes(RectComputer.randomBetween(-10, 10),
            RectComputer.randomBetween(-10, 10),
            RectComputer.randomBetween(-25, 25),
            RectComputer.randomBetween(10, 35),
            RectComputer.randomBetween(5, 15));

            Face.nose().noseSize(RectComputer.randomBetween(1, 8));

            Face.mouth().setMouth(RectComputer.randomBetween(0, 40),
            RectComputer.randomBetween(0, 30));

            Face.eyebrows().setEyebrows(RectComputer.randomBetween(0, 25),
            RectComputer.randomBetween(0, 4));

            Face.ears().earSize(RectComputer.randomBetween(0, 50));

    calcAllFeatures();

}



}

