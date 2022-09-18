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

        boolean hasHair;
        int moustacheSize; // [0,30]
        int curled; // [0,50]

        void drawMoustache(Graphics2D g2d) {

            if (hasHair) {

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

        boolean hasHair;
        int beardSize;

        void drawChinArea(Graphics2D g2d){
            if (hasHair){

            }
        }

        void setUpChin(int beardSize){
            this.beardSize=beardSize;
        }
    }

}


