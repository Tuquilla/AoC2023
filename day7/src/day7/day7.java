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
		cards.add('J'); // Part II 
		cards.add('2');
		cards.add('3');
		cards.add('4');
		cards.add('5');
		cards.add('6');
		cards.add('7');
		cards.add('8');
		cards.add('9');
		cards.add('T');
		//cards.add('J'); // Part I 
		cards.add('Q');
		cards.add('K');
		cards.add('A');
		
		ArrayList<Character> alist = new ArrayList<>(cards);
		
		ArrayList<ArrayList<ArrayList<Character>>> overall = new ArrayList<>();
		String sample = "sample.txt";
		String full = "input.txt";
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(full));
			String line = br.readLine();
			while(line != null) {
				ArrayList<Character> blatt = new ArrayList<>();
				System.out.println(line);
				String lineSplit = line.split(" ")[1];
				line = line.split(" ")[0];
				for (int i = 0; i < line.length(); i++) {
					blatt.add(line.charAt(i));
				}
				System.out.println(blatt);
				ArrayList<ArrayList<Character>> pair = new ArrayList<>();
				ArrayList<Character> strength = new ArrayList<>();
				ArrayList<Character> wert = new ArrayList<>();
				strength.add(Character.forDigit(frequencyCheck(blatt, cards),10));
				pair.add(strength);
				pair.add(blatt);
				
				for (int i = 0; i < lineSplit.length(); i++) {
					wert.add(lineSplit.charAt(i));
				}
				
				pair.add(wert);
				overall.add(pair);
				
				
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Auswertung Part I
		System.out.println();
		for (int i = 0; i < overall.size(); i++) {
			for (int j = 0; j < overall.size()-1; j++) {
				if(overall.get(j).get(0).get(0) > overall.get(j+1).get(0).get(0)) {
					ArrayList<ArrayList<Character>> biggerArr = overall.get(j);
					ArrayList<ArrayList<Character>> smallerArr = overall.get(j+1);
					overall.set(j, smallerArr);
					overall.set(j+1, biggerArr);
				}
				else if (overall.get(j).get(0).get(0) == overall.get(j+1).get(0).get(0)) {
					for (int v = 0; v < overall.get(j).get(1).size(); v++) {
						if (alist.indexOf(overall.get(j).get(1).get(v)) < alist.indexOf(overall.get(j+1).get(1).get(v))) {
							ArrayList<ArrayList<Character>> biggerArr = overall.get(j);
							ArrayList<ArrayList<Character>> smallerArr = overall.get(j+1);
							overall.set(j+1, smallerArr);
							overall.set(j, biggerArr);
							break;
						}
						else if (alist.indexOf(overall.get(j).get(1).get(v)) > alist.indexOf(overall.get(j+1).get(1).get(v))) {
							ArrayList<ArrayList<Character>> biggerArr = overall.get(j);
							ArrayList<ArrayList<Character>> smallerArr = overall.get(j+1);
							overall.set(j, smallerArr);
							overall.set(j+1, biggerArr);
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < overall.size(); i++) {
			System.out.print(overall.get(i).get(0) + " " + overall.get(i).get(1) + " " + overall.get(i).get(2));
			System.out.println();
		}
		
		//Caluclation Part1
		int Resultat = 0;
		for (int i = 0; i < overall.size(); i++) {
			String zwischenRes = "";
			for (int v = 0; v < overall.get(i).get(2).size(); v++) {
				zwischenRes += overall.get(i).get(2).get(v);
			}
			Resultat += ((i+1) * Integer.parseInt(zwischenRes));
		}
		System.out.println("Total: " + Resultat);
	}
	
	public static int frequencyCheck(ArrayList<Character> blatt, LinkedHashSet<Character> cards) {
		ArrayList<Character> type = new ArrayList<>();
		for(Character c : cards) {
			int frequenz = Collections.frequency(blatt, c);
			type.add(Character.forDigit(frequenz, 10));
		}
		int returnValue = 0;
		if (type.contains('5')) {
			//Full House
			returnValue = 6;
		}
		else if (type.contains('4')) {
			//Vierling
		    	if (Collections.frequency(blatt, 'J') >= 1) {
		    	    returnValue = 6;
		    	}
		    	else {
		    	    returnValue = 5;
		    	}
		}
		else if (type.contains('3')) {
			if (type.contains('2')) {
			    //Full House
			    if (Collections.frequency(blatt, 'J') >= 2) {
				returnValue = 6;
			    }
			    else {
				returnValue =  4;
			    }
			}
			else {
			    //Drilling
			    if (Collections.frequency(blatt, 'J') == 1) {
				returnValue = 5;
			    }
			    //Ist überflüssig
			    else if (Collections.frequency(blatt, 'J') == 2) {
				returnValue = 6;
			    }
			    else if (Collections.frequency(blatt, 'J') == 3) {
				returnValue = 5;
			    }
			    else {
				returnValue = 3;
			    }
			}
		}
		else if (type.contains('2')) {
		    	//Zwei Paar
			if (Collections.frequency(type, '2') == 2) {
			    if (Collections.frequency(blatt, 'J') == 2) {
				returnValue = 5;
			    }
			    else {
				if (Collections.frequency(blatt, 'J') == 1) {
				    returnValue = 4;
				}
				else {
				    returnValue = 2;
				}
			    }
			}
			else {
			    //Ein Paar
			    if (Collections.frequency(blatt, 'J') == 2) {
				returnValue = 3;
			    }
			    else if (Collections.frequency(blatt, 'J') >= 1) {
				returnValue = 3;
			    }
			    else {
				returnValue =  1;
			    }
			}
		}
		else {
			//Einzelkarte
		    	if (Collections.frequency(blatt, 'J') == 1) {
		    	    returnValue = 1;
		    	}
		    	else {
		    	    returnValue = 0;
		    	}
		}
		return returnValue;
	}

}
