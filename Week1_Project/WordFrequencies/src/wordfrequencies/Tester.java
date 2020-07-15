/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordfrequencies;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author daniel
 */
public class Tester {
    public static void main(String args[]) {
        WordFrequencies wf = new WordFrequencies();
        int wordCount = wf.findUnique().size();
        System.out.println("Number of unique words: " + wordCount);
        System.out.println("----------");
        System.out.println("");
        ArrayList<String> words = wf.getMyWords();
        ArrayList<Integer> freqs = wf.getMyFreqs();
        
        int maxCountIdx = wf.findIndexOfMax();
        
        String maxWord = words.get(maxCountIdx);
        
        System.out.println("Word freqs:");
        System.out.println("----------");
        for (int k = 0; k < wordCount; k++) {
            System.out.println(freqs.get(k) + " " + words.get(k));
        }
        System.out.println("");
        System.out.println("The word that occurs most often and its count are: " + maxWord + " " + freqs.get(maxCountIdx));
        System.out.println("----------");
        System.out.println("");
    }
}
