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
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.security.CodeSource;
import  Faces.FacialFeatures.Face;
import java.io.*;



// all of the export-import methods
public class GroupedMenuBarFunctions {

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
    public void ToJpg(String filename) {

        String fullfilename = filename + ".jpg";

        BufferedImage bi = new BufferedImage(FaceFrame.grid.getWidth(), FaceFrame.grid.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        FaceFrame.grid.paint(g); // this == JComponent
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", new File(fullfilename));
        } catch (Exception e) {
        }
    }

    // exports map as transparent png picture
    public void ToPng(String filename) {

        String fullfilename = filename + ".png";
        BufferedImage bi = new BufferedImage(FaceFrame.grid.getWidth(), FaceFrame.grid.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        FaceFrame.grid.paint(g); // this == JComponent
        g.dispose();
        try {
            ImageIO.write(bi, "png", new File(fullfilename));
        } catch (Exception e) {
        }
    }

    public void serialFace(String filename, Face face) {

        String fullname = filename + ".ser";
        try {
            FileOutputStream fileOut = new FileOutputStream(fullname);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(face);

            out.close();
            fileOut.close();
            System.out.println("Face saved!");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    public Face deSerialFace(String fullpath) {
        Face face = null;
        String filename = fullpath;// + ".ser";

        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            face = (Face) in.readObject();
            in.close();
            fileIn.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not Found  or Wrong File Name");

        } catch (IOException e) {
            System.out.println("Incompatible serialVersionUID");
            // e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
        }

        return face;

    }
}