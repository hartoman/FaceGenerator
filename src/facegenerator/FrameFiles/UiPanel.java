package facegenerator.FrameFiles;

import javax.swing.*;
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
        setSize(w - 200, h);
        setPreferredSize(new Dimension(w / 2, h));
        setMaximumSize(new Dimension(w, h));
        setLocation(this.getSize().width - w, 20);
        setBackground(Color.lightGray);

    }


//TODO ALL OF THESE
    class GeneralHeadPanel extends JPanel{

    }

    class FeaturePanel extends JPanel{

    }

    class HairPanel extends JPanel{

    }

    class FacialHairPanel extends JPanel{

    }

    class ExpressionPanel extends JPanel{

    }


}