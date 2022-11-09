
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

import javax.swing.JFrame;
import  Faces.FrameFiles.FaceFrame;


/**
 *
 * @author chris
 */
public class FaceGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // gets height of the screen
        int height = FaceFrame.getScreenHeightPercentage(90);
        FaceFrame.adaptUIFont();

        FaceFrame a = new FaceFrame("Random Face Generator", height, height, 20, 20);
        a.setExtendedState(JFrame.MAXIMIZED_BOTH);// makes screen adjust to fullsize
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        a.setVisible(true);
        a.setVisible(true);
    }

}
