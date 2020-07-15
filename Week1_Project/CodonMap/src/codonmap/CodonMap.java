/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codonmap;

import java.util.HashMap;

/**
 *
 * @author el_mo
 */
public class CodonMap {

    private HashMap<String,Integer> codonCount;
    
    public CodonMap() {
        codonCount = new HashMap<String,Integer>();
    }
    
    public HashMap<String,Integer> buildCodonMap(int start, String dna) {
        codonCount.clear();
        
        int lastIdx = dna.length() - (dna.length() % 3) - 1; 
        
        for (int k = start; k < lastIdx; k+=3) {
            String currDna = dna.substring(k,k+3);
            if (!codonCount.containsKey(currDna) && currDna.length() == 3) {
                codonCount.put(currDna, 1);
            } else if (codonCount.containsKey(currDna) && currDna.length() == 3) {
                int value = codonCount.get(currDna);
                codonCount.put(currDna, value + 1);
            }
        }
        
        return codonCount;
    }
    
    public String getMostCommonCodon() {
        String mostCommonCodon = "";
        int mostCommonCount = 0;
        
        for (String codon : codonCount.keySet()) {
            int currCount = codonCount.get(codon);
            if (currCount > mostCommonCount) {
                mostCommonCodon = codon;
                mostCommonCount = currCount;
            } 
        }
        
        return mostCommonCodon;
    }
    
    public void printCodonCounts(int start, int end) {
        
        System.out.println("Codons with a count between " + start + " and " + end + " are:");
        System.out.println("--------------------");
        for (String codon : codonCount.keySet()) {
            int currCount = codonCount.get(codon);
            if (currCount >= start && currCount <= end) {
                System.out.println(codon + ": " + codonCount.get(codon));
            }
        }
    }
    
}
