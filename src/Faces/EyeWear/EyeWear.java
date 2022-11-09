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

 package  Faces.EyeWear;

import java.awt.*;
import java.awt.geom.*;

import Faces.FunctionalClasses.AssistingMethods;
import  Faces.FunctionalClasses.SymmetricalFeature;

public abstract class EyeWear extends SymmetricalFeature {

    public abstract void drawEyeWear(Graphics2D g2d);

}

class NoEyeWear extends EyeWear {

    @Override
    public void drawEyeWear(Graphics2D g2d) {
        // nothing to show, there isn't any eyewear
    };
}

class Monocle extends EyeWear {

    @Override
    public void drawEyeWear(Graphics2D g2d) {

        // prepare dashed stroke for chain and bold stroke for frame
        float[] dashingPattern1 = { 2f, 2f }; // stroke intervals
        Stroke dashedStroke = new BasicStroke(2f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
        Stroke boldStroke = new BasicStroke(4f, 1, 1);

        // fill frame
        g2d.setColor(new Color(128, 128, 128, 64));
        g2d.fillOval(left + width * 1 / 8, top, height * 2 / 3, height * 2 / 3);

        // draw frame
        g2d.setStroke(boldStroke);
        g2d.setColor(Color.black);
        g2d.drawOval(left + width * 1 / 8, top, height * 2 / 3, height * 2 / 3);

        // draw chain
        g2d.setStroke(dashedStroke);
        Path2D.Double chain = new Path2D.Double();
        chain.moveTo(left + width * 1 / 8, top + height / 4);
        chain.curveTo(left, bottom + height, left, bottom + height, left, bottom);
        g2d.draw(chain);

        g2d.setStroke(new BasicStroke(1f));
    };

}

class Spectacles extends EyeWear {

    @Override
    public void drawEyeWear(Graphics2D g2d) {

        // prepare strokes
        Stroke boldStroke = new BasicStroke(4f, 1, 1);

        // fill frames
        g2d.setColor(new Color(128, 128, 128, 64));
        g2d.fillOval(left + width * 5 / 16, midy, height / 3, height / 3);
        g2d.fillOval(left + width * 9 / 16, midy, height / 3, height / 3);

        // draw framesss
        g2d.setStroke(boldStroke);
        g2d.setColor(Color.black);
        g2d.drawOval(left + width * 5 / 16, midy, height / 3, height / 3);
        g2d.drawOval(left + width * 9 / 16, midy, height / 3, height / 3);

        // draw bridge
        Path2D.Double bridge = new Path2D.Double();
        bridge.moveTo(left + width * 5 / 16 + height / 3, midy + height / 6);
        bridge.curveTo((left + width / 2), midy + height / 12, (left + width / 2), midy + height / 12,
                left + width * 9 / 16, midy + height / 6);
        g2d.draw(bridge);

        // draw arms
        // g2d.drawLine(left, top,left+width*5/16,midy+height/6);

        g2d.setStroke(new BasicStroke());
    };

}

class EyePatch extends EyeWear {

    @Override
    public void drawEyeWear(Graphics2D g2d) {

        // prepare stroke
        Stroke stripStroke = new BasicStroke(10, 1, 1);

        // draw patch
        g2d.fillOval(left + width * 0 / 32, top, width * 7 / 16, height * 5 / 6);

        // draw strip
        g2d.setStroke(stripStroke);
        Path2D.Double strip = new Path2D.Double();
        strip.moveTo(left,top + height * 9 / 10);
        strip.curveTo(left+width/2,top,left+width/2,top,right, top-height/2);
        g2d.draw(strip);

        g2d.setStroke(new BasicStroke());
    };

}

class GlassesSquare extends EyeWear{

    @Override
    public void drawEyeWear(Graphics2D g2d) {

        // prepare stroke
        Stroke stripStroke = new BasicStroke(10f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);

        
        // fill lenses
        g2d.setColor(new Color(128,128,128,64));
        g2d.fillRect(left+width*1/16, top+height/16, width*6/16, height*3/5);
        g2d.fillRect(left+width*9/16, top+height/16, width*6/16, height*3/5);

        // draw frame -eye portion
        g2d.setStroke(stripStroke);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(left+width*1/16, top+height/16, width*6/16, height*3/5);
        g2d.drawRect(left+width*9/16, top+height/16, width*6/16, height*3/5);

        // draw rest of frame
        g2d.drawLine(left,top, left+width*1/16, top+height/16 );
        g2d.drawLine(right,top, left+width*15/16, top+height/16 );
        g2d.drawLine(left+width*7/16,top+height*4/16, left+width*9/16, top+height*4/16 );

        // resets g2d
        g2d.setStroke(new BasicStroke());
        g2d.setColor(Color.BLACK);
        
    };
}

class GlassesRound extends EyeWear{

