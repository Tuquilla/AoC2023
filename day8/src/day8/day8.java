package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class day8 {

    public static void main (String[] args) throws IOException {
	
	BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	String line = br.readLine();
	String RLFolge = "";
	LinkedHashMap<String, ArrayList<String>> container = new LinkedHashMap<>();
	int counter = 0;
	while (line != null) {
	    
	    if (counter == 0) {
		RLFolge = line;
	    }
	    if (counter > 1) {
		ArrayList<String> both = new ArrayList<>();
		String[] values = line.split(" = ");
		String leftV = values[1].substring(1,4);
		String rightV = values[1].substring(6, 9);
		both.add(leftV);
		both.add(rightV);
		container.put(values[0], both);
	    }
	    counter++;
	    line = br.readLine();
	}
	
	String firstKey = "AAA";
	String secondKey = firstKey;
	int stringLength = RLFolge.length();
	int start = 0;
	int resultat = 1;
	//System.out.println("Resultat: " + searchSteps(container, firstKey, secondKey, start, resultat, RLFolge));
	
	
	List<String> keys = new ArrayList<>();
	keys = container.entrySet().parallelStream().filter(entry -> entry.getKey().charAt(2) == 'A').map(Map.Entry::getKey).collect(Collectors.toList());
	System.out.println(keys);
	ArrayList<Integer> resArr = new ArrayList<>();
	for (int i = 0; i < keys.size(); i++) {
	    secondKey = keys.get(0);
	    resArr.add(searchStepsII(container, keys.get(i), secondKey, start, resultat, RLFolge));
	}
	System.out.println(resArr);
	
	long zaehler = 1;
	boolean repeater = true;
	while(repeater) {
	    int plusCounter = 0;
	    for(int i = 0; i < resArr.size(); i++) {
		if (zaehler%resArr.get(i) == 0) {
		    plusCounter++;
		}
	    }
	    if (plusCounter == resArr.size()) {
		repeater = false;
		System.out.println("kgV und Somit PartII: " + zaehler);
	    }
	    zaehler++;
	}
    }
    
    public static int searchStepsII(LinkedHashMap<String, ArrayList<String>> container, String firstKey, String secondKey, int start, int resultat, String RLFolge) {
	while(true) {
	    List<ArrayList<String>> liste = new ArrayList<>();
	    //Try with Streams
	    if (RLFolge.charAt(start) == 'L') {
		final String firstKeyII = firstKey;
		liste = container.entrySet().parallelStream().filter(entry -> entry.getKey().equals(firstKeyII)).map(Map.Entry::getValue).collect(Collectors.toList());
		firstKey = liste.get(0).get(0);
	    }
	    else {
		final String firstKeyII = firstKey;
		liste = container.entrySet().parallelStream().filter(entry -> entry.getKey().equals(firstKeyII)).map(Map.Entry::getValue).collect(Collectors.toList());
		firstKey = liste.get(0).get(1);
	    }
	    
	    if (firstKey.charAt(2) == 'Z') {
		break;
	    }
	    
	    resultat++;
	    start++;
	    if (start >= RLFolge.length()) {
		start = 0;
	    }    
	    
	    liste.clear();
	}
	return resultat;
    }
    public static int searchSteps(LinkedHashMap<String, ArrayList<String>> container, String firstKey, String secondKey, int start, int resultat, String RLFolge) {
	while(true) {
	    List<ArrayList<String>> liste = new ArrayList<>();
	    //Try with Streams
	    if (RLFolge.charAt(start) == 'L') {
		final String firstKeyII = firstKey;
		liste = container.entrySet().parallelStream().filter(entry -> entry.getKey().equals(firstKeyII)).map(Map.Entry::getValue).collect(Collectors.toList());
		firstKey = liste.get(0).get(0);
	    }
	    else {
		final String firstKeyII = firstKey;
		liste = container.entrySet().parallelStream().filter(entry -> entry.getKey().equals(firstKeyII)).map(Map.Entry::getValue).collect(Collectors.toList());
		firstKey = liste.get(0).get(1);
	    }
	    
	    if (firstKey.equals("ZZZ")) {
		break;
	    }
	    
	    resultat++;
	    start++;
	    if (start >= RLFolge.length()) {
		start = 0;
	    }    
	    
	    liste.clear();
	}
	return resultat;
    }
}
