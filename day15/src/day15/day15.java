package day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class day15 {

    public static void main(String[] args) throws IOException {
	
	    //PartII
	HashMap<Integer, LinkedHashMap<String, Integer>> boxes = new HashMap<Integer, LinkedHashMap<String, Integer>>();
	
	try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
	    String line = br.readLine();
	    int totalSum = 0;
	    while(line != null) {
	        String[] snippets = line.split(",");
	        for (int i = 0; i < snippets.length; i++) {
	            int hashValue = hasher(snippets[i]);
	            totalSum += hashValue;
	            String label = label(snippets[i]);
	            int hashValueII = hasher(label);
	            // = Setzen oder ersetzen der Werte
	            if (snippets[i].contains("=")) {
	        	int focalLength = focalLength(snippets[i]);
	        	
	        	if (boxes.get(hashValueII) == null)  {
	        	    LinkedHashMap<String, Integer> dummy = new LinkedHashMap<>();
	        	    dummy.put(label, focalLength);
	        	    boxes.put(hashValueII, dummy);
	        	}
	        	else {
	        	    if(boxes.get(hashValueII).containsKey(label) == true) {
	        		boxes.get(hashValueII).put(label, focalLength);
	        	    }
	        	    else {
	        		boxes.get(hashValueII).put(label, focalLength);
	        	    }   
	        	}
	            }
	            // - (Entferenen der Werte)
	            else {
	        	if (boxes.get(hashValueII) != null && boxes.get(hashValueII).containsKey(label) == true) {
	        	    boxes.get(hashValueII).remove(label);
	        	}
	            }
	            
	        }
	        line = br.readLine();
	    }
	    
	    System.out.println("Resultat PartI: " + totalSum);
	    System.out.println(countingLens(boxes));
	}

    }
    
    public static int hasher(String word) {
	int summe = 0;
	for (int i = 0; i < word.length(); i++) {
	    summe = (summe + word.charAt(i))*17%256;
	}
	return summe;
    }
    
    public static int focalLength(String word) {
	String digit = word.split("=")[1];
	return Integer.parseInt(digit);
    }
    
    public static String label(String word) {
	if (word.contains("-")) {
	    return word.split("-")[0];
	}
	else {
	    return word.split("=")[0];
	}
    }
    
    public static int countingLens(HashMap<Integer, LinkedHashMap<String, Integer>> boxes) {
	int totalSum = 0;
	for(Entry<Integer, LinkedHashMap<String, Integer>> entry : boxes.entrySet()) {
	    System.out.print(entry);
	    int counter = 1;
	    for (Map.Entry<String, Integer> entryII : entry.getValue().entrySet()) {
		totalSum += entryII.getValue()*counter*(entry.getKey()+1);
		counter++;
	    }
	    System.out.println();
	}
	return totalSum;
    }

}
