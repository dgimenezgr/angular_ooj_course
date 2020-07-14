/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oocaesarcypher;

import edu.duke.FileResource;

/**
 *
 * @author daniel
 */
public class TestCaesarCipher {
    
    public static int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        
        int[] counts = new int[26];
        
        for (int k=0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
                
        return counts;
    };
    
    public static String breakCaesarCipher(String encrypted) {
        
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey =  maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex );
        }
        
        System.out.println("Extracted key: " + dkey);
        
        CaesarCipher cc = new CaesarCipher(dkey);

        return cc.decrypt(encrypted);
    }

    public static int maxIndex(int[] vals) {
        int maxDex = 0;
        
        for (int k=0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }        
        }
        
        return maxDex;
    }

    public static void main(String[] args) {
        FileResource resource = new FileResource();
        
        String input = resource.asString();
        
        System.out.println("Input: " + input);

        int key = 15;
        
        CaesarCipher cc = new CaesarCipher(key);
        
        String encrypted = cc.encrypt(input);
        String decrypted = cc.decrypt(encrypted);
        
        System.out.println("Encrypted string: " + encrypted);
        System.out.println("Decrypted string: " + decrypted);
        
        System.out.println("BreakCaesarCipher test: " + breakCaesarCipher(encrypted));
    }
}