    @Override
    public void drawEyeWear(Graphics2D g2d) {
    
    // prepare stroke
    Stroke stripStroke = new BasicStroke(5f, BasicStroke.CAP_ROUND,
    BasicStroke.JOIN_ROUND);
    
    // fill lenses
    g2d.setColor(new Color(128,128,128,64));
    g2d.fillOval(left+width*1/16, top+height/16, width*6/16, height*3/5);
    g2d.fillOval(left+width*9/16, top+height/16, width*6/16, height*3/5);

    // draw frame -eye portion
    g2d.setStroke(stripStroke);
    g2d.setColor(Color.BLACK);
    g2d.drawOval(left+width*1/16, top+height/16, width*6/16, height*3/5);
    g2d.drawOval(left+width*9/16, top+height/16, width*6/16, height*3/5);

    // draw rest of frame
    g2d.drawLine(left,top, left+width*1/16, top+height*4/16 );
    g2d.drawLine(right,top, left+width*15/16, top+height*4/16 );
    g2d.drawLine(left+width*7/16,top+height*4/16, left+width*9/16, top+height*4/16 );

    // resets g2d
    g2d.setStroke(new BasicStroke());
    g2d.setColor(Color.BLACK);
    
    
    
    
    }

}

class TinyBlack extends EyeWear{
    @Override
    public void drawEyeWear(Graphics2D g2d) {

                // prepare strokes
                Stroke boldStroke = new BasicStroke(4f, 1, 1);
                g2d.setStroke(boldStroke);
                g2d.setColor(Color.black);

                // fill frames
                g2d.fillOval(left + width * 5 / 16, midy, height / 3, height / 3);
                g2d.fillOval(left + width * 9 / 16, midy, height / 3, height / 3);

                // draw bridge
        Path2D.Double bridge = new Path2D.Double();
        bridge.moveTo(left + width * 5 / 16 + height / 3, midy + height / 6);
        bridge.curveTo((left + width / 2), midy + height / 12, (left + width / 2), midy + height / 12,
                left + width * 9 / 16, midy + height / 6);
        g2d.draw(bridge);

        // draw arms
         g2d.drawLine(left, top,left+width*5/16,midy+height/6);
         g2d.drawLine(right, top,AssistingMethods.symmetricHorizonal(left+width*5/16),midy+height/6);

        g2d.setStroke(new BasicStroke());
    }
}

class SunglassesPablo extends EyeWear{

    @Override
    public void drawEyeWear(Graphics2D g2d) {
    
    // prepare stroke
    Stroke stripStroke = new BasicStroke(5f, BasicStroke.CAP_ROUND,
    BasicStroke.JOIN_ROUND);
    
    // fill lenses
    g2d.setColor(new Color(255,255,0,175));
    g2d.fillOval(left, top+height/16, width*7/16, height*4/5);
    g2d.fillOval(left+width*9/16, top+height/16, width*7/16, height*4/5);

    // draw frame -eye portion
    g2d.setStroke(stripStroke);
    g2d.setColor(Color.DARK_GRAY);
    g2d.drawOval(left, top+height/16, width*7/16, height*4/5);
    g2d.drawOval(left+width*9/16, top+height/16, width*7/16, height*4/5);

    // draw rest of frame
    g2d.drawLine(left,top, left+width*1/32, top+height*4/16 );
    g2d.drawLine(right,top, left+width*31/32, top+height*4/16 );
    g2d.drawLine(left+width*6/16,top+height*3/16, left+width*10/16, top+height*3/16 );
    Path2D.Double lowerBridge = new Path2D.Double();
    lowerBridge.moveTo(left + width * 7 / 16, top + height / 2);
    lowerBridge.curveTo(left+ width * 7 / 16, top+ height / 4, left + width * 9 / 16, top+ height / 4, left + width * 9 / 16, top + height / 2);
    g2d.draw(lowerBridge);

    // resets g2d
    g2d.setStroke(new BasicStroke());
    g2d.setColor(Color.BLACK);
    };
}

class SunglassesTyler extends EyeWear{

    @Override
    public void drawEyeWear(Graphics2D g2d) {
    
    // prepare stroke
    Stroke stripStroke = new BasicStroke(9f, BasicStroke.CAP_ROUND,
    BasicStroke.JOIN_ROUND);
    
    // fill lenses
    g2d.setColor(new Color(255,0,0,210));
    g2d.fillOval(left, top-height/7, width*7/16, height*8/10);
    g2d.fillOval(left+width*9/16, top-height/7, width*7/16, height*8/10);

    // draw frame -eye portion
    g2d.setStroke(stripStroke);
    g2d.setColor(Color.decode("#C0C0C0"));
    g2d.drawOval(left, top-height/7, width*7/16, height*8/10);
    g2d.drawOval(left+width*9/16, top-height/7, width*7/16, height*8/10);

    // draw rest of frame
    g2d.drawLine(left,top, left+width*0/64, top+height*3/16 );
    g2d.drawLine(right,top, left+width*64/64, top+height*3/16 );

    Path2D.Double lowerBridge = new Path2D.Double();
    lowerBridge.moveTo(left + width * 7 / 16, top + height / 4);
    lowerBridge.curveTo(left+ width * 7 / 16, top+ height / 8, left + width * 9 / 16, top+ height / 8, left + width * 9 / 16, top + height / 4);
    g2d.draw(lowerBridge);

    // resets g2d
    g2d.setStroke(new BasicStroke());
    g2d.setColor(Color.BLACK);
    };
}

class SunglassesJackie extends EyeWear{

