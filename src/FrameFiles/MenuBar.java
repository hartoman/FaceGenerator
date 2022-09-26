package FrameFiles;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.security.CodeSource;
import java.io.File;



public class MenuBar extends JMenuBar{
    
    JMenuItem m10, m13, m11, m12, m21, m22;
    GroupedMapFunctions gmf;

        MenuBar() {
            gmf=new GroupedMapFunctions();
            addMenuButtons();
            addMenuListeners();
        }

        // adds the buttons to the menu
        void addMenuButtons() {
            // first columne
            JMenu column1 = new JMenu("Import/Export");

            m11 = new JMenuItem("Save image as jpg");
            m12 = new JMenuItem("Save image as png (transparent background)");

            column1.add(m11);
            column1.add(m12);
            add(column1);

            // second column
            JMenu column2 = new JMenu("Help");
            m21 = new JMenuItem("How to use");
            m22 = new JMenuItem("About");
            column2.add(m21);
            column2.add(m22);
            add(column2);

        }

        //assign action listeners to menuitems
        void addMenuListeners() {

            //  "Save image as jpg"
            m11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String filename = JOptionPane.showInputDialog(null, "Enter filename", "Save as jpg (blue background)", JOptionPane.INFORMATION_MESSAGE);
                    String fullpath = gmf.getCurrentDirectoryPath() + filename;
                    if ((filename != null) && (!filename.isEmpty())) {

                        FaceFrame.transparentBackground(false);
                        gmf.ToJpg(fullpath);

                    }

                }
            });
            //  "Save image as png"
            m12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String filename = JOptionPane.showInputDialog(null, "Enter filename", "Save image as png (transparent background)", JOptionPane.INFORMATION_MESSAGE);
                    String fullpath = gmf.getCurrentDirectoryPath() + filename;
                    if ((filename != null) && (!filename.isEmpty())) {

                        FaceFrame.transparentBackground(true);
                        FaceFrame.grid.removeAll();
                        FaceFrame.grid.updateUI();
                        gmf.ToPng(fullpath);
                        
                   
                    }
                    
                }
            });


            ////////////////////// column 2 ////////////////////////
            m21.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JOptionPane.showMessageDialog(null, "Map Parameters: \n"
                     //       + "Number of Squares = how many squares the grid has on each side. For example, 20 means 20x20 map.\n"
                     //       + "Distortion = noise added to the lines of the grid. 0 means that there is no distortion.\n\n"
                     //       + "Various map Options:\n"
                    //        + "Just play around and experiment, there are a lot of customization options for various types of maps"
                    //        + "Import/Export Options:\n"
                    //        + "Save map as jpg/ png = exports the map as image of the chosen type, with the selected transparencies.\n"
                     //       + "Load/export from json = store/load raw map data in json format. All files must be in the folder where the jar is run from.",
                            ,"How to Use", JOptionPane.QUESTION_MESSAGE);
                }
            });
            m22.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JOptionPane.showMessageDialog(null, "Random Map Generator v_1.2\n\n"
                  //          + "is made by Christos Chartomatsidis, 2022.\n"
                    //        + "Free for any use, attribution not required but is much appreciated.\n"
                      //      + "This application may be used as-is, I hold no responsibility if something goes wrong.\nHopefully it won't.\n\n"
                        //    + "For comments, bug reports, suggestions, or anything else, drop me a line at\nhartoman@gmail.com\n"
                          //  + "I hope you guys have as much fun using it, as I had creating it :)",
                            ,"About", JOptionPane.ERROR_MESSAGE);
                }
            });
        }



// all of the export-import methods
class GroupedMapFunctions {

    // returns the folder name of the current directory where the jar runs from
    public String getCurrentDirectoryPath() {

        String jarDir = new String();
        // this gets the folder name from where the jar file is executed        
        try {
            CodeSource codeSource = MenuBar.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            jarDir = jarFile.getParentFile().getPath() + "/";

        } catch (Exception e) {
            System.out.println("exception or whatever");
        }
        return jarDir;
    }


    // exports map as jpg picture
    private void ToJpg(String filename) {

        String fullfilename = filename + ".jpg";

        BufferedImage bi = new BufferedImage(FaceFrame.grid.getWidth(), FaceFrame.grid.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        FaceFrame.grid.paint(g);  //this == JComponent
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", new File(fullfilename));
        } catch (Exception e) {
        }
    }

    // exports map as transparent png picture
    private void ToPng(String filename) {

        String fullfilename = filename + ".png";
        BufferedImage bi = new BufferedImage(FaceFrame.grid.getWidth(), FaceFrame.grid.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        FaceFrame.grid.paint(g);  //this == JComponent
        g.dispose();
        try {
            ImageIO.write(bi, "png", new File(fullfilename));
        } catch (Exception e) {
        }
    }


}
}


