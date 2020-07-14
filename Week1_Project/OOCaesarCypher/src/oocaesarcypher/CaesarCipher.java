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
public class CaesarCipher {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String uCAlphabet = alphabet;
        String lCAlphabet = alphabet.toLowerCase();

        String shiftedLCAlphabet = shiftedAlphabet.toLowerCase();
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (isUpperCase(currChar)) {
                int idx = uCAlphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedAlphabet.charAt(idx);
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
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        
        String decrypted = cc.encrypt(input);
        
        return decrypted;
    }
}
