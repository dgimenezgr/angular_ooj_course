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
public class TestCaesarCipherTwo {

    public static String halfOfString(String message, int start) {
        
        String halfMessage = "";
        
        for (int k = start; k <= message.length();k+=2) {
            halfMessage += message.charAt(k);
        }
        
        return halfMessage;        
    }
    
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
    
    public static int maxIndex(int[] vals) {
        int maxDex = 0;
        
        for (int k=0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }        
        }
        
        return maxDex;
    }

    public static int getKey(String encrypted) {
        
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int key =  maxDex - 4;
        
        if (maxDex < 4) {
            key = 26 - (4 - maxDex );
        }
        
        return key;
    }
    
    public static String breakCaesarCipher(String input) {

        StringBuilder encrypted = new StringBuilder(input);
        
        String decrypted1 = "";
        String decrypted2 = "";
        
        for (int i = 0; i < encrypted.length(); i++) {
            if ((i + 1) % 2 == 1) {
                decrypted1 = decrypted1 + encrypted.charAt(i);
            } else if ((i + 1) % 2 == 0) {
                decrypted2 = decrypted2 + encrypted.charAt(i);
            }
        }
        
        int key1 = getKey(decrypted1);
        int key2 = getKey(decrypted2);
        
        System.out.println("KEY 1: " + key1);
        System.out.println("KEY 2: " + key2);

        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);

        return cc.decrypt(input);
    }
    
    public static void main(String[] args) {
        
        FileResource resource = new FileResource();
        
        String input = resource.asString();
        
        int key1 = 14;
        
        int key2 = 24;
        
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        
        String encrypted = cc.encrypt(input);
        
        String odecrypted = cc.decrypt(input);
        
        String decrypted = cc.decrypt(encrypted);
        
        System.out.println("Encrypted string: " + encrypted);
        System.out.println("Originally decrypted string: " + odecrypted);
        System.out.println("Decrypted string: " + decrypted);
        
        System.out.println("BreakCaesarCipher test: " + breakCaesarCipher(encrypted));

    }

}
