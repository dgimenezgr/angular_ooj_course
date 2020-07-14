/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesarcypher;

import edu.duke.FileResource;

/**
 *
 * @author daniel
 */
public class WordLengths {
    
    public static void countWordLengths(FileResource resource, int[] counts) {
        
        for (String s : resource.words()) {
            int wordLength = s.length();
            
            if (!Character.isLetter(s.charAt(wordLength - 1))) {
                wordLength = wordLength - 1;
            }

            if (!Character.isLetter(s.charAt(wordLength - 1))) {
                wordLength = wordLength - 1;
            }

            counts[wordLength] += 1;
        }
        
        for (int k = 1; k < counts.length; k++) {
            System.out.println("Length " + k + ": " + counts[k] + " words.");
        }
    }
    
    public static void main(String[] args) {
        FileResource resource = new FileResource("data/lotsOfWords.txt");
        
        int[] counts = new int[31];
        
        countWordLengths(resource, counts);
    }
    
}
