/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenerecipher;

import edu.duke.FileResource;
import java.util.HashSet;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author daniel
 */
public class CipherTester {
    
    public static String testCCEncrypt() {
        CaesarCipher cc = new CaesarCipher(4);
        
        // FILE INPUT
        FileResource fr = new FileResource();
        String input = fr.asString();

        return(cc.encrypt(input));
    }

    public static String testCCDecrypt(String input) {
        CaesarCipher cc = new CaesarCipher(4);

        return(cc.decrypt(input));
    }
    
    public static int testGetCBKey(String input) {
        CaesarCracker cb = new CaesarCracker();
        
        int key = cb.getKey(input);
        
        return key;
    }
    
    public static String testCBDecrypt(String input) {
        CaesarCracker cb = new CaesarCracker('a');

        String decrypted = cb.decrypt(input);
        
        return decrypted;
    }
    
    public static String testVigEncrypt() {
        
        int[] key = new int[]{17, 14, 12, 4};
        
        VigenereCipher vc = new VigenereCipher(key);

        // FILE INPUT
        FileResource fr = new FileResource();
        String input = fr.asString();

        String decrypted = vc.encrypt(input);
        
        return decrypted;
    }
    

    public static String testVigDecrypt(String input) {
        
        int[] key = new int[]{17, 14, 12, 4};
        
        VigenereCipher vc = new VigenereCipher(key);

        String decrypted = vc.decrypt(input);
        
        return decrypted;
    }
    
    
    public static String testVigSliceString(String input, int whichSlice, int totalSlices) {
        
        VigenereBreaker vb = new VigenereBreaker();

        String slice = vb.sliceString(input, whichSlice, totalSlices);
        
        return slice;
    }
    
    public static int[] testTryKeyLength() {
        
        VigenereBreaker vb = new VigenereBreaker();
        
        // FILE INPUT
        FileResource fr = new FileResource();
        String input = fr.asString();
        
        int[] key = vb.tryKeyLength(input, 38, 'e');
        
        return key;
    }
    
    public static void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();

        System.out.println(vb.breakVigenere());
    }
    
    public static void testReadDictionary() {
        FileResource dictFile = new FileResource("dictionaries/English");
        
        VigenereBreaker vb = new VigenereBreaker();
        
        HashSet dictionary = vb.readDictionary(dictFile);
        
//        for (int k = 0; k < dictionary.size(); k++) {
            System.out.println(dictionary);
//        }
    }
    
    public static void main(String[] args) {
        
        // TEST ENCRYPT - DO NOT COMMENT OUT
//        String encrypted = testCCEncrypt();
//        System.out.println("Caesar Cipher Encrypt test: ");
//        System.out.println(encrypted);
        ////////////////
        
//        System.out.println("--------------------------------");
                
//        // TEST DECRYPT
//        String decrypted = testCCDecrypt(encrypted);
//        System.out.println("Caesar Cipher Decrypt test: ");
//        System.out.println(decrypted);
//        ////////////////
//        
//        System.out.println("--------------------------------");
                
//        // TEST DECRYPT CAESARBREAKER
//        String decrypted = testCBDecrypt(encrypted);
//        System.out.println("Caesar Breaker Decrypt test: ");
//        System.out.println(decrypted);
//        ////////////////
        
//        System.out.println("--------------------------------");
//        
//        // TEST ENCRYPT VIGENERES
//        String encrypted = testVigEncrypt();
//        System.out.println("Vigeneres Cipher Encrypt test: ");
//        System.out.println(encrypted);
//        ////////////////
//        
//        System.out.println("--------------------------------");
//        
//        // TEST DECRYPT VIGENERES
//        String decrypted = testVigDecrypt(encrypted);
//        System.out.println("Vigeneres Breaker Decrypt test: ");
//        System.out.println(decrypted);
//        ////////////////
//        
//        System.out.println("--------------------------------");
        
        // TEST VIGENERES SliceString
//        String vigInput = "abcdefghijklm";
//        int whichSlice = 2;
//        int totalSlices = 5;
//        System.out.println("Vigeneres Slice String test: ");
//        System.out.println(testVigSliceString(vigInput, whichSlice, totalSlices));
//        ////////////////

//        System.out.println("--------------------------------");

        // TEST VIGENERES tryKeyLength
        
//        int[] keyLength = testTryKeyLength();
//
//        System.out.println("Vigeneres Try Key Length: ");
//        
//        System.out.println(keyLength.length);
//        
//        for (int i = 0; i < keyLength.length; i++) {
//            System.out.println(keyLength[i]);
//        }
//        ////////////////

//        System.out.println("--------------------------------");

        // TEST BreakVigenere
        System.out.println("Vigeneres Test Break: ");
        testBreakVigenere();

        // TEST readDictionary
//        System.out.println("Read Dictionary TEST: ");
//        testReadDictionary();
    }
}
