package day18;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class day18 {

	public record Coord(int x, int y) {};
	
	public static void main(String[] args) throws IOException {
		
		char[][] ground = new char[650][650];
		char[][] groundII = new char[650][650];
		int xStart = ground.length / 2;
		int yStart = ground[xStart].length / 2;
		int[] newCoords = {xStart, yStart};
		int[] newCoordsII = {xStart, yStart};
		for (int i = 0; i < ground.length; i++) {
			for(int j = 0; j < ground[i].length; j++) {
				ground[i][j] = '.';
			}
		}
		for (int i = 0; i < groundII.length; i++) {
			for(int j = 0; j < groundII[i].length; j++) {
				groundII[i][j] = '.';
			}
		}
		BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("blabal.txt"));
		String line = br.readLine();
		
		while(line != null) {
			String[] pattern = line.split(" ");
			System.out.println(line);
			newCoords = outline(ground, pattern[0].charAt(0), Integer.parseInt(pattern[1]), newCoords[0], newCoords[1]);
			//System.out.println(newCoords[0] + "," + newCoords[1]);
			
			//PartII
			int length = Integer.parseInt(pattern[2].substring(2, pattern[2].length()-2), 16);
			System.out.println(length);
			
			line = br.readLine();
		}
		
		for (int i = 0; i < ground.length; i++) {
			for (int j = 0; j < ground[i].length; j++) {
				bw.append(ground[i][j]);
			}
			bw.append('\n');
		}
		System.out.println("Ich bin hier");
		
			floodFill(ground, xStart+2, yStart+2);	
		int count = 0;
		for (int i = 0; i < ground.length; i++) {
			for (int j = 0; j < ground[i].length; j++) {
				//System.out.print(ground[i][j]);
				if (ground[i][j] == '#') {
					count++;
				}
			}
			//System.out.println();
		}
		
		//Count
		System.out.println("Resultat PartI: " + count);
		
	}
	
	public static void floodFill(char[][] ground, int x, int y) {
		if (ground[x][y] == '.') {
			ground[x][y] = '#';
		}
		if (ground[x][y+1] == '.') {
			floodFill(ground, x, y+1);
		}
		if (ground[x][y-1] == '.') {
			floodFill(ground, x, y-1);
		}
		if (ground[x+1][y] == '.') {
			floodFill(ground, x+1, y);
		}
		if (ground[x-1][y] == '.') {
			floodFill(ground, x-1, y);
		}
	}
	
	public static int[] outline(char[][] ground, char direction, int length, int x, int y) {
		int[] coords = {x,y};
		if (direction == 'R') {
			for (int i = y; i <= y+length; i++) {
				ground[x][i] = '#';
				coords[1] = i;
			}
		}
		else if (direction == 'L') {
			for (int i = y; i >= y-length; i--) {
				ground[x][i] = '#';
				coords[1] = i;
			}
			
		}
		else if (direction == 'U') {
			for (int i = x; i >= x-length; i--) {
				ground[i][y] = '#';
				coords[0] = i;
			}
		}
		else if (direction == 'D') {
			for (int i = x; i <= x+length; i++) {
				ground[i][y] = '#';
				coords[0] = i;
			}
		}
		return coords;
	}

}
