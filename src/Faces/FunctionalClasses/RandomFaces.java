// Copyright © 2022 Christos Chartomatsidis

/*
 This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version. This program is distributed in the hope that it will be
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
    General Public License for more details. You should have received a copy of the GNU 
    General Public License along with this program. If not, see http://www.gnu.org/licenses/. 

 */

 package  Faces.FunctionalClasses;

import  Faces.FacialFeatures.Face;
import  Faces.Hair.HairStylezEnum;
import java.awt.*;

import  Faces.EyeWear.EyeWearEnum;

public class RandomFaces {

    int w, h;
    PartsGenerator pg;
    int hairNum;

    // constructor
    public RandomFaces(int w, int h) {
        this.w = w;
        this.h = h;
    }

    // creates random guys
    public Face RandomGuy() {

        // Face face = new Face(w, h);
        pg = new PartsGenerator(new Face(w, h));

        pg.generateColorsNoMakeup();
        pg.generateFacialHair();

        pg.generateNaturalHairColor();
        pg.generateHairCut("guy");

        pg.generateEyeWear();
        pg.generateFacialFeatures();

        pg.generateEyeColor();

        return pg.getFace();
    }

    // creates random gals
    public Face RandomGal() {

        pg = new PartsGenerator(new Face(w, h));

        pg.generateColorsWithMakeup();

        pg.generateNaturalHairColor();
        pg.generateHairCut("gal");

        pg.generateEyeWear();
        pg.generateFacialFeatures();

        pg.generateEyeColor();

        return pg.getFace();

    }

    // creates a person of totally random features
    public Face RandomTotally() {

        pg = new PartsGenerator(new Face(w, h));

        // has both characteristics
        pg.generateColorsWithMakeup();
        pg.generateFacialHair();

        pg.generateNaturalHairColor();
        // all haircuts possible
        pg.generateHairCut(" ");

        pg.generateEyeWear();
        pg.generateFacialFeatures();

        // all iris colors possible
        pg.getFace().setEyePupilColor(new Color(AssistingMethods.randomBetween(0,255),AssistingMethods.randomBetween(0,255),AssistingMethods.randomBetween(0,255)));
        
        return pg.getFace();

    }

    class PartsGenerator {

        Face face;

        PartsGenerator(Face face) {
            this.face = face;
            face.setAge(AssistingMethods.rollThreeKeepMin(0, 10));
        }

        void generateFacialFeatures() {

            face.getHead().setHead(AssistingMethods.rollThreeKeepMin(0, 50), AssistingMethods.rollThreeKeepMin(0, 50));

            face.getEyes().setEyes(AssistingMethods.rollThreeKeepMed(-10, 10),
                    AssistingMethods.rollThreeKeepMed(-10, 10),
                    AssistingMethods.rollThreeKeepMed(-15, 15),
                    AssistingMethods.rollThreeKeepMed(0, 20),
                    AssistingMethods.rollThreeKeepMed(15, 30));

            face.getNose().setNoseSize(AssistingMethods.rollThreeKeepMed(1, 8));

            face.getMouth().setMouth(AssistingMethods.rollThreeKeepMed(0, 40),
                    AssistingMethods.rollThreeKeepMed(0, 30));

            face.getEyebrows().setEyebrows(AssistingMethods.rollThreeKeepMed(0, 25),
                    AssistingMethods.rollThreeKeepMed(0, 4));

            face.getEars().setEarSize(AssistingMethods.rollThreeKeepMin(1, 50));

            AssistingMethods.calcAllFeatures(face);

        }

        void generateFacialHair() {

            int moustSize, moustCurl,  chinW, beardL, beardW;

            boolean hasMoust = AssistingMethods.randomBool();
            boolean hasGoatee = AssistingMethods.randomBool();
            boolean hasBeard = AssistingMethods.randomBool();

            int minChinW = 0;
            int minChinL = 0;
            int minMoustSize = 0;
            int chinH=0;

            // sets up beard - if the cheeks have hair, then there will always be a goatee
            if (hasBeard) {
                beardL = AssistingMethods.rollThreeKeepMed(1, 5);
                beardW = AssistingMethods.rollThreeKeepMed(1, 10);

                hasGoatee = true;
                minMoustSize = 7;
                minChinW = 4;
                chinH = beardL + 1;

            } else {
                beardL = 0;
                beardW = 0;
            }

            // sets up moustache
            if (hasMoust) {
                moustSize = AssistingMethods.rollThreeKeepMed(minMoustSize, 15);
                moustCurl = AssistingMethods.rollThreeKeepMed(-20, 20); // -40,40
            } else {
                moustSize = 0;
                moustCurl = 0;
            }

            // sets up goatee
            if (hasGoatee) {

                // if there is hair on the cheeks then minimal goatee dimensions adapt
                if (!hasBeard) {
                    chinH = AssistingMethods.rollThreeKeepMed(minChinL, 5);
                }

                chinW = AssistingMethods.rollThreeKeepMed(minChinW, 5);

                if(chinW>chinH){
                    chinH=chinW+AssistingMethods.randomBetween(-1, 1);
                }

            } else {
                chinH = 0;
                chinW = 0;
            }

            // puts everything together
            face.getFacialHair().setFacialHair(moustSize, moustCurl, chinH,
                    chinW, beardL, beardW);
        }

