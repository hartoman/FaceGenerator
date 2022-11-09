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

package Faces.FrameFiles;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import Faces.FacialFeatures.Face;
import java.io.File;
import java.awt.Color;

public class FaceGrid extends JPanel {

    Image background;
    String imgPlaceholder;
    Face face;
    int w, h, rows, columns, wdOfRow, htOfRow;

    FaceGrid(Face face,int w,int h,int rows,int columns) {
        this.w=w;
        this.h=h;
        this.rows=rows;
        this.columns=columns;
        wdOfRow = w / columns;
        htOfRow = h / rows;

        imgPlaceholder=new String();

        setSize(w, h);
        setPreferredSize(new Dimension(w, h));
        setMinimumSize(new Dimension(w, h));
        setMaximumSize(new Dimension(w, h));
        this.face = face;


    }

    @Override
    public void paintComponent(Graphics g) {

        removeAll();
        super.paintComponent(g);

        // draws background image
        g.drawImage(background, 0, 0, null);

        // paints the gridlines
       // paintGridlines(g);

        // paints the face
        paintFace(g);

        g.dispose();

    }

    // sets the background image
    public void setBackgroundImage(String filename) {

        if (filename != null) {
            try {
                background = ImageIO.read(new File(filename)).getScaledInstance(w, h, Image.SCALE_SMOOTH);
                imgPlaceholder=filename;
            } catch (Exception ex) {
                System.out.println("at least one wrong filename");
            //    System.exit(1);
            }
        }else{
            background=null;
            setBackground(new Color(255, 255, 255, 255));
        }
        removeAll();
    }

    public void setTransparentBackground(boolean transparent) {
        if (transparent) {
            background = null;
            setBackground(new Color(255, 255, 255, 0));
        } else {
          setBackgroundImage(imgPlaceholder);
        }
    }

    // convenience method, draws a grid
    public void paintGridlines(Graphics g) {
        // draws gridlines
        int k;
        w = getSize().width;
        h = getSize().height;

        for (k = 0; k < rows; k++) {
            g.drawLine(0, k * htOfRow, w, k * htOfRow);
        }

        for (k = 0; k < columns; k++) {
            g.drawLine(k * wdOfRow, 0, k * wdOfRow, h);
        }
    }

    // core rendering method
    public void paintFace(Graphics g) {

        // transforms Graphics to Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // ...which can have anti-aliasing, to smoothen lines and edges
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        // and draw the face
        face.drawFace(g2d);
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
        repaint();
    }

}
