//package Faces;

import javax.swing.JFrame;
import FrameFiles.FaceFrame;

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
        a.setExtendedState(JFrame.MAXIMIZED_BOTH);//makes screen adjust to fullsize
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        a.setVisible(true);
        a.setVisible(true);
    }



}
