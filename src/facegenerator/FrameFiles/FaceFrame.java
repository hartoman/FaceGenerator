/*
 * Made by Christos Chartomatsidis, 2022
 * This application is free to use, but it comes as-is:
 * I hold no responsibility for any damage or loss of that may arise from it's use.
 * Attribution is not required, but would be greatly appreciated.
 * For any comments, bug-reports, and ideas do not hesitate to contact me at:
 * hartoman@gmail.com
 */
package facegenerator.FrameFiles;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;

import Toolz.ImageScaler;
import facegenerator.Face;
/**
 *
 * @author chris
 */

public class FaceFrame extends JFrame {
    
   int w,h,rows,columns,wdOfRow,htOfRow;
   Grid grid;
   UiPanel uipanel;

    public FaceFrame(String title, int w, int h, int rows, int columns){
        this.w=w;
        this.h=h;
        this.rows=rows;
        this.columns=columns;
        wdOfRow = w/columns;
        htOfRow = h/rows;

        setSize(w*2, h + 40);
        setTitle(title);
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        grid = new Grid();
        add(grid);

        uipanel = new UiPanel(w,h);
        add(uipanel);
       
    }



// sets up the painting grid
    class Grid extends JPanel {

        private Image background;
        private Face face;
    
        Grid() {
            setSize(w,  h);
            setPreferredSize(new Dimension(w, h));
            setMinimumSize(new Dimension(w, h));
            setMaximumSize(new Dimension(w, h));
            face = new Face(w,h);

        //    setBackgroundImage("pir.jpg");
            
        }
    
        @Override
        public void paintComponent(Graphics g) {
    
            super.paintComponent(g);
    
            // draws background image
            g.drawImage(background, 0, 0, null);
            
            //paints the gridlines
            //paintGridlines(g);

            // paints the face
            paintFace(g);
            
            g.dispose();
            
        }
    
        // sets the background image
        public void setBackgroundImage(String filename) {
    
            if (filename != null) {
                try {
                    URL url = getClass().getClassLoader().getResource(filename);
                   background = ImageIO.read(url).getScaledInstance(w, h, Image.SCALE_SMOOTH);
                //    background = ImageIO.read(url);
                } catch (Exception ex) {
                    System.out.println("at least one wrong filename");
                    System.exit(1);
                }
            }
            removeAll();
        }
        
        // convenience method, draws a grid
        void paintGridlines(Graphics g){
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
        void paintFace(Graphics g){
            
            // transforms Graphics to Graphics2D 
            Graphics2D g2d =(Graphics2D)g;

            // ...which can have anti-aliasing, to smoothen lines and edges
            RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHints(rh);

            // and draw the face
            face.drawFace(g2d);
        }
    
    }
    
}
