/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesarcypher;

import edu.duke.*;
import static java.lang.Character.isUpperCase;

/**
 *
 * @author daniel
 */
public class CaesarCypher {
    public static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String uCAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedUCAlphabet = uCAlphabet.substring(key) + uCAlphabet.substring(0,key);
        
        String lCAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedLCAlphabet = lCAlphabet.substring(key) + lCAlphabet.substring(0,key);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (isUpperCase(currChar)) {
                int idx = uCAlphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedUCAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                int idx = lCAlphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedLCAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        
        return encrypted.toString(); 
    }
    
    public static String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String uCAlphabet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedUCAlphabet1 = uCAlphabet1.substring(key1) + uCAlphabet1.substring(0,key1);
        
        String lCAlphabet1 = "abcdefghijklmnopqrstuvwxyz";
        String shiftedLCAlphabet1 = lCAlphabet1.substring(key1) + lCAlphabet1.substring(0,key1);
        
        String uCAlphabet2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedUCAlphabet2 = uCAlphabet1.substring(key2) + uCAlphabet1.substring(0,key2);
        
        String lCAlphabet2 = "abcdefghijklmnopqrstuvwxyz";
        String shiftedLCAlphabet2 = lCAlphabet1.substring(key2) + lCAlphabet1.substring(0,key2);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i % 2 == 0) {
                if (isUpperCase(currChar)) {
                    int idx = uCAlphabet1.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedUCAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                } else {
                    int idx = lCAlphabet1.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedLCAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            } else if (i % 2 == 1) {
                if (isUpperCase(currChar)) {
                    int idx = uCAlphabet2.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedUCAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                } else {
                    int idx = lCAlphabet2.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedLCAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        
        return encrypted.toString();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // VARS
//        FileResource fr = new FileResource();
//        String message = fr.asString();
//        String message = "FirST LegION ATTACK eaST FLanK!";
//        int key = 15;
//        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
//        int key1 = 8;
//        int key2 = 21;
//
//        // TEST: encrypt
//        
//        System.out.println("TEST: encrypt()");
//        System.out.println("---------------");
//        
//        String encrypted = encrypt(message, key);
//        System.out.println(encrypted);
//        
//        String decrypted = encrypt(encrypted, 26-key);
//        System.out.println(decrypted);
//        
//        // TEST: encryptTwoKeys()
//        String encryptedTwoKeys = encryptTwoKeys(message, key1, key2);
//
//        System.out.println("");
//        System.out.println("");
//        
//        System.out.println("TEST: encryptTwoKeys()");
//        System.out.println("---------------");
//
//        System.out.println(encryptedTwoKeys);
    }
    
}
