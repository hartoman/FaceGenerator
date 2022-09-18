package facegenerator;
import java.awt.*;
import java.awt.geom.*; 


    // hair shape on the top of the head
   public  abstract class TopOfHead extends SymmetricalFeature {
        Shape topHair;
        abstract void drawTopOfHead(Graphics2D g2d);

    }


    /* various top of head parts */

    // special case where the top of the head is bald
    class BaldTop extends TopOfHead {

        @Override
        void drawTopOfHead(Graphics2D g2d) {
            // nothing to draw if the head is bald
        }
    }

    // high fringe
    class HighFringeTop extends TopOfHead {
        @Override
        void drawTopOfHead(Graphics2D g2d) {

            // high fringe
            int highWidth = Face.maxwidth - 2 * left;
            g2d.fillArc(left, top, highWidth, height * 13 / 8, 0, 180);
        }
    }

    // pulp fiction low fringe
    class LowFringeTop extends TopOfHead {
        @Override
        void drawTopOfHead(Graphics2D g2d) {

            g2d.fillArc(left, top, width, top + height * 5 / 3, 0, 180);

        }
    }

    // hair parts on the side
    class PartSideTop extends TopOfHead {
        @Override
        void drawTopOfHead(Graphics2D g2d) {
            Path2D.Double part = new Path2D.Double();
            part.moveTo(left + width * 4 / 5, midy + height / 4);
            part.curveTo(left + width / 5, bottom, left + width * 2 / 5, bottom, left, height * 13 / 8);
            part.lineTo(left, midy + height / 5);
            part.closePath();
            g2d.setColor(Face.hairColor);

            int highWidth = Face.maxwidth - 2 * left;
            g2d.fillArc(left, top, highWidth, height * 13 / 8, 0, 180);
            g2d.fill(part);

            g2d.setColor(Color.black);
        }
    }

    // hair parts in the middle
    class PartMiddleTop extends TopOfHead {

        @Override
        void drawTopOfHead(Graphics2D g2d) {

            Path2D.Double part = new Path2D.Double();
            part.moveTo(left, bottom);
            part.curveTo(left + width / 5, bottom, left + width * 2 / 5, bottom, left + width / 2, midy + height / 5);
            part.lineTo(left, midy + height / 5);
            part.closePath();

            Shape part2 = drawMirrored(part);
            g2d.draw(part);
            g2d.draw(part2);

            g2d.setColor(Face.hairColor);

            g2d.fillArc(left, top, width, top + height * 10 / 9, 0, 180);
            g2d.fill(part);
            g2d.fill(part2);

            g2d.setColor(Color.black);

        }
    }

    // fixed-size mohawk in the middle
    class MohawkTop extends TopOfHead {
        @Override
        void drawTopOfHead(Graphics2D g2d) {
            Path2D.Double moh = new Path2D.Double();
            moh.moveTo(left + width * 2 / 5, midy);
            moh.lineTo(left + width * 3 / 5, midy);
            moh.lineTo(left + width * 7 / 10, 10);
            moh.lineTo(left + width * 3 / 10, 10);
            moh.closePath();

            g2d.draw(moh);
            g2d.setColor(Face.hairColor);
            g2d.fill(moh);
            g2d.setColor(Color.black);
        }

    }