        // creates haircut from range for guys,gals, or all-inclusive
        void generateHairCut(String type) {

            int hairNum = 0;

            switch (type) {
                case "guy":
                    hairNum = AssistingMethods.randomBetween(0, 9);
                    break;
                case "gal":
                    hairNum = AssistingMethods.randomBetween(10, (HairStylezEnum.values().length - 1));
                    break;
                default:
                    hairNum = AssistingMethods.randomBetween(0, (HairStylezEnum.values().length - 1));
                    break;
            }

            face.setHairCut(hairNum);
            face.getHaircut().getBackHair().setLength(AssistingMethods.randomBetween(0, 50));

        }

        // creates the color of the hair semi-dependent on the age
        void generateNaturalHairColor() {

            switch (face.getAge()) {

                case 7:
                    face.setHairColor(Color.gray);
                    break;
                case 8:
                    face.setHairColor(Color.lightGray);
                case 9:
                    face.setHairColor(Color.lightGray);
                    break;
                case 10:
                    face.setHairColor(Color.white);
                    break;
                default: {

                    // we create an RGB color and transform it to HSB
                    int hairR = 255;
                    int hairG = AssistingMethods.rollThreeKeepMed(0, 255);
                    int hairB = AssistingMethods.rollThreeKeepMed(0, 51);

                    // manually translate the color to HSB
                    float[] values = Color.RGBtoHSB(hairR, hairG, hairB, null);
                    // for (float f : values) { System.out.println(f);}

                    float sCol = (float) (AssistingMethods.rollThreeKeepMed(1, 100) * 0.01);
                    float bCol = (float) (AssistingMethods.rollThreeKeepMed(1, 100) * 0.01);

                    Color hsbCol = Color.getHSBColor(values[0], sCol, bCol);
                    face.setHairColor(hsbCol);

                }
                    break;
            }

        }

        // assigns the color of the eyes
        void generateEyeColor(){

            // maximum sum of values between 102 and 204
            int totalRGB = AssistingMethods.randomBetween(102,204);

            // each value between 0 and 102
            int redVal = AssistingMethods.randomBetween(0,102);
            int greenVal = AssistingMethods.randomBetween(51,102);
            int blueVal = Math.max(0,totalRGB-(redVal+greenVal));

            // manually translate the color to HSB
            float[] values = Color.RGBtoHSB(redVal, greenVal, blueVal, null);
            // for (float f : values) { System.out.println(f);}

            // we mess around only with the saturation
            float sCol = (float) (AssistingMethods.rollThreeKeepMed(1, 100) * 0.01);
            // but for the lightness me top-up at the lightness of the default color
            float bCol = (float) (AssistingMethods.rollThreeKeepMed(1, (int)(values[2]*100)) * 0.01);

            // then we translate back to color
            Color hsbCol = Color.getHSBColor(values[0], sCol, bCol);

            face.setEyePupilColor(hsbCol);

        }

        // creates random skin color without wearing make-up
        void generateColorsNoMakeup() {

            // for different tones we adjust the last value: [0.2, 1]
            float toneV = AssistingMethods.rollThreeKeepMed(5, 10) * 0.1f;
            float toneH = AssistingMethods.rollThreeKeepMed(6, 10) * 0.01f;
            // toneH=0.08914729f;

            face.setSkinColor(Color.getHSBColor(toneH, 0.3372549f, toneV));
            face.setMakeupEyeColor(Color.getHSBColor(toneH, 0.3372549f, toneV));

            // the lips are just a little bit darker
            face.setLipsColor(Color.getHSBColor(toneH, 0.3372549f, toneV - 0.1f));

        }

        // color palette WITH make-up
        void generateColorsWithMakeup() {

            // for different tones we adjust the last value: [0.2, 1]
            float toneV = AssistingMethods.rollThreeKeepMed(5, 10) * 0.1f;
            float toneH = AssistingMethods.rollThreeKeepMed(6, 10) * 0.01f;

            face.setSkinColor(Color.getHSBColor(toneH, 0.3372549f, toneV));

            face.setMakeupEyeColor(new Color(AssistingMethods.randomBetween(0, 255),
                    AssistingMethods.randomBetween(0, 255), AssistingMethods.randomBetween(0, 255)));
            face.setLipsColor(new Color(AssistingMethods.randomBetween(0, 255), AssistingMethods.randomBetween(0, 255),
                    AssistingMethods.randomBetween(0, 255)));
        }

        void generateEyeWear(){
            boolean wearsGlasses = AssistingMethods.randomBool();
            if (wearsGlasses){
                face.setEyeWear(AssistingMethods.randomBetween(0, EyeWearEnum.values().length-1));
            }
            
        }

        public Face getFace() {
            return face;
        }

        public void setFace(Face face) {
            this.face = face;
        }

    }

}