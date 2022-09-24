package FrameFiles;

import javax.swing.*;

import FrameFiles.FaceFrame.Grid;

import java.awt.Dimension;
import java.awt.Color;

//sets up basic dimensions and layout
public class UiPanel extends JPanel {

    int w, h;

    public UiPanel(int w,int h){
        this.w=w;
        this.h=h;
    
        initPanel();

    }

    public void initPanel() {
        setSize(w, h);
        setPreferredSize(new Dimension(w, h));
            setMinimumSize(new Dimension(w, h));
            setMaximumSize(new Dimension(w, h));
        setLocation(w*2, 20);
        setBackground(Color.lightGray);

        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        GeneralHeadPanel gh = new GeneralHeadPanel(w,h);
        gh.setLocation(600,0);
        add(gh);
        add(new FeaturesPanel(w,h));
    }


//TODO ALL OF THESE
    class GeneralHeadPanel extends JPanel{
        int wg,hg;

        GeneralHeadPanel(int w,int h){
            this.wg=w/2;
            this.hg=h;

            setSize(wg, hg);
            setPreferredSize(new Dimension(wg , hg));
            setMaximumSize(new Dimension(wg, hg));
            setLocation(this.getSize().width, 20);
            setBackground(Color.green);
        }

        

    }

    class FeaturesPanel extends JPanel{
        int wf,hf;

        FeaturesPanel(int w,int h){
            this.wf=w/2;
            this.hf=h;

            setSize(wf, hf);
            setPreferredSize(new Dimension(wf , hf));
            setMaximumSize(new Dimension(wf, hf));
            setLocation(this.getSize().width, 20);
            setBackground(Color.red);
        }

    }

    class HairPanel extends JPanel{

    }

    class FacialHairPanel extends JPanel{

    }

    class ExpressionPanel extends JPanel{

    }


}