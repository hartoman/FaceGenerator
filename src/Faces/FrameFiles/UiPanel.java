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


 package  Faces.FrameFiles;

import Faces.FunctionalClasses.AssistingMethods;
import Faces.FunctionalClasses.RandomFaces;
import javax.swing.*;

import  Faces.FacialFeatures.Face;
import  Faces.Emotions.Emotion;
import  Faces.EyeWear.EyeWearEnum;
import  Faces.Hair.HairStylezEnum;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

//sets up basic dimensions and layout
public class UiPanel extends JPanel {

    private int w, h;
    private Face face;

    public UiPanel(int w, int h) {
        this.w = w;
        this.h = h;
        this.face = new Face(w, h);

        initPanel();

    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
        FaceFrame.grid.setFace(face);
    }

    public void initPanel() {
        //
        setSize(w, h);

        //
        setMaximumSize(new Dimension(w, h));
        setBackground(Color.lightGray);

        this.setLayout(new GridLayout(2, 3, 10, 10));
        //
        setPreferredSize(new Dimension(w, h));

        add(new GeneralHeadPanel("General", Color.green, Color.BLACK, Color.DARK_GRAY));
        add(new FeaturesPanel("Features", Color.red, Color.BLACK, Color.DARK_GRAY));
        add(new HairPanel("Hair", Color.BLUE, Color.white, Color.gray));
        add(new EyesPanel("Eyes", Color.cyan, Color.BLACK, Color.darkGray));
        add(new FacialHairPanel("Facial Hair", Color.BLACK, Color.WHITE, Color.gray));
        add(new RandomCreationPanel("Random Faces", Color.magenta, Color.BLACK, Color.darkGray));

    }

    abstract class Subpanel extends JPanel {

        Color labelTextColor, sliderTextColor;
        JLabel titleLabel;

        public Subpanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            setBackground(backgroundColor);
            this.labelTextColor = labelTextColor;
            this.sliderTextColor = sliderTextColor;

            titleLabel = new JLabel(title);
            titleLabel.setForeground(labelTextColor);

            setLayout(new GridLayout(10, 1));
            setPreferredSize(new Dimension(w / 6, h / 2));

            createElements();
            addElements();
            attachListeners();
        }

        abstract void createElements();

        abstract void addElements();

