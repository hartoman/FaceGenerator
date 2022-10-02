package FrameFiles;

import javax.swing.*;

import FacialFeatures.Face;
import Emotions.Emotion;
import FunctionalClasses.*;
import Hair.HairStylezEnum;


import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


//sets up basic dimensions and layout
public class UiPanel extends JPanel {

    int w, h;
    Face face;

    public UiPanel(int w, int h) {
        this.w = w;
        this.h = h;
        this.face=new Face(w,h);

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

        this.setLayout(new GridLayout(2, 3,10,10));
        //
        setPreferredSize(new Dimension(w , h ));

        add(new GeneralHeadPanel("General", Color.green, Color.BLACK, Color.DARK_GRAY));
        add(new FeaturesPanel("Features", Color.red, Color.BLACK, Color.DARK_GRAY));
        add(new HairPanel("Hair", Color.BLUE, Color.white, Color.gray));
        add(new EyesPanel("Eyes", Color.cyan, Color.BLACK, Color.darkGray));
        add(new ExpressionPanel("Various", Color.BLACK, Color.WHITE, Color.gray));
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

        JButton skinColorButton;
        JSlider thiccSlider, headShapeSlider;
        int tmpthicc, tmpshape;

        GeneralHeadPanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            super(title, backgroundColor, labelTextColor, sliderTextColor);
        }

        @Override
        public void createElements() {

            tmpthicc = face.geHead().getThiccness();
            tmpshape = face.geHead().getHeadShape();

            skinColorButton = new JButton("Skin Color");
            thiccSlider = UiMethods.createSlider("Slick", 0, "Thicc", 40, 0, sliderTextColor,
                    "Determines thiccness of head");
            headShapeSlider = UiMethods.createSlider("Conical", 0, "Potato", 40, 0, sliderTextColor,
                    "Determines shape of head");
            
            System.out.println(javax.swing.UIManager.getDefaults().getFont("Label.font"));
        }

        @Override
        public void addElements() {

            add(titleLabel);
            add(skinColorButton);
            add(thiccSlider);
            add(headShapeSlider);

        }

