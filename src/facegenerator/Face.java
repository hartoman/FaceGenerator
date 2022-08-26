/*
 * Made by Christos Chartomatsidis, 2022
 * This application is free to use, but it comes as-is:
 * I hold no responsibility for any damage or loss of that may arise from it's use.
 * Attribution is not required, but would be greatly appreciated.
 * For any comments, bug-reports, and ideas do not hesitate to contact me at:
 * hartoman@gmail.com
 */
package facegenerator;

import Toolz.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author chris
 */

public class Face {

    // gets ths symmentrical of x on the horizontal axis
    public int symmetricHorizonal(int x) {
        return maxwidth - x;
    }

    public void drawBezier(int bXL1, int bY1, int bXL2, int bY2, Graphics2D g2d) {
        // draws bezier lines for reference
        Path2D.Double bh1 = new Path2D.Double();
        bh1.moveTo(bXL1, bY1);
        bh1.lineTo(bXL2, bY2);
        g2d.draw(bh1);
    }

    // gets the approximate bounding box of a shape
    public Rectangle getBoundingBox(int leftMostX, int topMostY, int rightMostX, int bottomMostY) {
        return new Rectangle(leftMostX, topMostY, rightMostX - leftMostX, bottomMostY - topMostY);
    }

    // directly draws inserted rect
    public void toRect(Rectangle rect, Graphics2D g2d) {

        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();

        g2d.drawRect(x, y, width, height);

    }

    // divides the face to calculate bounding rectangles for facial features
    public void calcGuidingLines(int numLines, Graphics2D g2d) {

        int x1 = (int) halfFace.getX();
        int y1 = (int) halfFace.getY();
        int width1 = (int) halfFace.getWidth();
        int height1 = (int) halfFace.getHeight();

        // draws the horizontal lines
        for (int i = 0; i < numLines; i++) {
            // g2d.drawLine(x1,y1+height1*i/numLines, width1+x1,y1+ height1*i/numLines);
        }

        // draw vertical lines for the eyes
        // g2d.drawLine(x1+width1*2/6, y1, x1+width1*2/6, height1+y1);
        // g2d.drawLine(x1+width1*5/6, y1, x1+width1*5/6, height1+y1);

        eyes.setBoundRect(getBoundingBox(x1 + width1 * 2 / 6, (y1 + height1 * 3 / numLines),
                (x1 + width1 * 5 / 6),
                (y1 + height1 * 4 / numLines)));

    }





    int minHeight, height, maxwidth, midHeight, midWidth;
    Rectangle halfFace; // denominates the
    TextureHandler texture = new TextureHandler();
    final int thinness = 50; // larger number == thinner values range [0-50]
    Head head = new Head();
    Eyes eyes = new Eyes();

    public Face(int w, int h) {

        minHeight = 50;
        height = h - 50;
        midHeight = height / 2;
        maxwidth = w;
        midWidth = maxwidth / 2;

    }

    // puts everything together
    public void drawFace(Graphics2D g2d) {

        head.calcHead();
        

        toRect(halfFace, g2d);
        calcGuidingLines(8, g2d);

        eyes.drawEyes(g2d);

        head.drawHead(g2d);
    }

    class Head{

        int bXL1, bXR1, bY1; // bezier handle 1 (ear-eye axis) -- x for L, R and Y is the same
        int bXL2, bXR2, bY2; // bezier handle 2 (nose-mouth axis) -- x for L, R and Y is the same

     // calculates head size
     public void calcHead(){
        
        int mod1 = new Randomizer().randomBetween(0, thinness);
        // sum of numenators of (bXL1 + bXL2) must be in [0,5]
        bXL1 = midWidth * mod1 / 120;

        // bY1 numenator deviation must always be equal or lesser than 10% of height
        int mod2 = new Randomizer().randomBetween(0, 10);
        bY1 = (minHeight + ((height * mod2) / 120));

        // sum of numenators of (bXL1 + bXL2) must be in [0,5]
        int mod3 = thinness - mod1;
        bXL2 = midWidth * mod3 / 120;

        // bY2 numenator must always be greater than 90% of height
        int mod4 = new Randomizer().randomBetween(110, 120);
        bY2 = (height - (minHeight * mod4) / 120);

        //calculates the other half of the head
        bXR1 = symmetricHorizonal(bXL1);
        bXR2 = symmetricHorizonal(bXL2);

        // sets the rectangle boundary of the left half of the face
        halfFace = getBoundingBox(Math.max(bXL1, bXL2), minHeight, midWidth, height);

    }


