// Copyright © 2022 Christos Chartomatsidis

/*
 This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version. This program is distributed in the hope that it will be
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
    General Public License for more details. You should have received a copy of the GNU 
    General Public License along with this program. If not, see http://www.gnu.org/licenses/. 

 */

 package  Faces.Hair;
import java.awt.*;
import java.awt.geom.*;

import  Faces.FacialFeatures.Face;
import  Faces.FunctionalClasses.SymmetricalFeature; 


    // hair shape on the top of the head
   public abstract class TopOfHead extends SymmetricalFeature {

    protected Color hairColor, skinColor;
    protected int age;

        abstract void drawTopOfHead(Graphics2D g2d);

        public void setHaircutColors(Color hairColor,Color skinColor){
            this.hairColor=hairColor;
            this.skinColor=skinColor;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void drawForeheadWrinkes(Graphics2D g2d){

            g2d.setStroke(new BasicStroke(0.05f));
            
            for (int i=0;i<(age-1)/2;i++){
                Path2D.Double wrL = new Path2D.Double();
                wrL.moveTo(left + width *1 /12, bottom-height*i/10);
                wrL.curveTo(left + width *3/ 12, top+height*(5-i)/6, left + width *5/12, top+height*(5-i)/6, left+width/2, bottom-height*i/10);
                Shape wrR=drawMirrored(wrL);
                g2d.draw(wrL);
                g2d.draw(wrR);
            }

            g2d.setStroke(new BasicStroke());
            
        }


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
            int highWidth = Face.getMaxwidth() - 2 * left;
            g2d.setColor(hairColor);
            g2d.fillArc(left, top, highWidth, height * 13 / 8, 0, 180);
            g2d.setColor(Color.black);
        }
    }

    // pulp fiction low fringe
    class LowFringeTop extends TopOfHead {
        @Override
        void drawTopOfHead(Graphics2D g2d) {
            g2d.setColor(hairColor);
            
            g2d.fillArc(left, top, width, top + height * 5 / 3, 0, 180);
            g2d.setColor(Color.black);
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
            g2d.setColor(hairColor);

            int highWidth = Face.getMaxwidth() - 2 * left;
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

            g2d.setColor(hairColor);

            g2d.fillArc(left, top, width, top + height * 11 / 9, 0, 180);
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
            g2d.setColor(hairColor);
            g2d.fill(moh);
            g2d.setColor(Color.black);
        }

    }


    class HighTemples extends TopOfHead{

        @Override
        void drawTopOfHead(Graphics2D g2d) {
            
            g2d.setColor(hairColor);
            g2d.fillArc(left, top, width, top + height * 6 / 3, 0, 180);

             g2d.setColor(skinColor);
            g2d.fillArc(left, midy+height/8, width/2, midy+height/4, 0, 180);
            g2d.fillArc(left+width/2, midy+height/8, width/2, midy+height/4, 0, 180);

            g2d.setColor(Color.black);

        }
    }



    class Spikes extends TopOfHead{

        @Override
        void drawTopOfHead(Graphics2D g2d) {
            
            g2d.setColor(hairColor);
            g2d.fillArc(left, top, width, top + height * 6 / 3, 0, 180);

            // experimental

         //   g2d.setColor(Color.red);
            Point topOfspike = new Point(0,0);
            Point headPoint1 = new Point(100,100);
            Point headPoint2 = new Point(150,200);

            int innerPoints = 14;

            Point[] innerLeft = new Point[1+innerPoints/2];

            for (int i=0;i<innerLeft.length;i++){
                innerLeft[i]=new Point();
            }
            
            for (int i=0;i<innerLeft.length;i++){
                innerLeft[i].setLocation(left+width*i/innerPoints, bottom-height*(i)*1/(1*(innerPoints/2)));
            }

            // dras first row of spikes
            for (int i =0;i<innerLeft.length-1;i++){
                headPoint1.setLocation(innerLeft[i].x, innerLeft[i].y);
                headPoint2.setLocation(innerLeft[i+1].x, innerLeft[i+1].y);
                topOfspike.setLocation(headPoint1.x-width*4/(5*innerPoints),headPoint2.y-height*3/(innerPoints/2));
                Shape tri = new Polygon(new int[]{topOfspike.x,headPoint1.x,headPoint2.x},new int[]{topOfspike.y,headPoint1.y,headPoint2.y},3);
                Shape triR = drawMirrored(tri);
                g2d.draw(tri);
                g2d.draw(triR);
                g2d.fill(tri);
                g2d.fill(triR);
            }
            
            // draws the following rows
            for (int i =0;i<innerLeft.length-2;i++){
                headPoint1.setLocation(innerLeft[i].x, innerLeft[i].y);
                headPoint2.setLocation(innerLeft[i+2].x, innerLeft[i+2].y);
                topOfspike.setLocation(headPoint1.x-width*4/(5*innerPoints),headPoint2.y-height*3/(innerPoints/2));
                Shape tri = new Polygon(new int[]{topOfspike.x,headPoint1.x,headPoint2.x},new int[]{topOfspike.y,headPoint1.y,headPoint2.y},3);
                Shape triR = drawMirrored(tri);
                g2d.draw(tri);
                g2d.draw(triR);
                g2d.fill(tri);
                g2d.fill(triR);
            }

            g2d.setColor(skinColor);
            g2d.fillArc(left, midy+height/8, width/2, midy+height/4, 0, 180);
            g2d.fillArc(left+width/2, midy+height/8, width/2, midy+height/4, 0, 180);

            g2d.setColor(Color.black);

        }
    }