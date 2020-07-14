/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesarcypher;

import static caesarcypher.CaesarCypher.*;
import edu.duke.FileResource;

/**
 *
 * @author daniel
 */
public class CaesarBreaker {
    
    public static String[] getCommon() {
        FileResource resource = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for (String s : resource.words()) {
            common[index] = s;
            index += 1;
        }
        
        return common;
    }
    
    public static int indexOf(String[] list, String word) {
        for (int k=0; k<list.length; k++) {
            if (list[k].equals(word)) {
                return k;
            }
        }
        return 1;
    }
    
    public static void countWords(FileResource resource, String[] common, int[] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = indexOf(common,word);
            if (index != -1) {
                counts[index] += 1;            
            }
        }
    }
    
    public static void countShakespeare() {
        String[] plays = {};
        
        String[] common = getCommon();
        
        int[] counts = new int[common.length];
        
        for (int k=0; k < plays.length; k++) {
            FileResource resource = new FileResource("data/" + plays[k]);
            countWords(resource,common,counts);
            System.out.println("Done with " + plays[k]);
        }
        
        for (int k=0; k < common.length; k++) {
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
    
    public static String decrypt(String encrypted) {
        CaesarCypher cc = new CaesarCypher();
        
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey =  maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex );
        }
//        for (int i = 0; i < freqs.length; i++) {
//            System.out.println("FREQUENCIA " + i + ": " + freqs[i]);
//        }
                
        return cc.encrypt(encrypted, 26 - dkey);
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
    
    public static String decryptTwoKeys(String message) {
        
        CaesarCypher cc = new CaesarCypher();

        StringBuilder encrypted = new StringBuilder(message);
        
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
        
        return cc.encryptTwoKeys(encrypted.toString(), 26 - key1, 26 - key2);
        
    }
    
    public static void testEncrypt() {
        FileResource resource = new FileResource("data/wordsLotsOfEs.txt");
        
        System.out.println(encrypt(resource.asString(), 17));
    }
    
    
    public static void testEncryptTwoKeys() {
        // FileResource resource = new FileResource("data/wordsLotsOfEs.txt");
        // String message = resource.asString();
        String message = "Top ncmy qkff vi vguv vbg ycpx";
        
        System.out.println(encryptTwoKeys(message, 26 - 2, 26 - 20));
    }
    
    
    public static void testDecrypt() {
//        FileResource resource = new FileResource();
        FileResource resource = new FileResource("data/wordsLotsOfEsEncryptedCheat.txt");
        String message = resource.asString();
        
        System.out.println(decrypt(message));
    }
    
    public static void testDecryptTwoKeys() {
        FileResource resource = new FileResource("data/mysteryTwoKeysPractice.txt");
         String message = resource.asString();
//        String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        
        System.out.println(decryptTwoKeys(message));
    }

    public static void main(String[] args) {
        System.out.println("TEST ENCRYPT");
        testEncrypt();

        System.out.println("------------");
        System.out.println("");
        System.out.println("------------");
        
        System.out.println("TEST DECRYPT");
        testDecrypt();

        System.out.println("------------");
        System.out.println("");
        System.out.println("------------");
        
        System.out.println("TEST ENCRYPT TWO KEYS");
        testEncryptTwoKeys();

        System.out.println("------------");
        System.out.println("");
        System.out.println("------------");
        
        System.out.println("TEST DECRYPT TWO KEYS");
        testDecryptTwoKeys();
    }
}
