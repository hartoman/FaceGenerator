package FacialHair;
import FunctionalClasses.*;
import java.awt.*;
import java.awt.geom.*;


public class ChinArea extends SymmetricalFeature {

    private int soulpatchHeight;          // [1,6]
    private int soulpatchWidth;


    void drawChinArea(Graphics2D g2d,Color hairColor){

        if ((soulpatchWidth>0)||(soulpatchHeight>0)){

            Path2D.Double sp = new Path2D.Double();
            sp.moveTo(right, midy);
            int soulLowest = midy+height*(1+soulpatchHeight)/6;
            int soulLeftest = right-width*soulpatchWidth/6-10;
            sp.curveTo(soulLeftest, midy, soulLeftest, soulLowest, right, soulLowest);
            sp.closePath();

            Shape spr = drawMirrored(sp);
            g2d.draw(sp);
            g2d.draw(spr);
            g2d.setColor(hairColor);
            g2d.fill(sp);
            g2d.fill(spr);
            g2d.setColor(Color.black);
        }
    }

    void setUpChin(int soulpatchHeight, int soulpatchWidth){
        this.soulpatchHeight=soulpatchHeight;
        this.soulpatchWidth=soulpatchWidth;
    }



    public int getSoulpatchHeight() {
        return soulpatchHeight;
    }

    public void setSoulpatchHeight(int soulpatchHeight) {
        this.soulpatchHeight = soulpatchHeight;
    }

    public int getSoulpatchWidth() {
        return soulpatchWidth;
    }

    public void setSoulpatchWidth(int soulpatchWidth) {
        this.soulpatchWidth = soulpatchWidth;
    }
}

