package FacialHair;
import FunctionalClasses.*;
import java.awt.*;
import java.awt.geom.*;
import FacialFeatures.Face;

public class ChinArea extends SymmetricalFeature {

    private int soulpatchHeight;          // [1,6]
    private int soulpatchWidth;

    public int soulpatchHeight() {
        return soulpatchHeight;
    }

    public void soulpatchHeight(int soulpatchHeight) {
        this.soulpatchHeight = soulpatchHeight;
    }

    public int soulpatchWidth() {
        return soulpatchWidth;
    }

    public void soulpatchWidth(int soulpatchWidth) {
        this.soulpatchWidth = soulpatchWidth;
    }

    void drawChinArea(Graphics2D g2d){

        if ((soulpatchWidth>0)||(soulpatchHeight>0)){

            Path2D.Double sp = new Path2D.Double();
            sp.moveTo(right, midy);
            int soulLowest = midy+height*(1+soulpatchHeight)/6;
            int soulLeftest = right-width*soulpatchWidth/6;
            sp.curveTo(soulLeftest, midy, soulLeftest, soulLowest, right, soulLowest);
            sp.closePath();

            g2d.setColor(Face.hairColor());
            g2d.draw(sp);
            Shape spr = drawMirrored(sp);
            g2d.draw(spr);
            g2d.fill(sp);
            g2d.fill(spr);
            g2d.setColor(Color.black);
        }
    }

    void setUpChin(int soulpatchHeight, int soulpatchWidth){
        this.soulpatchHeight=soulpatchHeight;
        this.soulpatchWidth=soulpatchWidth;
    }
}
