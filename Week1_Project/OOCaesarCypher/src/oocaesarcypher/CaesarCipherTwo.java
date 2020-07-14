/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oocaesarcypher;

import static java.lang.Character.isUpperCase;

/**
 *
 * @author daniel
 */
public class CaesarCipherTwo {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String lcAlphabet = alphabet.toLowerCase();
        
        String shiftedLCAlphabet1 = shiftedAlphabet1.toLowerCase();
        String shiftedLCAlphabet2 = shiftedAlphabet2.toLowerCase();
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i % 2 == 0) {
                if (isUpperCase(currChar)) {
                    int idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                } else {
                    int idx = lcAlphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedLCAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            } else if (i % 2 == 1) {
                if (isUpperCase(currChar)) {
                    int idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                } else {
                    int idx = lcAlphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedLCAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        
        return encrypted.toString();
    }
    
    public String decrypt(String input) {

        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);

        return cc.encrypt(input);
    }
}
