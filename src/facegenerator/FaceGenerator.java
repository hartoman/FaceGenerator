/*
 * Made by Christos Chartomatsidis, 2022
 * This application is free to use, but it comes as-is:
 * I hold no responsibility for any damage or loss of that may arise from it's use.
 * Attribution is not required, but would be greatly appreciated.
 * For any comments, bug-reports, and ideas do not hesitate to contact me at:
 * hartoman@gmail.com
 */
package facegenerator;

import javax.swing.JFrame;

/**
 *
 * @author chris
 */
public class FaceGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        FaceFrame a = new FaceFrame("Random Face Generator", 580, 620, 20, 20);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        a.setVisible(true);
        a.setVisible(true);
    }
    
}