        abstract void attachListeners();
    }

    class GeneralHeadPanel extends Subpanel {

        JButton skinColorButton,backgroundButton,clearBackground;
        JLabel ageLabel,eyeWearLabel;
        JSlider thiccSlider, headShapeSlider, ageSlider;
        int tmpthicc, tmpshape;
        JComboBox<String> eyeWearList;

        GeneralHeadPanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            super(title, backgroundColor, labelTextColor, sliderTextColor);
        }

        @Override
        public void createElements() {

            // tmpthicc = face.getHead().getThiccness();
            // tmpshape = face.getHead().getHeadShape();
            ageLabel = new JLabel("Age");
            eyeWearLabel = new JLabel("Eye Wear");

            skinColorButton = new JButton("Skin Color");
            backgroundButton = new JButton("Background Image");
            clearBackground = new JButton("Clear Background");

            thiccSlider = UiMethods.createSlider("Slick", 0, "Thicc", 40, 0, sliderTextColor,
                    "Determines thiccness of head");
            headShapeSlider = UiMethods.createSlider("Conical", 0, "Potato", 40, 0, sliderTextColor,
                    "Determines shape of head");

            ageSlider = UiMethods.createSlider("Young", 0, "Old", 10, 0, sliderTextColor,
                    "Wrinkles from old age");

            
            

            String[] eyewrlist = new String[EyeWearEnum.values().length];
            for (int i = 0; i < EyeWearEnum.values().length; i++) {
                eyewrlist[i] = EyeWearEnum.values()[i].toString();
            }
            eyeWearList = new JComboBox<String>(eyewrlist);

        }

        @Override
        public void addElements() {

            add(titleLabel);
            add(skinColorButton);
            add(thiccSlider);
            add(headShapeSlider);

            add(ageLabel);
            add(ageSlider);

            add(eyeWearLabel);
            add(eyeWearList);

            add(backgroundButton);
            add(clearBackground);

        }

        @Override
        public void attachListeners() {

            // skin color chooser button
            skinColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Skin color", new Color(255, 215, 169, 255));
                    if (tmpColor != null) {
                        face.setSkinColor(tmpColor);
                        face.setMakeupEyeColor(tmpColor);
                        skinColorButton.setBackground(tmpColor);
                        FaceFrame.grid.repaint();
                    }

                }
            });

            // head thiccness slider
            thiccSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
                    tmpthicc = thiccSlider.getValue();
                    face.getHead().setThiccness(tmpthicc);
                    face.getHead().setHeadShape(tmpshape);
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // head shape slider
            headShapeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
                    tmpshape = headShapeSlider.getValue();
                    face.getHead().setThiccness(tmpthicc);
                    face.getHead().setHeadShape(tmpshape);
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // head shape slider
            ageSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
                    // tmpshape = ageSlider.getValue();
                    // face.getHead().setThiccness(tmpthicc);
                    // face.getHead().setHeadShape(tmpshape);
                    face.setAge(ageSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // eyewear choice slider
            eyeWearList.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    EyeWearEnum ew[] = EyeWearEnum.values();
                    int chosenEw=0;

                    String selection = eyeWearList.getSelectedItem().toString();
                    for (int i = 0; i < ew.length; i++) {

                        if (ew[i].toString().equals(selection)) {
                            chosenEw = i;
                            break;
                        }
                    }
                    face.setEyeWear(chosenEw);
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            backgroundButton.addActionListener(new java.awt.event.ActionListener() {
                GroupedMenuBarFunctions gmf = new GroupedMenuBarFunctions();
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                String imgPath="";// = JColorChooser.showDialog(null, "Skin color", new Color(255, 215, 169, 255));
                           
                                    // dialog opens focused on current directory path
                JFileChooser fc = new JFileChooser(gmf.getCurrentDirectoryPath());

                // restrict the user to select files of all types
                fc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("select a .jpg, .png or .bmp file", "jpg","png","bmp");
                fc.addChoosableFileFilter(restrict);

                int choice = fc.showOpenDialog(null);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    imgPath = fc.getSelectedFile().getAbsolutePath();
                    System.out.println(imgPath);
                }

                    if ((imgPath!= null)&&(imgPath !="")) {
                        FaceFrame.grid.setBackgroundImage(imgPath);
                        FaceFrame.grid.repaint();
                    }
                }
            });

            clearBackground.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    FaceFrame.grid.setBackgroundImage(null);
                }
            });
        }
    }

    class FeaturesPanel extends Subpanel {

        JSlider noseSizeSlider, earSizeSlider, mouthSizeSlider, lipsSizeSlider;
        JButton lipsColorButton;
        JLabel noseLabel, earLabel, mouthLabel, lipsLabel;

        FeaturesPanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            super(title, backgroundColor, labelTextColor, sliderTextColor);

        }

        @Override
        public void createElements() {

            noseSizeSlider = UiMethods.createSlider("French", 1, "Caesar", 8, 1, sliderTextColor, "Size of nose");
            earSizeSlider = UiMethods.createSlider("Delicate", 0, "Radar", 50, 1, sliderTextColor, "Size of ears");
            mouthSizeSlider = UiMethods.createSlider("Diet", 0, "Maw", 40, 0, sliderTextColor, "Size of mouth");
            lipsSizeSlider = UiMethods.createSlider("None", 0, "Silicone", 30, 15, sliderTextColor, "Size of Lips");

            // colors labels
            noseLabel = new JLabel("Nose");
            earLabel = new JLabel("Ears");
            mouthLabel = new JLabel("Mouth");
            lipsLabel = new JLabel("Lips");
            UiMethods.setColortoLabels(labelTextColor, noseLabel, earLabel, mouthLabel, lipsLabel);

            lipsColorButton = new JButton("Lips Color");
        }

        @Override
        public void addElements() {
            add(titleLabel);
            add(noseLabel);
            add(noseSizeSlider);
            add(earLabel);
            add(earSizeSlider);
            add(mouthLabel);
            add(mouthSizeSlider);
            add(lipsLabel);
            add(lipsColorButton);
            add(lipsSizeSlider);

        }

        @Override
        public void attachListeners() {

            noseSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getNose().setNoseSize(noseSizeSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            earSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getEars().setEarSize(earSizeSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            mouthSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getMouth().setMouthSize(mouthSizeSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            lipsSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getMouth().setLipSize(lipsSizeSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // lips color button
            lipsColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Lips color", new Color(255, 190, 169, 255));
                    if(tmpColor!=null){
                        face.setLipsColor(tmpColor);
                        lipsColorButton.setBackground(tmpColor);
                        FaceFrame.grid.removeAll();
                        FaceFrame.grid.repaint();  
                    }
                }
            });
        }

    }

    class HairPanel extends Subpanel {

        JLabel eyebrowLabel, hairstyleLabel;

        JButton hairColorButton;
        JSlider eyebrowSizeSlider, eyebrowThiccnessSlider, hairStyleSlider, hairLengthSlider;

        HairPanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            super(title, backgroundColor, labelTextColor, sliderTextColor);
            setMinimumSize(new Dimension(w / 6, h / 2));
        }

        @Override
        public void createElements() {

            hairstyleLabel = new JLabel("Hair Style");
            eyebrowLabel = new JLabel("EyeBrows");

            UiMethods.setColortoLabels(labelTextColor, hairstyleLabel, eyebrowLabel);

            hairStyleSlider = UiMethods.createSlider("", 0, "", HairStylezEnum.values().length - 1, 2, sliderTextColor,
                    "Chooses Hairstyle");

            hairColorButton = new JButton("Hair Color");

            hairLengthSlider = UiMethods.createSlider("Short", 0, "Long", 50, 0, sliderTextColor,
                    "Hair Length");

            eyebrowSizeSlider = UiMethods.createSlider("None", 0, "Unibrow", 25, 0, sliderTextColor, "Eyebrow Size");
            eyebrowThiccnessSlider = UiMethods.createSlider("None", 0, "Bushy", 4, 2, sliderTextColor,
                    "Eyebrow Thiccness");

        }

        @Override
        public void addElements() {
            add(titleLabel);
            add(hairStyleSlider);
            add(hairLengthSlider);
            add(hairColorButton);
            add(eyebrowLabel);
            add(eyebrowSizeSlider);
            add(eyebrowThiccnessSlider);

        }

        @Override

        // hair color button
        public void attachListeners() {
            hairColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Hair color", new Color(0, 17, 16, 0));
                    
                    if(tmpColor!=null){
                        face.setHairColor(tmpColor);
                        hairColorButton.setBackground(tmpColor);
                        FaceFrame.grid.repaint();
                    }
                    
                    
                }
            });

            // hair style chooser slider
            hairStyleSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.setHairCut(hairStyleSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // hair length chooser slider (only works with hairstyles that have backhair)
            hairLengthSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getHaircut().getBackHair().setLength(hairLengthSlider.getValue());

                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // eyebrow size slider
            eyebrowSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getEyebrows().setEyebrowSize(eyebrowSizeSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // eyebrow thiccness slider
            eyebrowThiccnessSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getEyebrows().setEyebrowThiccness(eyebrowThiccnessSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });
        }
    }

    class EyesPanel extends Subpanel {

        JSlider eyeSizeslider, eyeDistlider, eyeAnglSlider, upEyelidSlider, lowerEyelidSlider;
        JButton irisColorButton, eyeballColorButton, eyeShadowColorButton;

        EyesPanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            super(title, backgroundColor, labelTextColor, sliderTextColor);

        }

        @Override
        public void createElements() {

            eyeSizeslider = UiMethods.createSlider("Beady", 0, "Large", 35, 25, sliderTextColor, "Eye Size");
            eyeDistlider = UiMethods.createSlider("Far", -10, "Close", 30, 10, sliderTextColor, "Eye Size");

            irisColorButton = new JButton("Iris Color");
            eyeballColorButton = new JButton("Eyeball Color");
            eyeShadowColorButton = new JButton("Eye Shadow Color");
            eyeAnglSlider = UiMethods.createSlider("Crooked", -25, "Asian", 25, 0, sliderTextColor, "Eye Angle");
            upEyelidSlider = UiMethods.createSlider("Punched", -10, "Rested", 10, 0, sliderTextColor,
                    "Bagginess of upper eyelid");
            lowerEyelidSlider = UiMethods.createSlider("Insomnia", -10, "Rested", 10, 0, sliderTextColor,
                    "Bagginess of lower eyelid");

        }

        @Override
        public void addElements() {
            add(titleLabel);
            add(eyeSizeslider);
            add(eyeDistlider);
            add(irisColorButton);
            add(eyeballColorButton);
            add(eyeShadowColorButton);
            add(eyeAnglSlider);
            add(upEyelidSlider);
            add(lowerEyelidSlider);

        }

        @Override
        public void attachListeners() {

            // eye size slider
            eyeSizeslider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getEyes().setSize(eyeSizeslider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // eye distance from middle axis slider
            eyeDistlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getEyes().setEyedist(eyeDistlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // eye angle slider
            eyeAnglSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getEyes().setAngle(eyeAnglSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // upper eyelid thiccness slider
            upEyelidSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getEyes().setDistortion1(upEyelidSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // lower eyelid thiccness slider
            lowerEyelidSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getEyes().setDistortion2(lowerEyelidSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // iris color chooser button
            irisColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Iris color", Color.DARK_GRAY);
                    if(tmpColor!=null){
                        face.setEyePupilColor(tmpColor);
                        irisColorButton.setBackground(tmpColor);
                        FaceFrame.grid.repaint();
                    }
                   
                }
            });

            // eyeball color chooser button
            eyeballColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Eyeball color", Color.white);
                    if(tmpColor!=null){
                        face.setEyeballColor(tmpColor);
                        eyeballColorButton.setBackground(tmpColor);
                        FaceFrame.grid.repaint();
                    }
                   
                }
            });

            // eyeshadow color chooser button
            eyeShadowColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Eye shadow color", new Color(255, 215, 169, 255));
                    if(tmpColor!=null){
                        face.setMakeupEyeColor(tmpColor);
                    eyeShadowColorButton.setBackground(tmpColor);
                    FaceFrame.grid.repaint();
                    }
                    
                }
            });

        }

    }

    class RandomCreationPanel extends Subpanel {

        JButton resetButton, RandMaleButton, RandFemaleButton, TotallyRandomButton;

        JComboBox<String> emotionList;
        JLabel emotionLabel;

        RandomCreationPanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            super(title, backgroundColor, labelTextColor, sliderTextColor);

        }

        @Override
        public void createElements() {
            resetButton = new JButton("Reset Face");
            RandMaleButton = new JButton("Random Guy");
            RandFemaleButton = new JButton("Random Gal");
            TotallyRandomButton = new JButton("Totally Random");

            emotionLabel = new JLabel("Emotion");
            UiMethods.setColortoLabels(labelTextColor, emotionLabel);

            String[] emotionlist = new String[Emotion.values().length];
            for (int i = 0; i < Emotion.values().length; i++) {
                emotionlist[i] = Emotion.values()[i].toString();
            }
            emotionList = new JComboBox<String>(emotionlist);

        }

        @Override
        public void addElements() {
            add(titleLabel);
            add(resetButton);
            add(new JSeparator());
            add(RandMaleButton);
            add(RandFemaleButton);
            add(TotallyRandomButton);
            add(new JSeparator());
            add(emotionLabel);
            add(emotionList);
        }

        @Override
        public void attachListeners() {

            // resets the face
            resetButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                   
                    face.resetFace();
                    FaceFrame.grid.setBackgroundImage(null);
                    FaceFrame.grid.repaint();

                }
            });
            // random guy face
            RandMaleButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    RandomFaces r = new RandomFaces(w, h);
                    face = r.RandomGuy();
                    FaceFrame.grid.setFace(face);

                    FaceFrame.grid.repaint();

                }
            });
            // random gal face
            RandFemaleButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    RandomFaces r = new RandomFaces(w, h);
                    face = r.RandomGal();
                    FaceFrame.grid.setFace(face);
                    FaceFrame.grid.repaint();

                }
            });
            // totally random face
            TotallyRandomButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    RandomFaces r = new RandomFaces(w, h);
                    face = r.RandomTotally();
                    FaceFrame.grid.setFace(face);
                    FaceFrame.grid.repaint();
                }
            });
            // set emotion
            emotionList.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    Emotion emotions[] = Emotion.values();
                    Emotion chosenEmot = emotions[0];
                    String selection = (String) emotionList.getSelectedItem();
                    for (int i = 0; i < emotions.length; i++) {
                        if (emotions[i].toString().equals(selection)) {
                            chosenEmot = emotions[i];
                            face.setExpression(chosenEmot);
                            break;
                        }
                    }
                 //   face.setExpression(chosenEmot);
                    FaceFrame.grid.repaint();
                }
            });
        }
    }

    class FacialHairPanel extends Subpanel {

        JSlider moustacheSizeSlider, moustacheCurveSlider,
                chinHairLengthSlider, chinHairWidthSlider,
                beardLengthSlider, beardWidthSlider;
        JLabel moustacheLabel, chinHairLabel, beardLabel;

        FacialHairPanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            super(title, backgroundColor, labelTextColor, sliderTextColor);

        }

        @Override
        public void createElements() {

            moustacheLabel = new JLabel("Moustache");
            chinHairLabel = new JLabel("Chin");
            beardLabel = new JLabel("Beard");

            UiMethods.setColortoLabels(labelTextColor, moustacheLabel, chinHairLabel, beardLabel);

            moustacheSizeSlider = UiMethods.createSlider("None", 0, "General", 20, 0, sliderTextColor,
                    "Moustache Size");

            moustacheCurveSlider = UiMethods.createSlider("FuManchu", -40, "Dali", 40, 0, sliderTextColor,
                    "Moustache Curve");

            chinHairLengthSlider = UiMethods.createSlider("None", 0, "Long", 6, 0, sliderTextColor,
                    "Chin Hair Length");

            chinHairWidthSlider = UiMethods.createSlider("None", 0, "Wide", 5, 0, sliderTextColor,
                    "Chin Hair Width");

            beardLengthSlider = UiMethods.createSlider("None", 0, "Long", 5, 0, sliderTextColor,
                    "Beard Length");

            beardWidthSlider = UiMethods.createSlider("None", 0, "Fuzzy", 10, 0, sliderTextColor,
                    "Beard Fuzziness");

        }

        @Override
        public void addElements() {
            add(titleLabel);
            add(moustacheLabel);
            add(moustacheSizeSlider);
            add(moustacheCurveSlider);
            add(chinHairLabel);
            add(chinHairLengthSlider);
            add(chinHairWidthSlider);
            add(beardLabel);
            add(beardLengthSlider);
            add(beardWidthSlider);

        }

        @Override
        public void attachListeners() {

            // moustache size slider
            moustacheSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getFacialHair().getMoustache().setMoustacheSize(moustacheSizeSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // moustache curve slider
            moustacheCurveSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getFacialHair().getMoustache().setCurled((moustacheCurveSlider.getValue()));
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // chin hair length slider
            chinHairLengthSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getFacialHair().getChinArea().setSoulpatchHeight(chinHairLengthSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // chin hair width slider
            chinHairWidthSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getFacialHair().getChinArea().setSoulpatchWidth(chinHairWidthSlider.getValue());
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            beardLengthSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getFacialHair().getCheeks().setBeardLength((beardLengthSlider.getValue()));
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            beardWidthSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getFacialHair().getCheeks().setBeardWidth((beardWidthSlider.getValue()));
                    AssistingMethods.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

        }
    }

}
