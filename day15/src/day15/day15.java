package day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day15 {

    public static void main(String[] args) throws IOException {
	try (BufferedReader br = new BufferedReader(new FileReader("sample.txt"))) {
	    String line = br.readLine();
	    int totalSum = 0;
	    while(line != null) {
	        String[] snippets = line.split(",");
	        System.out.println(snippets[0]);
	        for (int i = 0; i < snippets.length; i++) {
	    	totalSum += (hasher(snippets[i]));
	        }
	        line = br.readLine();
	    }
	    
	    System.out.print("Resultat PartI: " + totalSum);
	}

    }
    
    public static int hasher(String word) {
	int summe = 0;
	for (int i = 0; i < word.length(); i++) {
	    summe = (summe + word.charAt(i))*17%256;
	}
	return summe;
    }

}
