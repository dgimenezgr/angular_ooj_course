/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oocaesarcypher;

/**
 *
 * @author el_mo
 */
public class Simple {
    private String word; 
    private String phrase; 
    public Simple(int number, String w) { 	
        word = w; 	
        phrase = mystery(number, w);          
    }   
    public String mystery(int num, String s) {  	
        String answer = "";  	
        for (int k=0; k<num; k++) {     	
            answer = answer + s;  	
        }  	
        return answer; 
    } 

    public String toString() { 	
         return phrase + " is " + word + " repeated";
    } 
}
