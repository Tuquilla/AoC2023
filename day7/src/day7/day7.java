package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;


public class day7 {

	public static void main(String[] args) {
		LinkedHashSet<Character> cards = new LinkedHashSet<>();
		cards.add('2');
		cards.add('3');
		cards.add('4');
		cards.add('5');
		cards.add('6');
		cards.add('7');
		cards.add('8');
		cards.add('9');
		cards.add('T');
		cards.add('J');
		cards.add('Q');
		cards.add('K');
		cards.add('A');
		
		ArrayList<Character> alist = new ArrayList<>(cards);
		
		ArrayList<ArrayList<ArrayList<Character>>> overall = new ArrayList<>();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("sample.txt"));
			String line = br.readLine();
			while(line != null) {
				ArrayList<Character> blatt = new ArrayList<>();
				System.out.println(line);
				line = line.split(" ")[0];
				for (int i = 0; i < line.length(); i++) {
					blatt.add(line.charAt(i));
				}
				System.out.println(blatt);
				ArrayList<ArrayList<Character>> pair = new ArrayList<>();
				ArrayList<Character> strength = new ArrayList<>();
				strength.add(Character.forDigit(frequencyCheck(blatt, cards),10));
				pair.add(strength);
				pair.add(blatt);
				overall.add(pair);
				
				
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Auswertung Part I
		for (int i = 0; i < overall.size(); i++) {
			System.out.print(overall.get(i).get(0) + " " + overall.get(i).get(1));
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < overall.size(); i++) {
			for (int j = 0; j < overall.size()-1; j++) {
				if(overall.get(j).get(0).get(0) < overall.get(j+1).get(0).get(0)) {
					char bigger = overall.get(j).get(0).get(0);
					ArrayList<Character> biggerArr = overall.get(j).get(1);
					char smaller = overall.get(j+1).get(0).get(0);
					ArrayList<Character> smallerArr = overall.get(j+1).get(1);
					overall.get(j).get(0).set(0, smaller);
					overall.get(j).set(1, smallerArr);
					overall.get(j+1).get(0).set(0, bigger);
					overall.get(j+1).set(1, biggerArr);
				}
				else if (overall.get(j).get(0).get(0) == overall.get(j+1).get(0).get(0)) {
					for (int v = 0; v < overall.get(j).get(1).size(); v++) {
						if (alist.indexOf(overall.get(j).get(1).get(v)) > alist.indexOf(overall.get(j+1).get(1).get(v))) {
							
						}
					}
				}
			}
		}
		for (int i = 0; i < overall.size(); i++) {
			System.out.print(overall.get(i).get(0) + " " + overall.get(i).get(1));
			System.out.println();
		}
	}
	
	public static int frequencyCheck(ArrayList<Character> blatt, LinkedHashSet<Character> cards) {
		ArrayList<Character> type = new ArrayList<>();
		for(Character c : cards) {
			int frequenz = Collections.frequency(blatt, c);
			type.add(Character.forDigit(frequenz, 10));
		}
		if (type.contains('5')) {
			//Full House
			return 6;
		}
		else if (type.contains('4')) {
			//Vierling
			return 5;
		}
		else if (type.contains('3')) {
			if (type.contains('2')) {
				//Full House
				return 4;
			}
			else {
				//Drilling
				return 3;
			}
		}
		else if (type.contains('2')) {
				//Zwei Paar
			if (Collections.frequency(type, '2') == 2) {
				return 2;
			}
			else {
				//Ein Paar
				return 1;
			}
		}
		else {
			//Einzelkarte
			return 0;
		}
	}

}
