/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchgrayscale;

import edu.duke.*;
import java.io.File;

/**
 *
 * @author daniel
 */
public class BatchGrayscale {

    public static ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        
        return outImage;
    }
    
    public static void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            
            ImageResource gray = makeGray(inImage);
            
            String fname = inImage.getFileName();
            String newName = "gray-" + fname;

            gray.setFileName(newName);
            gray.save();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        selectAndConvert();
    }
    
}