        @Override
        public void attachListeners() {

            // skin color chooser button
            skinColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Skin color", new Color(255, 215, 169, 255));
                    face.setSkinColor(tmpColor);
                    face.setMakeupEyeColor(tmpColor);
                    skinColorButton.setBackground(tmpColor);
                    FaceFrame.grid.repaint();
                }
            });

            // head thiccness slider
            thiccSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
                    tmpthicc = thiccSlider.getValue();
                    face.geHead().setThiccness(tmpthicc);
                    face.geHead().setHeadShape(tmpshape);
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // head shape slider
            headShapeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
                    tmpshape = headShapeSlider.getValue();
                    face.geHead().setThiccness(tmpthicc);
                    face.geHead().setHeadShape(tmpshape);
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
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
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            earSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getEars().setEarSize(earSizeSlider.getValue());
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            mouthSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getMouth().setMouthSize(mouthSizeSlider.getValue());
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            lipsSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {

                    face.getMouth().setLipSize(lipsSizeSlider.getValue());
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // lips color button
            lipsColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Lips color", new Color(255, 190, 169, 255));
                    face.setLipsColor(tmpColor);
                    lipsColorButton.setBackground(tmpColor);
                    FaceFrame.grid.removeAll();
                    FaceFrame.grid.repaint();
                }
            });
        }

    }

    class HairPanel extends Subpanel {

        JLabel hairstyleLabel,  moustacheLabel, chinHairLabel;//facialHairLabel,
        // JComboBox hairStyleList;
        JButton hairColorButton;
        JSlider hairStyleSlider, moustacheSizeSlider, moustacheCurveSlider, chinHairLengthSlider, chinHairWidthSlider, hairLengthSlider;

        HairPanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            super(title, backgroundColor, labelTextColor, sliderTextColor);
            setMinimumSize(new Dimension(w / 6, h / 2));
        }

        @Override
        public void createElements() {

            hairstyleLabel = new JLabel("Hair Style");
            moustacheLabel = new JLabel("Moustache");
            chinHairLabel = new JLabel("Chin");

            UiMethods.setColortoLabels(labelTextColor, hairstyleLabel, moustacheLabel, chinHairLabel);

            hairStyleSlider = UiMethods.createSlider("", 0, "", HairStylezEnum.values().length-1, 0, sliderTextColor,
                    "Chooses Hairstyle");

            hairLengthSlider  = UiMethods.createSlider("Short", 0, "Long", 50, 0, sliderTextColor,
            "Hair Length");

            hairColorButton = new JButton("Hair Color");

            moustacheSizeSlider = UiMethods.createSlider("None", 0, "General", 20, 0, sliderTextColor,
                    "Moustache Size");

            moustacheCurveSlider = UiMethods.createSlider("FuManchu", -40, "Dali", 40, 0, sliderTextColor,
                    "Moustache Curve");

            chinHairLengthSlider = UiMethods.createSlider("None", 0, "Max", 6, 0, sliderTextColor,
                    "Chin Hair Length");

            chinHairWidthSlider = UiMethods.createSlider("None", 0, "Max", 6, 0, sliderTextColor,
                    "Chin Hair Width");

        }

        @Override
        public void addElements() {
            add(titleLabel);
            add(hairStyleSlider);
            add(hairLengthSlider);
            add(hairColorButton);
            add(moustacheLabel);
            add(moustacheSizeSlider);
            add(moustacheCurveSlider);
            add(chinHairLabel);
            add(chinHairLengthSlider);
            add(chinHairWidthSlider);
        }

        @Override

        // hair color button
        public void attachListeners() {
            hairColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Hair color", new Color(0, 17, 16, 0));
                    face.setHairColor(tmpColor);
                    hairColorButton.setBackground(tmpColor);
                    FaceFrame.grid.repaint();
                }
            });
      

        // hair style chooser slider
        hairStyleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {

                face.setHairCut(hairStyleSlider.getValue());
                RectComputer.calcAllFeatures(face);
                FaceFrame.grid.repaint();
            }
        });
        
        // hair length chooser slider (only works with hairstyles that have backhair)
        hairLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {

                face.getHaircut().getBackHair().setLength(hairLengthSlider.getValue());
                
                RectComputer.calcAllFeatures(face);
                FaceFrame.grid.repaint();
            }
        });


        // moustache size slider
        moustacheSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {

                face.getFacialHair().getMoustache().setMoustacheSize(moustacheSizeSlider.getValue());
                RectComputer.calcAllFeatures(face);
                FaceFrame.grid.repaint();
            }
        });

        // moustache curve slider
        moustacheCurveSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {

                face.getFacialHair().getMoustache().setCurled((moustacheCurveSlider.getValue()));
                RectComputer.calcAllFeatures(face);
                FaceFrame.grid.repaint();
            }
        });

        // chin hair length slider
        chinHairLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {

                face.getFacialHair().getChinArea().setSoulpatchHeight(chinHairLengthSlider.getValue());
                RectComputer.calcAllFeatures(face);
                FaceFrame.grid.repaint();
            }
        });

        // chin hair width slider
        chinHairWidthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {

                face.getFacialHair().getChinArea().setSoulpatchWidth(chinHairWidthSlider.getValue());
                RectComputer.calcAllFeatures(face);
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

            eyeSizeslider = UiMethods.createSlider("Canada", 10, "Large", 35, 25, sliderTextColor, "Eye Size");
            eyeDistlider = UiMethods.createSlider("Close", 5, "Far", 15, 10, sliderTextColor, "Eye Size");

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
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // eye distance from middle axis slider
            eyeDistlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
    
                    face.getEyes().setEyedist(eyeDistlider.getValue());
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // eye angle slider
            eyeAnglSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
    
                    face.getEyes().setAngle(eyeAnglSlider.getValue());
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // upper eyelid thiccness slider
            upEyelidSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
    
                    face.getEyes().setDistortion1(upEyelidSlider.getValue());
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // lower eyelid thiccness slider
            lowerEyelidSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
    
                    face.getEyes().setDistortion2(lowerEyelidSlider.getValue());
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // iris color chooser button
            irisColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Iris color", Color.DARK_GRAY);
                    face.setEyePupilColor(tmpColor);
                    irisColorButton.setBackground(tmpColor);
                    FaceFrame.grid.repaint();
                }
            });

            // eyeball color chooser button
            eyeballColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Eyeball color", Color.white);
                    face.setEyeballColor(tmpColor);
                    eyeballColorButton.setBackground(tmpColor);
                    FaceFrame.grid.repaint();
                }
            });

            // eyeshadow color chooser button
            eyeShadowColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null, "Eye shadow color", new Color(255, 215, 169, 255));
                    face.setMakeupEyeColor(tmpColor);
                    eyeShadowColorButton.setBackground(tmpColor);
                    FaceFrame.grid.repaint();
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
            for (int i=0;i<Emotion.values().length;i++){
                emotionlist[i]=Emotion.values()[i].toString();
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
                    
                    FaceFrame.grid.repaint();

                }
            });
            // random guy face
            RandMaleButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    face = RectComputer.createRandomFace("guy");
                    FaceFrame.grid.setFace(face);
                    FaceFrame.grid.repaint();

                }
            });
            // random gal face
            RandFemaleButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    face =RectComputer.createRandomFace("gal");
                    FaceFrame.grid.setFace(face);
                    FaceFrame.grid.repaint();

                }
            });
            // totally random face
            TotallyRandomButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    face =RectComputer.createRandomFace("default");
                    FaceFrame.grid.setFace(face);
                    FaceFrame.grid.repaint();
                }
            });
            // set emotion
            emotionList.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    Emotion emotions[]=Emotion.values();
                    Emotion chosenEmot=emotions[0];
                    String selection = (String)emotionList.getSelectedItem();
                    for (int i=0;i<emotions.length;i++){
                        if (emotions[i].toString().equals(selection)){
                            chosenEmot=emotions[i];
                            face.setExpression(chosenEmot);
                            break;
                        }
                    }
                    face.setExpression(chosenEmot);
                    FaceFrame.grid.repaint();
                }
            });
        }
    }

    class ExpressionPanel extends Subpanel {

        JSlider eyebrowSizeSlider, eyebrowThiccnessSlider;
        JLabel eyebrowLabel;

        ExpressionPanel(String title, Color backgroundColor, Color labelTextColor, Color sliderTextColor) {

            super(title, backgroundColor, labelTextColor, sliderTextColor);

        }

        @Override
        public void createElements() {
            eyebrowLabel = new JLabel("EyeBrows");
            UiMethods.setColortoLabels(labelTextColor, eyebrowLabel);

            eyebrowSizeSlider = UiMethods.createSlider("None", 0, "Unibrow", 25, 0, sliderTextColor, "Eyebrow Size");
            eyebrowThiccnessSlider = UiMethods.createSlider("None", 0, "Bushy", 4, 2, sliderTextColor,
                    "Eyebrow Thiccness");
        }

        @Override
        public void addElements() {
            add(titleLabel);
            add(eyebrowLabel);
            add(eyebrowSizeSlider);
            add(eyebrowThiccnessSlider);
        }

        @Override
        public void attachListeners() {

            // eyebrow size slider
            eyebrowSizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
    
                    face.getEyebrows().setEyebrowSize(eyebrowSizeSlider.getValue());
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });

            // eyebrow thiccness slider
            eyebrowThiccnessSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent event) {
    
                    face.getEyebrows().setEyebrowThiccness(eyebrowThiccnessSlider.getValue());
                    RectComputer.calcAllFeatures(face);
                    FaceFrame.grid.repaint();
                }
            });





        }
    }

}
