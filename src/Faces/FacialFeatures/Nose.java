package FacialFeatures;
import java.awt.*;
import java.awt.geom.*;
import FunctionalClasses.SymmetricalFeature;

public class Nose extends SymmetricalFeature {

    private int noseSize;

    

    void drawNose(Graphics2D g2d,Color skinColor) {

        int bridgeBeginy = top;
        int bh1y = top + height * 1 / 5;
        int bh2y = top + height * 3 / 5;
        int bridgeEndy = top + height * 4 / 5;
        int bh3y = top + height * 8 / 9;
        int bh4y = top + height * 18 / 19;// bh3y+20;
        int bh5y = top + height * 11 / 10;

        int pos2 = 9 - noseSize;
        int bridgeBeginx = left + width * noseSize / 9;
        int bh1x = right;
        int bh2x = left + width / 2;
        int bridgeEndx = left + width * pos2 / 9;
        int bh3x = bridgeEndx - width * pos2 / 2 / 9;
        int bh4x = left + width * 4 / 5;
        int bh5x = left + width * 8 / 9;

        Path2D.Double nose = new Path2D.Double();
        nose.moveTo(bridgeBeginx, bridgeBeginy);

        nose.curveTo(bh1x, bh1y, bh2x,
                bh2y,
                bridgeEndx, bridgeEndy);

        nose.curveTo(bh3x, bh3y, bh4x,
                bh4y,
                bh4x, bh4y);

        nose.curveTo(bh5x, bh5y, right, bh5y, left + width,
                bh5y);

        // so that it gets drawn OVER the moustache
        g2d.draw(nose);
        g2d.setColor(skinColor);
        nose.lineTo(right, top);
        nose.closePath();
        g2d.setColor(Color.black);

        Shape nose2 = drawMirrored(nose);
        g2d.draw(nose2);

        g2d.setColor(skinColor);
        g2d.fill(nose);
        g2d.fill(nose2);
        g2d.setColor(Color.BLACK);
    }

public int getNoseSize() {
        return noseSize;
}

public void setNoseSize(int noseSize) {
        this.noseSize = noseSize;
}


    
}
