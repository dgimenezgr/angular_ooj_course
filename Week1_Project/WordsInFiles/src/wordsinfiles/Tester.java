/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsinfiles;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author daniel
 */
public class Tester {

    public static void printWholeInfo() {
        WordsInFiles wif = new WordsInFiles();
        wif.buildWordFileMap();
        
        int maxNumberOfFiles = wif.maxNumber();
        
        ArrayList<String> wordsInMaxNumberOfFilesAL = wif.wordsInNumFiles(maxNumberOfFiles);
        
        int wordsInMaxNumberOfFilesNo = wordsInMaxNumberOfFilesAL.size();
        
        System.out.println("The greatest number of files a word appears in is " + maxNumberOfFiles + ", and there are " + wordsInMaxNumberOfFilesNo + " such words: " + wordsInMaxNumberOfFilesAL.toString());

        for (int k = 0; k < wordsInMaxNumberOfFilesAL.size(); k++) {
            String thisWord = wordsInMaxNumberOfFilesAL.get(k);
            System.out.println(thisWord + " appears in the files:");
            wif.printFilesIn(thisWord);
            System.out.println("");
        }
        
        ArrayList<String> wordsInFourFiles = wif.wordsInNumFiles(4);
        
        int wordsInFourFilesInt = wordsInFourFiles.size();
        
        System.out.println("There are " + wordsInFourFilesInt + " words that appear in 4 files.");
        
        System.out.println("The files in which tree appears are: ");
        wif.printFilesIn("tree");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        printWholeInfo();
    }
}
