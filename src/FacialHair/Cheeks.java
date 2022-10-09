package FacialHair;

import FunctionalClasses.*;
import java.awt.*;
import java.awt.geom.*;

public class Cheeks extends SymmetricalFeature {

    private int beardLength; // [0,5] -- at -0 nothing shows
    private int beardWidth; // [0,10]
    private int age;

    public void drawBeardArea(Graphics2D g2d, Color hairColor) {

        // adds wrinkes from age
        drawWrinkles(g2d);

       

        // adds beard if any
        if ((beardLength > 0)) {

            Path2D.Double br = new Path2D.Double();
            br.moveTo(left, top);
            int topRightX = right;
            int topRightY = top + height * 2 / 5;
            br.curveTo(left + width / 3, topRightY, left + width * 2 / 3, topRightY, topRightX, topRightY);
            int botY = top + height * 9 / 10 + height * (beardLength - 1) / 10;
            br.curveTo(left + width * 9 / 10, topRightY, left + width * 9 / 10, topRightY, right, botY);
            br.curveTo(left - width * beardWidth / 10, bottom, left - width * (beardWidth / 10), midy, left, top);
            br.closePath();

            Shape spr = drawMirrored(br);
            g2d.draw(br);
            g2d.draw(spr);
            g2d.setColor(hairColor);
            g2d.fill(br);
            g2d.fill(spr);
            g2d.setColor(Color.black);

        }


    }

    // draws all the facial wrinkles
    public void drawWrinkles(Graphics2D g2d){

        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(0.5f));

        // wrinkes around eyes
        for (int i = 0; i < Math.min((age+1)/2,4); i++) {

            Path2D.Double wr1 = new Path2D.Double();

            wr1.moveTo(left + width / 4, top - (height * 3/2 / 12)+(height*i/20));
            wr1.curveTo(left + 2*width / 6, top + height * (i ) / 20, left + width * 4 / 6, top + height * (3+i) / 20,
                    right+width/3, top+height*(-1/(Math.max(i,1))/20));
            g2d.draw(wr1);
            Shape wrr1 = drawMirrored(wr1);
            g2d.draw(wrr1);
        }

         // wrinkes of cheekbones
        for (int i = 0; i < (age-2)/2; i++) {

            Path2D.Double wr1 = new Path2D.Double();
            wr1.moveTo(left + width / 6, top + height * (i - 1) / 12);
            wr1.curveTo(left + width / 6, top + height * (i + 1) / 12, left + width * (i) / 3, top + height * (3+i) / 12,
                    left + width * (7 - i) / 6, top + height * (3 + i) / 12);
            g2d.draw(wr1);
            Shape wrr1 = drawMirrored(wr1);
            g2d.draw(wrr1);
        }

        // wrinkes around mouth
        for (int i = 0; i < Math.min(age/2,2); i++) {

            Path2D.Double wr2 = new Path2D.Double();
            wr2.moveTo(left + width * (21 - i) / 20, top + height * 8 / 10);
            wr2.curveTo(left + width * (4 - i) / 5, midy, left + width * (4 - i) / 3, midy,
                    left + width * (11 - i) / 10, top + height * 4 / 10);
            g2d.draw(wr2);
            Shape wrr2 = drawMirrored(wr2);
            g2d.draw(wrr2);
        }
        g2d.setStroke(new BasicStroke());
    }

    public int getBeardLength() {
        return beardLength;
    }

    public void setBeardLength(int beardLength) {
        this.beardLength = beardLength;
    }

    public int getBeardWidth() {
        return beardWidth;
    }

    public void setBeardWidth(int beardWidth) {
        this.beardWidth = beardWidth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
