#include <iostream>
#include <string>
#include <fstream>
#include <vector>
using namespace std;
enum Numbers {
	one = 1,
	two = 2,
	three = 3,
	four = 4,
	five = 5,
	six = 6,
	seven = 7,
	eight = 8,
	nine = 9
};

static const char *enumStr[] = {
	"one",
	"two",
	"three",
	"four",
	"five",
	"six",
	"seven",
	"eight",
	"nine"
};

int main() {

  fstream newfile;
	int totalSum = 0;
	vector<int> numbers;
	
//	string one = "one";
//	string two = "two";
//	string three = "three";
//	string four = "four";
//	string five = "five";
//	string six = "six";
//string seven = "seven";
//	string eight = "eight";
//	string nine = "nine";

	newfile.open("input.txt", ios::in);
	int linecounter = 1;
	if (newfile.is_open()){
		string tp;
		while (getline(newfile, tp)){
		//cout << tp << "\n";
		//48 - 57 ASCII
			int substringStart = 0;
			int stringLength = 1;
			for (int i = 0; i < tp.size(); i++) {
				/*
				for (int j = 0; j < 9; j++) {
					if (tp.substr(substringStart, stringLength).find(enumStr[j]) != string::npos) {
						numbers.push_back(j+1);
						break;
					}
				}*/
				if (tp[i] >= 48 && tp[i] <= 57) {
					numbers.push_back(tp[i]-48);
					substringStart = i;
					stringLength = 2;
				}
				else {
					for (int j = 0; j < 9; j++) {
						if (tp.substr(substringStart, stringLength).find(enumStr[j]) != string::npos) {
							numbers.push_back(j+1);
							substringStart = i;
							stringLength = 1;
							break;
						}
					}
					stringLength++;
				}
			}
			cout << linecounter << " ";
			if(numbers.size() == 1) {
				cout << (numbers[0] + numbers[0] * 10) << endl;
				totalSum += (numbers[0] + numbers[0] * 10); 
			}
			else {
				cout << (numbers[0] * 10 + numbers[numbers.size()-1]) << endl;
				totalSum += (numbers[0] * 10 + numbers[numbers.size()-1]);
			}
			linecounter++;
			numbers.clear();
		}
		newfile.close();
		cout << totalSum << endl;
	}	
}
	