    @Override
    public void drawEyeWear(Graphics2D g2d) {
          
    // prepare stroke
    Stroke stripStroke = new BasicStroke(5f, BasicStroke.CAP_ROUND,
    BasicStroke.JOIN_ROUND);
    
    // fill lenses
    g2d.setColor(Color.BLACK);
    g2d.fillOval(left, top+height/16, width*7/16, height*9/10);
    g2d.fillOval(left+width*9/16, top+height/16, width*7/16, height*9/10);

    // draw frame -eye portion
    g2d.setStroke(stripStroke);
    g2d.setColor(Color.BLACK);
    g2d.drawOval(left, top+height/16, width*7/16, height*9/10);
    g2d.drawOval(left+width*9/16, top+height/16, width*7/16, height*9/10);

    // draw rest of frame
    g2d.drawLine(left,top, left+width*1/32, top+height*4/16 );
    g2d.drawLine(right,top, left+width*31/32, top+height*4/16 );
    g2d.drawLine(left+width*6/16,top+height*3/16, left+width*10/16, top+height*3/16 );
    Path2D.Double lowerBridge = new Path2D.Double();
    lowerBridge.moveTo(left + width * 7 / 16, top + height / 2);
    lowerBridge.curveTo(left+ width * 7 / 16, top+ height / 4, left + width * 9 / 16, top+ height / 4, left + width * 9 / 16, top + height / 2);
    g2d.draw(lowerBridge);

    // resets g2d
    g2d.setStroke(new BasicStroke());
    g2d.setColor(Color.BLACK);

    };
}
//
class SunglassesJanice extends EyeWear{

    @Override
    public void drawEyeWear(Graphics2D g2d) {

             // prepare stroke
         Stroke frameStroke = new BasicStroke(12f, BasicStroke.CAP_ROUND,
         BasicStroke.JOIN_ROUND);
    
          // draw frame -eye portion
        g2d.setStroke(frameStroke);
        g2d.setColor(Color.decode("#DAA520"));

        g2d.drawLine(left, top, left+width*1/16, top+height*2/10);
        g2d.drawLine(right, top, left+width*15/16, top+height*2/10);
        g2d.drawLine(left+width*7/16, top+height*2/10, left+width*9/16, top+height*2/10);

        Path2D.Double leftHeart = new Path2D.Double();
        leftHeart.moveTo(left+width*4/16,top+height*2/10);
        leftHeart.curveTo(left, top-height*2/10, left, top+ height / 2, left+width*4/16,top+height*4/5);
        leftHeart.curveTo(left+ width * 8 / 16, top+ height / 2, left + width * 8 / 16, top-height*2/10, left+width*4/16,top+height*2/10);
        Shape rightHeart = drawMirrored(leftHeart);

        g2d.draw(leftHeart);
        g2d.draw(rightHeart);
        g2d.setColor(Color.RED);
        g2d.fill(leftHeart);
        g2d.fill(rightHeart);
        g2d.setStroke(new BasicStroke());
    };
}

class SunglassesNeo extends EyeWear{

    @Override
    public void drawEyeWear(Graphics2D g2d) {

     // prepare stroke
    Stroke stripStroke = new BasicStroke(5f, BasicStroke.CAP_ROUND,
    BasicStroke.JOIN_ROUND);
    
    // fill lenses
    g2d.setColor(Color.BLACK);
    g2d.fillOval(left, top+height/16, width*7/16, height*4/10);
    g2d.fillOval(left+width*9/16, top+height/16, width*7/16, height*4/10);

    // draw frame -eye portion
    g2d.setStroke(stripStroke);
    g2d.setColor(Color.BLACK);
    g2d.drawOval(left, top+height/16, width*7/16, height*4/10);
    g2d.drawOval(left+width*9/16, top+height/16, width*7/16, height*4/10);

    // draw rest of frame
    g2d.drawLine(left,top, left+width*1/32, top+height*4/16 );
    g2d.drawLine(right,top, left+width*31/32, top+height*4/16 );

    g2d.drawLine(left+width*6/16,top+height*3/16, left+width*10/16, top+height*3/16 );
    Path2D.Double lowerBridge = new Path2D.Double();
    lowerBridge.moveTo(left+width*6/16,top+height*3/16);
    lowerBridge.curveTo(left+ width * 7 / 16, top+ height / 4, left + width * 9 / 16, top+ height / 4, left+width*10/16, top+height*3/16);
    g2d.draw(lowerBridge);

    // resets g2d
    g2d.setStroke(new BasicStroke());
    g2d.setColor(Color.BLACK);
    };
}