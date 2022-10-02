package FacialHair;
import FunctionalClasses.*;
import java.awt.*;
import java.awt.geom.*;

public class Cheeks extends SymmetricalFeature {
    
    private int beardLength;          // [0,5] -- at -1 does n
    private int beardWidth;




    public void drawBeardArea(Graphics2D g2d,Color hairColor){

     //   if ((beardLength>0)||(beardWidth>0)){

            Path2D.Double br = new Path2D.Double();

            br.moveTo(left, top);
            int topRightX = right;
            int topRightY = top+height*2/5;
            br.curveTo(left+width/3, topRightY, left+width*2/3, topRightY, topRightX, topRightY);
            int botY =top + height*9/10+height*(beardLength-1)/10;
            br.curveTo(left+width*9/10, topRightY, left+width*9/10, topRightY, right, botY);

         //   br.curveTo(left, midy, left, midy, left, top);
            br.curveTo(left-width*(beardWidth/10), bottom, left-width*(beardWidth/10), midy, left, top);
            br.closePath();

          g2d.setColor(hairColor);
        //    g2d.setColor(Color.red);
            g2d.draw(br);
            Shape spr = drawMirrored(br);
            g2d.draw(spr);
            g2d.fill(br);
             g2d.fill(spr);
            g2d.setColor(Color.black); /* */


      //  }
    }

    void setUpBeard(int soulpatchHeight, int soulpatchWidth){
        this.beardLength=beardLength;
        this.beardWidth=beardWidth;
    }









    public int beardLength() {
        return beardLength;
    }
    public void beardLength(int beardLength) {
        this.beardLength = beardLength;
    }
    public int beardWidth() {
        return beardWidth;
    }
    public void beardWidth(int beardWidth) {
        this.beardWidth = beardWidth;
    }





    
}
