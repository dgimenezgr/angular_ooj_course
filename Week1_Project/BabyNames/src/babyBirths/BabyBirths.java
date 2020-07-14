/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babyBirths;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author daniel
 */
public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn >= 100) {
                System.out.println("Name: " + rec.get(0));
                System.out.println("Gender: " + rec.get(1));
                System.out.println("Nº Born: " + rec.get(2));
            }
        }
    }
    
    public static void totalBirths(FileResource fr) {
        int totalBirths = 0;
        
        // Name counters
        int nameCount = 0;
        int girlNames = 0;
        int boyNames = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            nameCount++;
            
            if (rec.get(1).equals("M")) {
                boyNames++;
            } else if (rec.get(1).equals("F")) {
                girlNames++;
            }
        }
        
        System.out.println("Total births: " + totalBirths);
        System.out.println("Boy births: " + boyNames);
        System.out.println("Girl births: " + girlNames);
        System.out.println("Name count: " + nameCount);
    }
    
    public static int getRank(int year, String name, String gender) {
        int rank = 0;
        int thisRank = -1;
        
        FileResource fr = new FileResource("us_babynames_by_year\\yob" + year + ".csv");
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(1).equals(gender) && rec.get(0).equals(name)) {
                    thisRank = rank;
                }
            }
        }
        
        return thisRank;
    }
    
    public static String getName(int year, int rank, String gender) {
        String newName = "NO NAME";
        
        int rankCounter = 0;
        
        FileResource fr = new FileResource("us_babynames_by_year\\yob" + year + ".csv");
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rankCounter++;
                if (rankCounter == rank) {
                    newName = rec.get(0);
                }
            }
        }
        return newName;
    }
    
    public static String whatIsNameInYear(String name, int year, int newYear, String gender) {
        String newName = "There was no instance of this name that year.";
        
        int thisRank = getRank(year, name, gender);
        
        if (getName(newYear, thisRank, gender) != "NO NAME") {
            String tempName = getName(newYear, thisRank, gender);
            String pronoun = gender.equals("M") ? "he" : "she";
            newName = name + " born in " + year + " would be " + tempName + " if " + pronoun + " was born in " + newYear + "."; // Isabella born in 2012 would be Sophia if she was born in 2014.
        }
        
        return newName;
    }
    
    public static int yearOfHighestRank(String name, String gender) {
        int year = -1;
        
        int highestRank = 0;
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            String thisYearSt = f.getName();
            
            int thisYear = cleanYear(thisYearSt);
            
            int thisRank = getRank(thisYear, name, gender);
            
            System.out.println("Año " + thisYear + ": Rango " + thisRank);

            if ((highestRank == 0 && thisRank != -1) || (highestRank != 0 && (thisRank < highestRank))) {
                highestRank = thisRank;
                year = thisYear;
                
                System.out.println(highestRank);
            }            
        };
        
        
        return year;
    }
    
    public static double getAverageRank(String name, String gender) {
        int yearCounter = 0;
        
        int totalSum = 0;
        
        double averageRank = -1.0;
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            String thisYearSt = f.getName();
            
            int thisYear = cleanYear(thisYearSt);
            
            int thisRank = getRank(thisYear, name, gender);
            
            if (thisRank != -1) {
                yearCounter++;
                totalSum += thisRank;
            }            
        };

        if ( totalSum != 0) {
            averageRank = (double) totalSum / yearCounter;            
        }
        
        return averageRank;
    }
    
    public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirths = 0;

        FileResource fr = new FileResource("us_babynames_by_year\\yob" + year + ".csv");
//        FileResource fr = new FileResource("us_babynames_test\\yob" + year + "short.csv");

        int thisNameRank = getRank(year, name, gender);
        
        int currentRank = thisNameRank - 1;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (currentRank == 0) {
                    break;
                } else {
                    int thisNameBirths = Integer.parseInt(rec.get(2));
                    totalBirths += thisNameBirths;
                    System.out.println(rec.get(1));
                }
                currentRank--;
            }
        }

        return totalBirths;
    }
    
    public static int cleanYear(String filename) {
        String thisYearSt = filename.substring(0, filename.lastIndexOf('.')).substring(3);
            
        int thisYear = Integer.parseInt(thisYearSt);

        return thisYear;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        FileResource fr = new FileResource("us_babynames_by_year\\yob1900.csv");
//        totalBirths(fr);
//        

//        System.out.println("getRank() test:");
//        System.out.println("---------------");
//        System.out.println(getRank(1971,"Frank","M"));
//
//        System.out.println("");
//        
//        System.out.println("getName() test:");
//        System.out.println("---------------");
//        System.out.println(getName(1982,450,"M"));
//
//        System.out.println("");
//        
//        System.out.println("whatIsNameInYear() test:");
//        System.out.println("---------------");
//        System.out.println(whatIsNameInYear("Owen",1974,2014,"M"));
//
//        System.out.println("");
//        
//        System.out.println("yearOfHighestRank() test:");
//        System.out.println("---------------");
//        System.out.println(yearOfHighestRank("Mich","M"));
//        
//        System.out.println("");
//        
//        System.out.println("getAverageRank() test:");
//        System.out.println("---------------");
//        System.out.println(getAverageRank("Robert","M"));
//        
//        System.out.println("");
        
//        System.out.println("getTotalBirthsRankedHigher() test:");
//        System.out.println("---------------");
//        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
          
                
    }    

}
