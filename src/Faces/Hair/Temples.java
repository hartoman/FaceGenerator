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
import  Faces.FunctionalClasses.SymmetricalFeature;

      // hair of the temple area -- exists in all hairstyles
      public class Temples extends SymmetricalFeature {

    private boolean hasHair;

    void drawTemples(Graphics2D g2d,Color hairColor) {

        Path2D.Double temple = new Path2D.Double();
        temple.moveTo(left * 10 / 10, bottom);
        temple.curveTo(left * 9 / 10, top + height * 2 / 3, left, top + height / 3, left + width / 2, top);
        temple.curveTo(left + width / 2, top + height / 10, left + width / 2, top + height / 10,
                left + width * 11 / 10, top + height / 10);
        temple.curveTo(left, bottom * 11 / 10, right, bottom * 11 / 10, left, bottom);
        temple.closePath();

        Shape temple2 = drawMirrored(temple);
        g2d.draw(temple);
        g2d.draw(temple2);
        g2d.setColor(hairColor);
        g2d.fill(temple);
        g2d.fill(temple2);
        g2d.setColor(Color.black);

    }

    public boolean getHasHair() {
        return hasHair;
    }

    public void setHasHair(boolean hasHair) {
        this.hasHair = hasHair;
    }


    
}