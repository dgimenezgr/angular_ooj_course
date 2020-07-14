/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oocaesarcypher;

import edu.duke.*;
import static java.lang.Character.*;

/**
 *
 * @author daniel
 */
public class OOCaesarCypher {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public OOCaesarCypher(int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        int mainKey = key;
    }
    
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String uCAlphabet = alphabet;
        String shiftedUCAlphabet = uCAlphabet.substring(key) + uCAlphabet.substring(0,key);
        
        String lCAlphabet = alphabet.toLowerCase();
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
}