     // draws the overall head shape
     public void drawHead(Graphics2D g2d) {

        Path2D.Double head = new Path2D.Double();

        // draws face:
        head.moveTo(midWidth, minHeight);
        // left side
        head.curveTo(bXL1, bY1,
                bXL2, bY2,
                midWidth, height);

        // right side
        head.curveTo(bXR2, bY2,
                bXR1, bY1,
                midWidth, minHeight);

        // finish off head
        head.closePath();

        g2d.draw(head);
    
        // g2d.setColor(Color.pink);
        // g2d.fill(head);
    
        }



    }

    class Eyes {

        Rectangle boundRect = new Rectangle();

        public void setBoundRect(Rectangle rect) {
            this.boundRect = rect;
        }

        public void drawEyes(Graphics2D g2d) {

            toRect(boundRect, g2d);

          //  we get what we need from the rect
            int left=boundRect.x;
            int width = boundRect.width;
            int height = boundRect.height;
            int right=width+boundRect.x;
            int top=boundRect.y;
            int bottom=boundRect.height+boundRect.y;
            int midy =(top+bottom)/2;
            
            // values range [-10,10], the closer to 0, the more average the eye
            // for upper eyeball limit
            int distortion1 = 0;
            // for lower eyeball limit
            int distortion2 = 0;
            


            // draw eyeline shape
            Path2D.Double eye = new Path2D.Double();
            eye.moveTo(left, midy);
            eye.curveTo(left+width/3,top+distortion1,left+width*2/3,top+distortion1,right, midy);
            eye.curveTo(left+width*2/3,bottom+distortion2,left+width/3 ,bottom+distortion2,left, midy);
            eye.closePath();
            g2d.draw(eye);

            // paint eyeball -- TODO: add various tones of red
       //     g2d.setColor(Color.white);
       //     g2d.fill(eye);
       //     g2d.setColor(Color.black);

            //create iris
            Ellipse2D.Double iris = new Ellipse2D.Double(left+width/3,top,width/3,height);
            g2d.draw(iris);
       //     g2d.setColor(Color.white);
       //     g2d.fill(eye);
       //     g2d.setColor(Color.black);

            // draws the pupis
            Ellipse2D.Double pupil = new Ellipse2D.Double(left+width*4/9,top+height/2,width/9,width/9);
            g2d.draw(pupil);
            g2d.fill(pupil);


            // draw eyelid  
            // values range [0,75], 0=closed, 75=wide open
            int distortion3 = 50;

            Path2D.Double eyelidUP = new Path2D.Double();
            eyelidUP.moveTo(left,midy);
            eyelidUP.curveTo(left+width/3,top+distortion1,left+width*2/3,top+distortion1,right, midy);
            eyelidUP.curveTo(left+width*2/3,bottom-distortion3,left+width/3 ,bottom-distortion3,left,midy);
            eyelidUP.closePath();
            g2d.draw(eyelidUP);
            //can add eyeshadow
         //   g2d.fill(eyelidUP);

            Path2D.Double eyelidBOT = new Path2D.Double();
            eyelidBOT.moveTo(right,midy);
            eyelidBOT.curveTo(left+width*2/3,bottom+distortion2,left+width/3 ,bottom+distortion2,left, midy);
            eyelidBOT.curveTo(left+width/3,bottom+distortion3/5,left+width*2/3,bottom+distortion3/5,right, midy);
            eyelidBOT.closePath();
            g2d.draw(eyelidBOT);
             //can add eyeshadow
        //    g2d.fill(eyelidBOT);

        }

    }



    class eyebrows {

        public eyebrows() {

        }
    }

    class nose {

        public nose() {

        }
    }

    class TextureHandler {
        String skinTexture;
        String pupilTexture;
        String hairTexture;

        // set the color schemes
        public void setTextures(String skinTexture, String pupilTexture, String hairTexture) {
            this.skinTexture = skinTexture;
            this.pupilTexture = pupilTexture;
            this.hairTexture = hairTexture;
        }
    }

}
