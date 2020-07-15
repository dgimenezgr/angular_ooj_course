/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charactersinplay;

import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class Tester {
    public static void main(String args[]) {
        CharactersInPlay cp = new CharactersInPlay();
        cp.findAllCharacters();
        
        ArrayList<String> characters = cp.getCharacters();
        ArrayList<Integer> counts = cp.getCounts();
        
        for (int k = 0;k < characters.size(); k++) {
            if (counts.get(k) > 1) {
                System.out.println(characters.get(k) + " " + counts.get(k));
            }
        }
        
        System.out.println("");
        cp.charactersWithNumParts(10,15);
    }
}
