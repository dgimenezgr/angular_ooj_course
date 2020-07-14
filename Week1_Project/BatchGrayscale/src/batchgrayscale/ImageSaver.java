/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchgrayscale;

import edu.duke.*;
import java.io.File;
import java.util.HashSet;

/**
 *
 * @author daniel
 */
public class ImageSaver {
    public static void doSave() {
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "copy-" + fname;
            image.setFileName(newName);
            image.save();
            
//            image.draw();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        doSave();
    }
}
