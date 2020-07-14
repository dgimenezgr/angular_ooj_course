/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesarcypher;

import static caesarcypher.CaesarCypher.encrypt;
import edu.duke.FileResource;

/**
 *
 * @author daniel
 */
public class WordPlay {
    /**
     * @param args the command line arguments
     */
    
    public static Boolean isVowel(char ch) {
        Boolean isVowel = false;
        
        String vowelDict = "AEIOUaeiou";
        
        if (vowelDict.indexOf(String.valueOf(ch)) != -1) {
            isVowel = true;
        }
        
        return isVowel;
    }
    
    public static String replaceVowels(String phrase, char ch) {
        
        StringBuilder phraseBuilder = new StringBuilder(phrase);
        
        for (int i = 0; i < phraseBuilder.length(); i++) {
            char currChar = phraseBuilder.charAt(i);
            
            if (isVowel(currChar)) {
                phraseBuilder.setCharAt(i, ch);
            }

        }
        
        return phraseBuilder.toString();
    }
    
    public static String emphasize(String phrase, char ch) {
        StringBuilder phraseBuilder = new StringBuilder(phrase);
        
        for (int i = 0; i < phraseBuilder.length(); i++) {
            char currChar = phraseBuilder.charAt(i);
            
            if (Character.toLowerCase(currChar) == ch) {
                if ((i+1) % 2 == 1) {
                    phraseBuilder.setCharAt(i, '*');
                } else {
                    phraseBuilder.setCharAt(i, '+');
                }
            }
        }
        
        return phraseBuilder.toString();
    }

    public static void main(String[] args) {
        // Test: isVowel
        // System.out.println(isVowel('a'));

        // Test: replaceVowels
        // System.out.println(replaceVowels("ThE swIft brown fox", '*'));
        
        // Test: emphasize
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

}
