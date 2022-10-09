/*
 * Made by Christos Chartomatsidis, 2022
 * This application is free to use, but it comes as-is:
 * I hold no responsibility for any damage or loss of that may arise from it's use.
 * Attribution is not required, but would be greatly appreciated.
 * For any comments, bug-reports, and ideas do not hesitate to contact me at:
 * hartoman@gmail.com
 */
package FrameFiles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import FacialFeatures.Face;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

/**
 *
 * @author chris
 */

public class FaceFrame extends JFrame {

    static int w, h, rows, columns, wdOfRow, htOfRow;
    static Grid grid;
    static UiPanel uipanel;
    static MenuBar menu;


    public FaceFrame(String title, int w, int h, int rows, int columns) {

        FaceFrame.w = w;
        FaceFrame.h = h;
        FaceFrame.rows = rows;
        FaceFrame.columns = columns;
        wdOfRow = w / columns;
        htOfRow = h / rows;

        setSize(w * 2, h);
        setTitle(title);

        // sets icon
        ImageIcon image = new ImageIcon(FaceFrame.class.getResource("/icon.jpg"));
        this.setIconImage(image.getImage());

        setLayout(new GridLayout(1, 2));

        menu = new MenuBar();
        setJMenuBar(menu);

        uipanel = new UiPanel(w, h);
        grid = new Grid(uipanel.getFace());

        add(grid);
        add(uipanel);
        

    }

    public static int w() {
        return FaceFrame.w;
    }

    public static int h() {
        return FaceFrame.h;
    }

    public static void transparentBackground(boolean transparent) {
        if (transparent) {
            FaceFrame.grid.background = null;
            FaceFrame.grid.setBackground(new Color(255, 255, 255, 0));
        } else {
            FaceFrame.grid.setBackground(new Color(255, 255, 255, 255));
        }
    }

    // sets up the painting grid
    class Grid extends JPanel {

        Image background;
        Face face;

        Grid(Face face) {
            setSize(w, h);
            setPreferredSize(new Dimension(w, h));
            setMinimumSize(new Dimension(w, h));
            setMaximumSize(new Dimension(w, h));
            this.face = face;

            // setBackgroundImage("pir.jpg");
        }

        @Override
        public void paintComponent(Graphics g) {

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
                    URL url = getClass().getClassLoader().getResource(filename);
                    background = ImageIO.read(url).getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    // background = ImageIO.read(url);
                } catch (Exception ex) {
                    System.out.println("at least one wrong filename");
                    System.exit(1);
                }
            }
            removeAll();
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

    // gets a given percentage of the screen height
    public static int getScreenHeightPercentage(int percentage) {
        GraphicsDevice size = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int height = (int) size.getDisplayMode().getHeight() * percentage / 100;
        return height;
    }

    // sets the global font for the application
    public static void adaptUIFont() {

        // javax.swing.plaf.FontUIResource f = new Font();
        java.util.Enumeration keys = UIManager.getDefaults().keys();

        // adjusts the font size based on screen height in pixels
        int height = getScreenHeightPercentage(100);
        int adjFontSize = (height / 64) + 1;

        // assigns the adjusted font size to all elements
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, new javax.swing.plaf.FontUIResource("Dialog", Font.BOLD, adjFontSize));
            }
        }
    }
}