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
package  Faces.FrameFiles;

import javax.swing.*;
import  Faces.FacialFeatures.Face;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

public class MenuBar extends JMenuBar {

    JMenuItem m00, m01, m10, m11, m12, m21, m22;
    GroupedMenuBarFunctions gmf;

    MenuBar() {
        gmf = new GroupedMenuBarFunctions();
        addMenuButtons();
        addMenuListeners();
    }

    // adds the buttons to the menu
    void addMenuButtons() {

        JMenu column0 = new JMenu("I'm Serial");

        m00 = new JMenuItem("Serialize");
        m01 = new JMenuItem("DeSerialize");
        column0.add(m00);
        column0.add(m01);
        add(column0);

        // first columne
        JMenu column1 = new JMenu("Export Image...");
        m11 = new JMenuItem("... as jpg");
        m12 = new JMenuItem("... as png (transparent background)");

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

    // assign action listeners to menuitems
    void addMenuListeners() {

        // "Save image as jpg"
        m11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String filename = JOptionPane.showInputDialog(null, "Enter filename", "Save as jpg (blue background)",
                        JOptionPane.INFORMATION_MESSAGE);
                String fullpath = gmf.getCurrentDirectoryPath() + filename;
                if ((filename != null) && (!filename.isEmpty())) {
                    
                  //  FaceFrame.grid.setTransparentBackground(false);
                    gmf.ToJpg(fullpath);

                }

            }
        });
        // "Save image as png"
        m12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String filename = JOptionPane.showInputDialog(null, "Enter filename",
                        "Save image as png (transparent background)", JOptionPane.INFORMATION_MESSAGE);
                String fullpath = gmf.getCurrentDirectoryPath() + filename;
                if ((filename != null) && (!filename.isEmpty())) {

                    FaceFrame.grid.setTransparentBackground(true);
                    FaceFrame.grid.removeAll();
                    FaceFrame.grid.updateUI();
                    gmf.ToPng(fullpath);
                    FaceFrame.grid.setTransparentBackground(false);
                }

            }
        });

        // "SERIALIZE
        m00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                JFileChooser fc = new JFileChooser(gmf.getCurrentDirectoryPath());
                int choice = fc.showSaveDialog(null);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fc.getSelectedFile();
                    String fullname = fileToSave.getAbsolutePath();
                    gmf.serialFace(fullname, FaceFrame.grid.getFace());

                }

            }
        });

        // DESERIALIZE
        m01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                // dialog opens focused on current directory path
                JFileChooser fc = new JFileChooser(gmf.getCurrentDirectoryPath());

                // restrict the user to select files of all types
                fc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only serializable Face files", "ser");
                fc.addChoosableFileFilter(restrict);

                int choice = fc.showOpenDialog(null);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    String fullpath = fc.getSelectedFile().getAbsolutePath();
                    System.out.println(fullpath);
                    Face tmp = null;
                    tmp = gmf.deSerialFace(fullpath);
                    if (tmp != null) {
                        FaceFrame.uipanel.setFace(tmp);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incompatible serialVersionUID\n\n"
                                + "Make sure that the .ser file is of type 'Face' and formatted in the current version",
                                "Error Loading File", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        ////////////////////// column 2 ////////////////////////

        // TODO: UPDATE
        m21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "What Can You do with Face Generator:\n"
                +"\nThis simple program draws cartoonish sketches of faces, just by using equations based on the ratios\n"
                +"between various facial characteristics.So EVERYTHING you see, is just a product of simple maths (which \n"
                +"nonetheless required painful trial-and-error) , < not a single line > has been hand-drawn.\n\n"
                +"The goal is to easily make on-the-fly NPCs for both pen-and-paper and computer games.\n"
                +"The faces made can be exported as .jpg, .png (with transparent background), or as a serialized .ser file.\n"
                +"The .ser files can later be reloaded, and the emotions they display can be changed,\n"
                +"which can be super-useful for NPCs reactions to story events, dialog choices etc.\n"
                +"\nThe user is able to create his own sketch and fine-tune most of the features, but there is also the choice for\n"
                +"Completely randomly generated   The possibilities and combinations are practically endless"
                
                        , "How to Use", JOptionPane.QUESTION_MESSAGE);
            }
        });
        m22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "Face Generator v_1.0\n\n" +
                        "Boring legal stuff:\n" +
                        "\nCopyright © 2022 Christos Chartomatsidis\n" +
                        "This program is free software: you can redistribute it and/or modify it under the terms of the GNU \nGeneral Public License as published by"
                        + " the Free Software Foundation, either version 3 of the License,\nor (at your option) any later version. This program is distributed in the hope that it will be useful,\n but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS \nFOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. \nYou should have received a copy of the GNU General Public License along with this program. \nIf not, see http://www.gnu.org/licenses/."

                        + "\n\nAttribution is not required but would be very much appreciated.\n"

                        + "For comments, bug reports, suggestions, or anything else, drop me a line at\nhartoman@gmail.com\n"
                        + "I hope you guys have as much fun using it, as I had creating it :)", "About",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

   

}
