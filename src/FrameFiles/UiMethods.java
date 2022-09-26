package FrameFiles;

import javax.swing.*;
import java.awt.Color;
import java.util.Hashtable;

public class UiMethods {

    public static JSlider createSlider(String atMin, int minValue, String atMax, int maxValue,int defaultValue,
    Color fontcolor,String tooltip) {

        JSlider slider = new JSlider(JSlider.HORIZONTAL, minValue, maxValue, defaultValue);
        setSliderLabels(slider, atMin, minValue, atMax, maxValue, fontcolor);
        slider.setToolTipText(tooltip);

        return slider;
    }

    public static void setSliderLabels(JSlider slider, String atMin, int minValue, String atMax, int maxValue,
            Color fontcolor) {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();

        JLabel minLabel = new JLabel(atMin);
        JLabel maxLabel = new JLabel(atMax);
        setColortoLabels(fontcolor, minLabel, maxLabel);
        labelTable.put(minValue, minLabel);
        labelTable.put(maxValue, maxLabel);

        slider.setPaintLabels(true);
        slider.setOpaque(false);
        slider.setLabelTable(labelTable);

    }

    public static void setColortoLabels(Color fontcolor, JLabel... jLabels) {
        for (int i = 0; i < jLabels.length; i++) {
            jLabels[i].setForeground(fontcolor);
        }

    }

    public static Color assignColor(){
        return JColorChooser.showDialog(null,"pick color",Color.white);
        
    }
}
