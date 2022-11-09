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

 package  Faces.FunctionalClasses;

import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

    // for everything on the head we draw the left one, and the right one is mirrored symmetrically
    
    public abstract class SymmetricalFeature implements Serializable{

        protected int left, width, height, right, top, bottom, midy;
        protected Rectangle boundRect = new Rectangle();
        protected Color colors[];

        // for every feature,draws its mirrored equivalent on the right
        public Shape drawMirrored(Shape leftFeature) {

            // set up for right feature
            Shape rightFeature = leftFeature;
            AffineTransform at = new AffineTransform();

            // move to new position around center Y axis
            int newMaxX = (int) (rightFeature.getBounds().getX());

            at.translate(AssistingMethods.symmetricHorizonal(newMaxX), 0);

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
    



        //getter
        public Rectangle getBoundRect(){
            return this.boundRect;
        }
        // setter
        public void setBoundRect(Rectangle rect){
            this.boundRect=rect;
        }
    

        


    }