package facegenerator;
import java.awt.*;
import java.awt.geom.*;

    // for everything on the head we draw the left one, and the right one is mirrored symmetrically
    
    public abstract class SymmetricalFeature {

        protected int left, width, height, right, top, bottom, midy;
        protected Rectangle boundRect = new Rectangle();

        // for every feature,draws its mirrored equivalent on the right
        public Shape drawMirrored(Shape leftFeature) {

            // set up for right feature
            Shape rightFeature = leftFeature;
            AffineTransform at = new AffineTransform();

            // move to new position around center Y axis
            int newMaxX = (int) (rightFeature.getBounds().getX());

            at.translate(RectComputer.symmetricHorizonal(newMaxX), 0);

            // mirror it (around axis x=0)
            at.scale(-1, 1);

            // return it to its original (new) position
            at.translate(-newMaxX, 0);

            return at.createTransformedShape(rightFeature);
        }

        // convenience method to have on hand the necessary numbers for drawing
        public void setBoundingBoxParameters() {
            left = boundRect.x; // leftmost x of box
            width = boundRect.width; // box width
            height = boundRect.height; // box height
            right = width + boundRect.x; // rightmost x
            top = boundRect.y; // topmost y
            bottom = boundRect.height + boundRect.y; // bottomost y
            midy = (top + bottom) / 2; // middle of height
        }
    
        public Rectangle getBoundRect(){
            return this.boundRect;
        }
    
        public void setBoundRect(Rectangle rect){
            this.boundRect=rect;
        }
    
    }