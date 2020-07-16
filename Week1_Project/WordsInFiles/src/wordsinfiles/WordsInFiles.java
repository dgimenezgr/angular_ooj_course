/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsinfiles;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author daniel
 */
public class WordsInFiles {

    private HashMap<String,ArrayList> wordsToFiles;
    
    public WordsInFiles() {
        wordsToFiles = new HashMap<String,ArrayList>();
    }
    
    private void addWordsFromFile(File f) {
        
        FileResource fr = new FileResource(f);
        
        String fileName = f.getName();
        
        for (String word : fr.words()) {
            
            if (!wordsToFiles.containsKey(word)) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(fileName);
                wordsToFiles.put(word, list);
            } else {
                ArrayList<String> curr = wordsToFiles.get(word);
                curr.add(fileName);
                wordsToFiles.put(word, curr);
            }
        }
    }
    
    public void buildWordFileMap() {
        
        wordsToFiles.clear();
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);        
        }
    }
    
    public int maxNumber() {
        int maxNumber = 0;
        
        for (String word : wordsToFiles.keySet()) {
            ArrayList<String> files = wordsToFiles.get(word);
            int fileCount = files.size();
            if (fileCount > maxNumber) {
                maxNumber = fileCount;            
            }
        }
        
        return maxNumber;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordsInTheseFiles = new ArrayList<String>();
        
        for (String word : wordsToFiles.keySet()) {
            ArrayList<String> files = wordsToFiles.get(word);
            int filesCount = files.size();
            if (filesCount == number) {
                wordsInTheseFiles.add(word);
            }
        }
        
        return wordsInTheseFiles;
    }
    
    public void printFilesIn(String word) {
        ArrayList<String> files = wordsToFiles.get(word);
        for (int k = 0; k < files.size(); k++) {
            System.out.println(files.get(k));
        }
    }
}
