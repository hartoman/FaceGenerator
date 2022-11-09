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

import javax.swing.*;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

/**
 *
 * @author chris
 */

public class FaceFrame extends JFrame {

    static FaceGrid grid;
    static UiPanel uipanel;
    static MenuBar menu;


    public FaceFrame(String title, int w, int h, int rows, int columns) {

        setSize(w * 2, h);
        setTitle(title);

        // sets icon
        ImageIcon image = new ImageIcon(FaceFrame.class.getResource("/icon.jpg"));
        this.setIconImage(image.getImage());

        setLayout(new GridLayout(1, 2));

        menu = new MenuBar();
        setJMenuBar(menu);

        uipanel = new UiPanel(w, h);
        grid = new FaceGrid(uipanel.getFace(),w,h,rows,columns);

        add(grid);
        add(uipanel);
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
