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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // buildWordFileMap()
        WordsInFiles wf = new WordsInFiles();
        
        wf.buildWordFileMap();
        
        // Determine the maximum number of files any word is in
//        int maxNumberOfFiles = wf.maxNumber();
//        // Determine all the words that are in the maximum number of files
//        ArrayList<String> wordsInMaxFiles = wf.wordsInNumFiles(maxNumberOfFiles);
//        // For each such word, print the filenames of the files it is in
//        
//        System.out.println("The maximum number of files in which a single word appears is " + maxNumberOfFiles);
//        System.out.println("-----------------------------");
//        System.out.println("-----------------------------");
//        System.out.println("The words which appear this number of times are ");
//        
//        for (int k = 0; k < wordsInMaxFiles.size(); k++) {
//            String wordInMaxFiles = wordsInMaxFiles.get(k);
//            System.out.println("\"" + wordInMaxFiles + "\"" + " in: ");
//        System.out.println("-----------------------------");
//            wf.printFilesIn(wordInMaxFiles);
//        System.out.println("-----------------------------");
//        System.out.println("");
//        }
//        System.out.println("-----------------------------");
//        System.out.println("-----------------------------");
//        System.out.println("They appear in the following files: ");
        
//        System.out.println(wf.wordsInNumFiles(4).size());
            wf.printFilesIn("tree");
        
    }
}
