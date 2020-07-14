/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchgrayscale;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import java.io.File;

/**
 *
 * @author daniel
 */
public class BatchInversions {
    public static ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
           
            int newRed = 255 - inPixel.getRed();
            int newGreen = 255 - inPixel.getGreen();
            int newBlue = 255 - inPixel.getBlue();
            
            pixel.setRed(newRed);
            pixel.setGreen(newGreen);
            pixel.setBlue(newBlue);
        }
        
        return outImage;
    }
    
    public static void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            
            ImageResource inverted = makeInversion(inImage);
            
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;

            inverted.setFileName(newName);
            inverted.save();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        selectAndConvert();
    }
}
