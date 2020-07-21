/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenerecipher;

import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slices = new StringBuilder(message);
        
        String slice = "";
        
        for (int k = whichSlice; k  < slices.length(); k += totalSlices) {
            slice = slice + slices.charAt(k);
        }
        
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        
        // User sliceString to create one slice for each index in the key
        for (int k = 0; k < klength; k++) {
            String thisSlice = sliceString(encrypted, k, klength);
            int thisKey = cc.getKey(thisSlice);
            
            key[k] = thisKey;
        }

        return key;
    }

//    public String breakVigenere(String encrypted, int keylength, char mostCommon) {
//        int[] thisKey = tryKeyLength(encrypted, keylength, 'e');
//        
//        VigenereCipher vc = new VigenereCipher(thisKey);
//        
//        String decrypted = vc.decrypt(encrypted);
//
//        return decrypted;
//        
//    }
    
    public String breakVigenere() {
        FileResource encrFile = new FileResource();
        String encrypted = encrFile.asString();
        
        // Make HashMap of dictionaries
        HashMap dictionaries = new HashMap<String,HashSet<String>>();
        DirectoryResource ddr = new DirectoryResource();
        
        for (File f : ddr.selectedFiles()) {
            FileResource thisDFr = new FileResource(f);
            HashSet<String> dictionary = readDictionary(thisDFr);
            dictionaries.put(f.getName().toLowerCase(),dictionary);            
        }

//        FileResource dictFile = new FileResource("dictionaries/English");
//        HashSet dictionary = readDictionary(dictFile);
//        
//        String decrypted = breakForLanguage(encrypted, dictionary);

        String decrypted = breakForAllLangs(encrypted, dictionaries);
        
        return decrypted;
        
    }

    public HashSet readDictionary(FileResource fr){
        HashSet dictionary = new HashSet();
        
        for (String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
    
        return dictionary;
    }
    
    public int countWords(String message, HashSet dictionary) {
        int realWords = 0;
        String[] words = message.split("\\W+");
        
        for (int k = 0; k < words.length; k++) {
            if (dictionary.contains(words[k].toLowerCase())) {
                realWords++;            
            }
        }
        
        return realWords;
    }
    
    public String breakForLanguage(String encrypted, HashSet dictionary) {
        int firstKeyLength = 1;
        int lastKeyLength = 100;
        
        int highestKeyLength = 0;
        int mostRealWords = 0;
        String decrypted = "";
        
        char thisMostCommonChar = mostCommonCharIn(dictionary);
        
        for (int k = firstKeyLength; k < lastKeyLength; k++) {
            int[] thisKey = tryKeyLength(encrypted, k, thisMostCommonChar);
//            int[] thisKey = tryKeyLength(encrypted, 38, 'e');
            VigenereCipher vc = new VigenereCipher(thisKey);

            String thisDecryption = vc.decrypt(encrypted);
            int thisRealWords = countWords(thisDecryption, dictionary);

            if (thisRealWords > mostRealWords) {
                mostRealWords = thisRealWords;
                decrypted = thisDecryption;
                highestKeyLength = k;
            }
        }
        
        System.out.println("Keylength used: " + highestKeyLength);
        
        System.out.println("Valid words: " + mostRealWords);
                
        return decrypted;
    }
 
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<String,Integer> letterFreqs = new HashMap<String,Integer>();
        int mostUsedFreq = 0;
        
        char mostCommonChar = ' ';
        
        for (String thisWord : dictionary) {
            for (int i = 0; i < thisWord.length(); i++) {
                char thisChar = thisWord.charAt(i);
                String thisCharStr = String.valueOf(thisChar).toLowerCase();
                if (!letterFreqs.containsKey(thisCharStr)) {
                    letterFreqs.put(thisCharStr,1);
                } else {
                    int currValue = letterFreqs.get(thisCharStr);
                    int newValue = currValue + 1;
                    letterFreqs.put(thisCharStr, newValue);
                }   
            }
        }
        
        for (String letter : letterFreqs.keySet()) {
            if (letterFreqs.get(letter) > mostUsedFreq) {
                mostUsedFreq = letterFreqs.get(letter);
                mostCommonChar = letter.charAt(0);
            }
        }
        
        return mostCommonChar;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages) {
        String realDecrypted = null;
        int realWords = 0;
        
        for (String lang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int currRealWords = countWords(encrypted, dictionary);
            if (currRealWords > realWords) {
                realWords = currRealWords;
                realDecrypted = decrypted;
            }
        }
                
        return realDecrypted;
    }
}
