package FrameFiles;

import javax.swing.*;

import FacialFeatures.Face;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.util.Hashtable;

//sets up basic dimensions and layout
public class UiPanel extends JPanel {

    int w, h;

    public UiPanel(int w, int h) {
        this.w = w;
        this.h = h;

        initPanel();

    }

    public void initPanel() {
        setSize(w, h);

        setMaximumSize(new Dimension(w, h));
        setBackground(Color.lightGray);

        this.setLayout(new GridLayout(2, 3));

        add(new GeneralHeadPanel());
        add(new FeaturesPanel());
        add(new HairPanel());
        add(new EyesPanel());
        add(new ExpressionPanel());
        add(new FacialHairPanel());

    }


    class GeneralHeadPanel extends JPanel {
        //
        GeneralHeadPanel() {

            setBackground(Color.green);
            Color letterColor = Color.BLACK;
            setLayout(new GridLayout(10, 1));

            JButton skinColorButton = new JButton("Skin Color");
            skinColorButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Color tmpColor = JColorChooser.showDialog(null,"Skin color",new Color(255,215,169,255));
                    Face.skinColor(tmpColor);
                    skinColorButton.setBackground(tmpColor);
                    FaceFrame.grid.removeAll();
                    FaceFrame.grid.repaint();
                }
            });

            JSlider thiccSlider = new JSlider(JSlider.HORIZONTAL, 0, 40, 0);
            UiMethods.setSliderLabels(thiccSlider, "Slick", 0, "Thicc", 40, letterColor);
            thiccSlider.setToolTipText("Determines thiccness of head");

            JSlider headShapeSlider = new JSlider(JSlider.HORIZONTAL, 0, 40, 0);
            UiMethods.setSliderLabels(headShapeSlider, "Conical", 0, "Potato", 40, letterColor);
            headShapeSlider.setToolTipText("Determines shape of head");

            // create string[] with values of enum
            String emotions[] = { "ena", "dyo" };
            // set values of enum to return on selection
            JComboBox emotionList = new JComboBox(emotions);

            add(new JLabel("General"));
            add(skinColorButton);
            add(thiccSlider);
            add(headShapeSlider);
            add(new JLabel("Expression"));
            add(emotionList);
        }

    }

    class FeaturesPanel extends JPanel {

        FeaturesPanel() {
            setBackground(Color.red);
            Color letterColor = Color.BLACK;
            setLayout(new GridLayout(10, 1));

            JSlider noseSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
            UiMethods.setSliderLabels(noseSizeSlider, "French", 1, "Manatee", 8, letterColor);
            noseSizeSlider.setToolTipText("Size of nose");

            JSlider earSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);
            UiMethods.setSliderLabels(earSizeSlider, "Delicate", 0, "Radar Dish", 50, letterColor);
            earSizeSlider.setToolTipText("Size of ears");

            JSlider mouthSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 40, 0);
            UiMethods.setSliderLabels(mouthSizeSlider, "Diet", 0, "Maw", 40, letterColor);
            mouthSizeSlider.setToolTipText("Size of mouth");

            JSlider lipsSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, 0);
            UiMethods.setSliderLabels(lipsSizeSlider, "None", 0, "Silicone", 30, letterColor);
            lipsSizeSlider.setToolTipText("Size of lips");

            JButton lipsColorButton = new JButton("Lips Color");

            add(new JLabel("Features"));
            add(new JLabel("Nose"));
            add(noseSizeSlider);
            add(new JLabel("Ears"));
            add(earSizeSlider);
            add(new JLabel("Mouth"));
            add(mouthSizeSlider);
            add(new JLabel("Lips"));
            add(lipsSizeSlider);
            add(lipsColorButton);
        }

    }

    class HairPanel extends JPanel {

        HairPanel() {
            setBackground(Color.blue);
            Color letterColor = Color.WHITE;

            JLabel titleLabel = new JLabel("Hair");
            JLabel facialHairLabel = new JLabel("Facial Hair");
            JLabel moustacheLabel = new JLabel("Moustache");
            JLabel chinHairLabel = new JLabel("Chin");

            UiMethods.setColortoLabels(letterColor, titleLabel, facialHairLabel, moustacheLabel, chinHairLabel);

            setLayout(new GridLayout(10, 1));

            // create string[] with values of enum
            String hairstyle[] = { "ena", "dyo" };
            // set values of enum to return on selection
            JComboBox hairStyleList = new JComboBox(hairstyle);
            JButton hairColorButton = new JButton("Hair Color");

            // TODO COMPRESS ALL
            JSlider moustacheSizeSlider = UiMethods.createSlider("None", 0, "The General", 20, 0, letterColor);// new
                                                                                                               // JSlider(JSlider.HORIZONTAL,
                                                                                                               // 0, 20,
                                                                                                               // 0);
            // UiMethods.setSliderLabels(moustacheSizeSlider, "None", 0, "The General", 20,
            // letterColor);
            moustacheSizeSlider.setToolTipText("Moustache Size");

            JSlider moustacheCurveSlider = new JSlider(JSlider.HORIZONTAL, -40, 40, 0);
            UiMethods.setSliderLabels(moustacheCurveSlider, "Hogan", -40, "Dali", 40, letterColor);
            moustacheCurveSlider.setToolTipText("Moustache Curve");

            JSlider chinHairLengthSlider = new JSlider(JSlider.HORIZONTAL, 0, 6, 0);
            UiMethods.setSliderLabels(chinHairLengthSlider, "None", 0, "Max", 6, letterColor);
            chinHairLengthSlider.setToolTipText("Chin Hair Length");

            JSlider chinHairWidthSlider = new JSlider(JSlider.HORIZONTAL, 0, 6, 0);
            UiMethods.setSliderLabels(chinHairWidthSlider, "None", 0, "Max", 6, letterColor);
            chinHairWidthSlider.setToolTipText("Chin Hair Width");

            add(titleLabel);
            add(hairStyleList);
            add(hairColorButton);
            add(facialHairLabel);
            add(moustacheLabel);
            add(moustacheSizeSlider);
            add(moustacheCurveSlider);
            add(chinHairLabel);
            add(chinHairLengthSlider);
            add(chinHairWidthSlider);
        }
    }

    class EyesPanel extends JPanel {

        EyesPanel() {

            setBackground(Color.cyan);
            Color letterColor = Color.BLACK;

            setLayout(new GridLayout(10, 1));

            JButton irisColorButton = new JButton("Iris Color");
            JButton eyeballColorButton = new JButton("Eyeball Color");
            JButton eyeShadowColorButton = new JButton("Eye Shadow Color");

            JSlider eyeAnglSlider = new JSlider(JSlider.HORIZONTAL, -25, 25, 0);
            UiMethods.setSliderLabels(eyeAnglSlider, "Crooked", -25, "Asian", 25, letterColor);
            eyeAnglSlider.setToolTipText("Eye Angle");

            JSlider upEyelidSlider = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
            UiMethods.setSliderLabels(upEyelidSlider, "Insomnia", -10, "Rested", 10, letterColor);
            upEyelidSlider.setToolTipText("Bagginess of upper eyelid");

            JSlider lowerEyelidSlider = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
            UiMethods.setSliderLabels(lowerEyelidSlider, "Insomnia", -10, "Rested", 10, letterColor);
            lowerEyelidSlider.setToolTipText("Bagginess of lower eyelid");

            JSlider eyebrowSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 25, 0);
            UiMethods.setSliderLabels(eyebrowSizeSlider, "None", 0, "Bogdan", 25, letterColor);
            eyebrowSizeSlider.setToolTipText("Eyebrow Size");

            JSlider eyebrowThiccnessSlider = new JSlider(JSlider.HORIZONTAL, 0, 4, 0);
            UiMethods.setSliderLabels(eyebrowThiccnessSlider, "None", 0, "Bogdan", 4, letterColor);
            eyebrowThiccnessSlider.setToolTipText("Eyebrow Thiccness");

            add(new JLabel("Eyes"));
            add(irisColorButton);
            add(eyeballColorButton);
            add(eyeShadowColorButton);
            add(eyeAnglSlider);
            add(upEyelidSlider);
            add(lowerEyelidSlider);
            add(new JLabel("EyeBrows"));
            add(eyebrowSizeSlider);
            add(eyebrowThiccnessSlider);
        }

    }

    class FacialHairPanel extends JPanel {

        FacialHairPanel() {

            setBackground(Color.MAGENTA);
            setLayout(new GridLayout(10, 1));

        }

    }

    class ExpressionPanel extends JPanel {
        ExpressionPanel() {

            setBackground(Color.BLACK);
            JLabel title = new JLabel("Expression");
            title.setForeground(Color.WHITE);
            add(title);
        }

    }


}