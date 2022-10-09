package FunctionalClasses;
import java.awt.*;
import java.awt.geom.*;
import FacialFeatures.*;

   /* convenience methods for drawing the framing lines */
    public class DrawingMethods {

        // draws the assisting guiding lines
        public static void drawGuidingLines(int numLines, Graphics2D g2d,Face face) {

            Rectangle halfFace=face.getHalfFace();

            int x1 = (int) halfFace.getX();
            int y1 = (int) halfFace.getY();
            int width1 = (int) halfFace.getWidth();
            int height1 = (int) halfFace.getHeight();

            // blue color for assisting lines
            g2d.setColor(Color.blue);

            // draws the horizontal lines
           
              for (int i = 0; i < numLines; i++) {
              g2d.drawLine(x1, y1 + height1 * i / numLines, width1 + x1, y1 + height1 * i /
              numLines);
              }
              
              // draw vertical lines for the eyes
              g2d.drawLine(x1 + width1 * 2 / 6, y1, x1 + width1 * 2 / 6, height1 + y1);
              g2d.drawLine(x1 + width1 * 5 / 6, y1, x1 + width1 * 5 / 6, height1 + y1);
              
            /*  */

            // show bezier handles (endpoints of the line)
             Path2D.Double bez = new Path2D.Double();
             bez.moveTo(face.getHead().getBXL1(), face.getHead().getBY1());
             bez.lineTo(face.getHead().getBXL2(), face.getHead().getBY2());
             g2d.draw(bez);

            // gray color for bounding rectangles
            g2d.setColor(Color.gray);

             drawRect(halfFace, g2d);
             drawRect(face.getNose().getBoundRect(), g2d);
             drawRect(face.getEyebrows().getBoundRect(), g2d);

             drawRect(face.getHaircut().getTopOfHead().getBoundRect(), g2d);
             drawRect(face.getHaircut().getTemples().getBoundRect(), g2d);
             drawRect(face.getMouth().getBoundRect(), g2d);
             drawRect(face.getEars().getBoundRect(), g2d);

            // drawRect(facialHair.moustache.getBoundRect, g2d);
             drawRect(face.getFacialHair().getChinArea().getBoundRect(), g2d);

            // line starting from middle of face and ending on outline at the given height
        //     drawBezHeight(Face.getHeight()/2,g2d,Face.getMaxwidth(),Face.getMidWidth(),face.getHead().getBezhandles());

            drawRect(face.getFacialHair().getCheeks().getBoundRect(), g2d);

            // back to default
            g2d.setColor(Color.black);
        }

        // directly draws inserted rect
        public static void drawRect(Rectangle rect, Graphics2D g2d) {

            int x = (int) rect.getX();
            int y = (int) rect.getY();
            int width = (int) rect.getWidth();
            int height = (int) rect.getHeight();

            g2d.drawRect(x, y, width, height);
        }

        // draws a single horizontal line from middle of screen up to bezier curve of
        // face
        public static void drawBezHeight(int yPos1, Graphics2D g2d, int maxwidth,int midWidth,int[] bezhandles) {

            Path2D.Double bezheight1 = new Path2D.Double();

            Point bezPoint1 = RectComputer.headPointOnYpos(yPos1, bezhandles);

            bezheight1.moveTo(maxwidth - midWidth, yPos1);
            bezheight1.lineTo(bezPoint1.x, yPos1);
            g2d.setColor(Color.red);
            g2d.draw(bezheight1);
            g2d.setColor(Color.black);
        }
    }