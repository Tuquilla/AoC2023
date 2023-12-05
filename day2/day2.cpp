#include <iostream>
#include <string>
#include <fstream>
#include <vector>
using namespace std;

int blueCheck(string tp) {
	int max = 1;
	string sets = tp.substr(tp.find(":")+1, tp.length());
  //find blue
	size_t pos = sets.find("blue", 0);
	while (pos != string::npos) {
		if (sets[pos-3] == ' ') {
			if (sets[pos-2]-48 > max) {
				max = sets[pos-2]-48;
			}
		}
		else {
			if ((sets[pos-3]-48)*10 + sets[pos-2]-48 > max) {
				max = (sets[pos-3]-48)*10 + sets[pos-2]-48; 
			}
		}
		pos = sets.find("blue", pos + 1);
	}
	return max;
};

int redCheck(string tp) {
	int max = 1;
	string sets = tp.substr(tp.find(":")+1, tp.length());
  //find blue
	size_t pos = sets.find("red", 0);
	while (pos != string::npos) {
		if (sets[pos-3] == ' ') {
			if (sets[pos-2]-48 > max) {
				max = sets[pos-2]-48;
			}
		}
		else {
			if ((sets[pos-3]-48)*10 + sets[pos-2]-48 > max) {
				max = (sets[pos-3]-48)*10 + sets[pos-2]-48; 
			}
		}
		pos = sets.find("red", pos + 1);
	}
	return max;
};

int greenCheck(string tp) {
	int max = 1;
	string sets = tp.substr(tp.find(":")+1, tp.length());
  //find blue
	size_t pos = sets.find("green", 0);
	while (pos != string::npos) {
		if (sets[pos-3] == ' ') {
			if (sets[pos-2]-48 > max) {
				max = sets[pos-2]-48;
			}
		}
		else {
			if ((sets[pos-3]-48)*10 + sets[pos-2]-48 > max) {
				max = (sets[pos-3]-48)*10 + sets[pos-2]-48;
			}
		}
		pos = sets.find("green", pos + 1);
	}
	return max;
};



int main() {

  fstream newfile;
	int totalSum = 0;
	vector<int> blue;
	vector<int> red;
	vector<int> green;
	
	newfile.open("input.txt", ios::in);
	int linecounter = 1;
	if (newfile.is_open()){
		string tp;
		while (getline(newfile, tp)){
			//12 red, 13 green, 14 blue
			//Get all sets
			totalSum += (blueCheck(tp) * redCheck(tp) * greenCheck(tp));
			linecounter++;
		}
		newfile.close();
		cout << totalSum << endl;
	}	
}	
