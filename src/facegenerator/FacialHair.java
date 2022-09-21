package facegenerator;
import java.awt.*;
import java.awt.geom.*;

public class FacialHair {

    MoustacheArea moustache;
    ChinArea chin;

    FacialHair(){

        moustache = new MoustacheArea();
        chin=new ChinArea();
    }


    void drawFacialHair(Graphics2D g2d){

            moustache.drawMoustache(g2d);
            chin.drawChinArea(g2d);

        
    }


    class MoustacheArea extends SymmetricalFeature {

        int moustacheSize; // [0,30]
        int curled; // [0,50]

        void drawMoustache(Graphics2D g2d) {

            if (moustacheSize>0) {

                if (curled < 0) {
                    moustacheSize = Math.max(3, moustacheSize);
                }

                int moustacheEnd = right - width * (1 + moustacheSize) / 10;
                int angleModifier;
                angleModifier = -height * curled / 10;
                Path2D.Double moust = new Path2D.Double();
                moust.moveTo(right, top);

                moust.curveTo(moustacheEnd + (moustacheSize * 9 / 10), top, moustacheEnd + moustacheSize * 1 / 10, top,
                        moustacheEnd, top + angleModifier * 2 / 3);
                moust.curveTo(moustacheEnd + (moustacheSize * 1 / 10), top + height * 2 / 3,
                        moustacheEnd + (moustacheSize * 9 / 10), top + height / 3, right, top + height / 2);

                Shape moust2 = drawMirrored(moust);
                g2d.draw(moust);
                g2d.draw(moust2);
                g2d.setColor(Face.hairColor);
                g2d.fill(moust);
                g2d.fill(moust2);
                g2d.setColor(Color.black);

            }

        }

        void setUpMoustache(int size,int curled){
            this.moustacheSize=size;
            this.curled=curled;
        }

    }

    class ChinArea extends SymmetricalFeature {

        int soulpatchHeight;          // [1,6]
        int soulpatchWidth;

        void drawChinArea(Graphics2D g2d){

            if ((soulpatchWidth>0)||(soulpatchHeight>0)){

                Path2D.Double sp = new Path2D.Double();
                sp.moveTo(right, midy);
                int soulLowest = midy+height*(1+soulpatchHeight)/6;
                int soulLeftest = right-width*soulpatchWidth/6;
                sp.curveTo(soulLeftest, midy, soulLeftest, soulLowest, right, soulLowest);
                sp.closePath();

                g2d.setColor(Face.hairColor);
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

}


