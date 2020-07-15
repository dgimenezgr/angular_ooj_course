/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charactersinplay;

import edu.duke.FileResource;
import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class CharactersInPlay {

    private ArrayList<String> characters;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    private void update(String person) {

        int index = characters.indexOf(person);

        if (index == -1) {
            characters.add(person);
            counts.add(1);
        } else {
            int value = counts.get(index);
            counts.set(index,value+1);
        }
    }
    
    public void findAllCharacters() {
        characters.clear();
        counts.clear();
        
        FileResource resource = new FileResource();
        
        for (String line : resource.lines()) {
            if (line.indexOf(".") != -1) {
                line = line.substring(0, line.indexOf("."));
                update(line);
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("Characters with " + num1 + " or more and " + num2 + " or less speaking parts.");
        System.out.println("-----------------------------------------------------------------------------");
        
        for (int k = 0; k < characters.size(); k++) {
            if (counts.get(k) >= num1 && counts.get(k) <= num2) {
                System.out.println(characters.get(k));
            }        
        }
    }
    
    public ArrayList<String> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<String> characters) {
        this.characters = characters;
    }

    public ArrayList<Integer> getCounts() {
        return counts;
    }

    public void setCounts(ArrayList<Integer> counts) {
        this.counts = counts;
    }
    
}
