/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gladlibmap;

import edu.duke.FileResource;
import edu.duke.URLResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author daniel
 */
public class GladLibMap {

        private ArrayList<String> usedList;
	
        private HashMap<String,ArrayList<String>> myMap;
        
	private Random myRandom;
        
        private ArrayList<String> myUsedLabels;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
                myMap = new HashMap<String,ArrayList<String>>();
                myUsedLabels = new ArrayList<String>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
                myMap = new HashMap<String,ArrayList<String>>();
                myUsedLabels = new ArrayList<String>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
                myMap.clear();
            
                String[] categories = new String[]{"adjective", "noun", "color", "country", "name", "animal", "verb", "fruit", "timeframe"};
                
                for (int k = 0; k < categories.length; k++) {
                    ArrayList<String> tmp = readIt(source+"/" + categories[k] + ".txt");
                    String category = categories[k];
                    myMap.put(category, tmp);
                }
                
                usedList = new ArrayList<String>();
	}
	
	private String randomFrom(ArrayList<String> source){
            int index = myRandom.nextInt(source.size());
            return source.get(index);
	}
	
	private String getSubstitute(String label) {
            ArrayList<String> category = myMap.get(label);
            
            if (myUsedLabels.indexOf(label) == -1) {
                myUsedLabels.add(label);
            }
            
            if (label.equals("number")) {
                return ""+myRandom.nextInt(50)+5;
            } else if (!label.equals("number")) {
                return randomFrom(category);
            } else {
                return "**UNKNOWN**";
            }
	}
	
	private String processWord(String w){
            int first = w.indexOf("<");
            int last = w.indexOf(">",first);
            if (first == -1 || last == -1){
                    return w;
            }
            String prefix = w.substring(0,first);
            String suffix = w.substring(last+1);
            String sub = getSubstitute(w.substring(first+1,last));
            
            while (usedList.indexOf(sub) != -1) {
                sub = getSubstitute(w.substring(first+1,last));
            }

            usedList.add(sub);
            
            return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
        private void totalWordsInMap() {
            int totalWordsInMap = 0;
            for (String key : myMap.keySet()) {
                totalWordsInMap += myMap.get(key).size();
            }
            
            System.out.println("");
            System.out.println("There were a total of " + totalWordsInMap + " possible words.");
        }
        
        private int totalWordsConsidered() {
            int totalWordsConsidered = 0;
            
            for (int k = 0; k < myUsedLabels.size(); k++) {
                String thisLabel = myUsedLabels.get(k);

                if (!thisLabel.equals("number")) {
                    ArrayList<String> thisCategory = myMap.get(thisLabel);
                    int thisCategoryWords = thisCategory.size();
                    totalWordsConsidered += thisCategoryWords;
                }
            }
            
            return totalWordsConsidered;
        }
        
	public void makeStory(){
            usedList.clear();
            myUsedLabels.clear();
            
	    System.out.println("\n");
            String story = fromTemplate("data/madtemplate.txt");
            printOut(story, 60);
	    System.out.println("\n");
            System.out.println(usedList.size() + " words were replaced.");
            totalWordsInMap();
            System.out.println(totalWordsConsidered() + " words were considered.");
        }
    
}
