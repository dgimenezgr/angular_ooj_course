/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherdata;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author daniel
 */
public class WeatherData {

    public static int lowestHumidityInYear() {
        DirectoryResource dr = new DirectoryResource();
        
        int lowestHumidity = -1;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            for (CSVRecord record : fr.getCSVParser()) {
                System.out.println(record.get("Humidity") + "," + record.get("DateUTC"));
            }
        }
        
        return lowestHumidity;
    }

    public static int lowestTemperatureInYear() {
        DirectoryResource dr = new DirectoryResource();
        
        int lowestHumidity = -1;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            for (CSVRecord record : fr.getCSVParser()) {
                System.out.println(record.get("TemperatureF") + "," + record.get("DateUTC"));
                
            }
        }
        
        return lowestHumidity;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Test lowestHumidityInYear()
//        System.out.println("Menor humedad en 2013: " + lowestHumidityInYear());
        System.out.println("Menor temperatura en 2014: " + lowestTemperatureInYear());
    }
    
}
