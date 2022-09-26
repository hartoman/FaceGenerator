package FacialFeatures;
import FunctionalClasses.*;
import java.awt.*;
import java.awt.geom.*;


// defines the main features-outline of the head.
// The rest of the features are drawn on basis of these
public class Head{

   private int thiccness; // larger number == fatter, values range [0-50]
   private int mod1;// ,mod2,mod4,mod3;

   private int bXL1, bXR1, bY1; // bezier handle 1 (ear-eye axis) -- x for L, R and Y is the same
   private int bXL2, bXR2, bY2; // bezier handle 2 (nose-mouth axis) -- x for L, R and Y is the same

    // calculates head size
    public void calcHead() {

        int mod2 = 5; // [0,10]
        int mod4 = 115; // [110,120]
    //    int mod3 = 50 - (thiccness + mod1) / 2;
        int mod3 = 50 - (thiccness + mod1);// / 2;

        bXL1 = Face.midWidth() * mod1 / 120;
        bY1 = (Face.minHeight() + ((Face.height() * mod2) / 120));

        bXL2 = Face.midWidth() * mod3 / 120;
        bY2 = (Face.height() - (Face.minHeight() * mod4) / 120);

        // calculates the other half of the head
        bXR1 = RectComputer.symmetricHorizonal(bXL1);
        bXR2 = RectComputer.symmetricHorizonal(bXL2);

        // sets the rectangle boundary of the left half of the face
        Face.halfFace( RectComputer.getBoundingBox(Math.max(bXL1, bXL2), Face.minHeight(), Face.midWidth(), Face.height()));

    }

    // draws the overall head shape
    public void drawHead(Graphics2D g2d) {

        Path2D.Double head = new Path2D.Double();

        // draws face:
        head.moveTo(Face.midWidth(), Face.minHeight());
        // left side
        head.curveTo(bXL1, bY1,
                bXL2, bY2,
                Face.midWidth(), Face.height());

        // right side
        head.curveTo(bXR2, bY2,
                bXR1, bY1,
                Face.midWidth(), Face.minHeight());

        // finish off head
        head.closePath();

        g2d.draw(head);
        g2d.setColor(Face.skinColor());
        g2d.fill(head); // with skin color

    }

    public void setHead(int thiccness,int mod1){
        this.thiccness=thiccness;
        this.mod1=mod1;
    }

    //getters
    public int bXL1() {
        return bXL1;
    }

    public int bY1() {
        return bY1;
    }

    public int bXL2() {
        return bXL2;
    }

    public int bY2() {
        return bY2;
    }



    //more getters

    public int thiccness() {
        return thiccness;
    }

    public int mod1() {
        return mod1;
    }
    
    //setters

    public void thiccness(int thiccness) {
        this.thiccness = thiccness;
    }

    public void mod1(int mod1) {
        this.mod1 = mod1;
    }
    
}