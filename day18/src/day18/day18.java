package day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day18 {

	public record Coord(int x, int y) {};
	
	public static void main(String[] args) throws IOException {
		
		char[][] ground = new char[100][100];
		for (int i = 0; i < ground.length; i++) {
			for(int j = 0; j < ground[i].length; j++) {
				ground[i][j] = '.';
			}
		}
		BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
		String line = br.readLine();
		
		while(line != null) {
			String[] pattern = line.split(" ");
			System.out.println(line);
			line = br.readLine();
		}

	}
	
	public static void outline(char[][] ground, char direction, int length, int x, int y) {
		
	}

}
