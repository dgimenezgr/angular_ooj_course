/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordfrequencies;

import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author daniel
 */
public class WordFrequencies {

    ArrayList<String> myWords;
    ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public ArrayList findUnique() {
        FileResource resource = new FileResource();
        
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
        
        return myWords;
    }

    public int findIndexOfMax() {
        int maxCount = Collections.max(myFreqs);
        int maxCountIdx = myFreqs.indexOf(maxCount);

        return maxCountIdx;
    }
    
    public ArrayList<String> getMyWords() {
        return myWords;
    }

    public void setMyWords(ArrayList<String> myWords) {
        this.myWords = myWords;
    }

    public ArrayList<Integer> getMyFreqs() {
        return myFreqs;
    }

    public void setMyFreqs(ArrayList<Integer> myFreqs) {
        this.myFreqs = myFreqs;
    }

}
