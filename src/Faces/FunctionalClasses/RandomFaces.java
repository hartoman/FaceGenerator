package FunctionalClasses;

import FacialFeatures.Face;
import FacialHair.FacialHair;
import FunctionalClasses.AssistingMethods;
import Hair.HairCut;
import Hair.HairStylezEnum;
import FrameFiles.FaceFrame;
import java.awt.*;

public class RandomFaces {

    int w, h;
    PartsGenerator pg = new PartsGenerator();
    int hairNum;



    // constructor
    public RandomFaces(int w, int h) {
        this.w = w;
        this.h = h;
    }

    // creates random guys
    public Face RandomGuy() {

        Face face = new Face(w, h);
        pg.generateFacialFeatures(face);
        pg.generateColorsNoMakeup(face);

        pg.generateFacialHair(face);
        pg.generateHairColor(face);

        face.setAge(AssistingMethods.randBtwnNormalized(0, 10));
        
        pg.generateHairCut("guy", face);



        

        return face;
    }

    public Face RandomGal() {

        Face face = new Face(w, h);
        pg.generateFacialFeatures(face);
        pg.generateColorsWithMakeup(face);
        face.setAge(AssistingMethods.randBtwnNormalized(0, 10));
        pg.generateHairCut("gal", face);
        return face;

    }

    public Face RandomTotally() {

        Face face = new Face(w, h);

     /*   face.setMakeupEyeColor(new Color(AssistingMethods.randomBetween(0, 255), AssistingMethods.randomBetween(0, 255),
                AssistingMethods.randomBetween(0, 255)));
        face.setLipsColor(new Color(AssistingMethods.randomBetween(0, 255), AssistingMethods.randomBetween(0, 255),
                AssistingMethods.randomBetween(0, 255))); 
     
        hairNum = AssistingMethods.randomBetween(0, (HairStylezEnum.values().length - 1));

*/

        return face;
    }

    class PartsGenerator {

        void generateFacialFeatures(Face face) {

            face.getHead().setHead(AssistingMethods.randBtwnNormalized(0, 50), AssistingMethods.randomBetween(0, 50));

            face.getEyes().setEyes(AssistingMethods.randBtwnNormalized(-10, 10),
                    AssistingMethods.randBtwnNormalized(-10, 10),
                    AssistingMethods.randBtwnNormalized(-25, 25),
                    AssistingMethods.randBtwnNormalized(0, 35),
                    AssistingMethods.randBtwnNormalized(-10, 30));

            face.getNose().setNoseSize(AssistingMethods.randBtwnNormalized(1, 8));

            face.getMouth().setMouth(AssistingMethods.randBtwnNormalized(0, 40),
                    AssistingMethods.randBtwnNormalized(0, 30));

            face.getEyebrows().setEyebrows(AssistingMethods.randBtwnNormalized(0, 25),
                    AssistingMethods.randBtwnNormalized(0, 4));

            face.getEars().setEarSize(AssistingMethods.randBtwnNormalized(1, 50));

            AssistingMethods.calcAllFeatures(face);

        }

        void generateFacialHair(Face face) {


            int moustSize, moustCurl, chinH, chinW, beardL, beardW, hairNum;

            boolean hasMoust = AssistingMethods.randomBool();
            boolean hasBeard = AssistingMethods.randomBool();

            /*
             * if (hasMoust) {
             * moustSize = AssistingMethods.randomBetween(0, 20);
             * moustCurl = AssistingMethods.randomBetween(-40, 40);
             * } else {
             * moustSize = 0;
             * moustCurl = 0;
             * }
             * 
             * if (hasBeard) {
             * chinH = AssistingMethods.randomBetween(0, 6);
             * chinW = AssistingMethods.randomBetween(0, 6);
             * //TODO DEFINE MAXNUMBERS
             * beardL = AssistingMethods.randomBetween(0, 5);
             * beardW = AssistingMethods.randomBetween(0, 10);
             * 
             * } else {
             * chinH = 0;
             * chinW = 0;
             * beardL = 0;
             * beardW = 0;
             * }
             * 
             * // face.getFacialHair().setFacialHair(moustSize, moustCurl, chinH,
             * chinW,beardL,beardW);
             */
        }

// TODO PROBLEM
        void generateHairCut(String type, Face face) {

            int hairNum=0;

            switch (type) {
                case "guy":
                    hairNum = AssistingMethods.randomBetween(0, 8);
                    break;
                case "gal":
                    hairNum = AssistingMethods.randomBetween(9, (HairStylezEnum.values().length - 1));
                    break;
                default:
                    hairNum = AssistingMethods.randomBetween(0, (HairStylezEnum.values().length - 1));
                    break;
            }

            face.setHairCut(hairNum);
            face.getHaircut().getTopOfHead().setHaircutColors(face.getHairColor(),face.getSkinColor());
            face.getHaircut().getBackHair().setLength(AssistingMethods.randomBetween(0, 50));

        }


        void generateHairColor(Face face){

            int hairR = 255;
            int hairG = AssistingMethods.randomBetween(0, 255);
            int hairB = AssistingMethods.randomBetween(0, 51);

            Color rgbCol = new Color(hairR, hairG, hairB);

            face.setHairColor(rgbCol);

            // face.setHairColor(new Color(hairR,hairG,hairB));

            // found out the right values by doing:
            // skinColor = new Color(255, 215, 169, 255);
            // float[] values = Color.RGBtoHSB(255, 215, 169, null);
            // for (float f : values) {
            // System.out.println(f);
            // }

                       
        }

        void generateColorsNoMakeup(Face face) {

            // for different tones we adjust the last value: [0.2, 1]
            float toneV = AssistingMethods.randomBetween(5, 10) * 0.1f;
            float toneH = AssistingMethods.randomBetween(6, 10) * 0.01f;
            // toneH=0.08914729f;

            face.setSkinColor(Color.getHSBColor(toneH, 0.3372549f, toneV));

            face.setMakeupEyeColor(Color.getHSBColor(toneH, 0.3372549f, toneV));

            // the lips are just a little bit darker
            face.setLipsColor(Color.getHSBColor(toneH, 0.3372549f, toneV - 0.1f));

        }


        void generateColorsWithMakeup(Face face) {

            // for different tones we adjust the last value: [0.2, 1]
            float toneV = AssistingMethods.randomBetween(5, 10) * 0.1f;
            float toneH = AssistingMethods.randomBetween(6, 10) * 0.01f;
            // toneH=0.08914729f;

            face.setSkinColor(Color.getHSBColor(toneH, 0.3372549f, toneV));

            face.setMakeupEyeColor(new Color(AssistingMethods.randomBetween(0, 255),
                    AssistingMethods.randomBetween(0, 255), AssistingMethods.randomBetween(0, 255)));
            face.setLipsColor(new Color(AssistingMethods.randomBetween(0, 255), AssistingMethods.randomBetween(0, 255),
                    AssistingMethods.randomBetween(0, 255)));
        }

    }

}