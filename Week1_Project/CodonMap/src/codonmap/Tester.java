/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codonmap;

import edu.duke.FileResource;
import java.util.HashMap;

/**
 *
 * @author el_mo
 */
public class Tester {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // buildCodonMap() TESTER
        CodonMap cm = new CodonMap();
        
        FileResource fr = new FileResource();
        
        String dna = fr.asString().trim();
        
        int[] offsets = new int[]{0,1,2};

        for (int offset : offsets) {
            HashMap<String,Integer> codons = cm.buildCodonMap(offset , dna);
            System.out.println("-------------------");
            System.out.println("-------------------");

            System.out.println(codons.size() + " UNIQUE CODONS IN OFFSET " + offset + ":");
            System.out.println("-------------------");

            for (String codon : codons.keySet()) {
                System.out.println(codon + ": " + codons.get(codon));
            }
            
            System.out.println("-------------------");
            System.out.println("");
            System.out.println("For this read frame, the most common codon is " + cm.getMostCommonCodon() + ".");
            System.out.println("");
            
            cm.printCodonCounts(1, 5);
            System.out.println("-------------------");
            System.out.println("-------------------");

            System.out.println("");
        }
    }
}
