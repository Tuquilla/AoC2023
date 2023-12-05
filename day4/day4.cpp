#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <unordered_set>
#include <sstream>
using namespace std;

string sample = "test.txt";
string full = "input.txt";

int main() {
  
  unordered_set<int> seeds;
  vector<vector<vector<int>>> mapping;
  int line = 1;
  //Read Input
  fstream newfile;
	newfile.open(sample, ios::in);
  vector<string> text;
  vector<vector<int>> mapCont;
	if (newfile.is_open()){
		string tp;
		while (getline(newfile, tp)){
      //Split line at :
      //Split first line for Seeds
      if(line == 1) {
        stringstream tpss(tp);
        string split;
        int counter = 0;
        while(getline(tpss, split, ':')) {
          cout << split << endl;
          stringstream seedNumber(split);
          string number;
          if (counter == 1) { 
            while(getline(seedNumber, number, ' ')) {
              if(number != "") {
                seeds.insert(stoi(number));
              }
              //seeds.insert(stoi(number));  
            }
          }
          counter++;
        }
      }
      else {
        if (tp[tp.size()-2] != ':' && tp.size()-1 != 0) {
          vector<int> mapSet;
          stringstream map(tp);
          string number;
          cout << tp << endl;
          while(getline(map, number, ' ')) {
            cout << tp << endl;
            if (number != "") {
              mapSet.push_back(stoi(number));
            }
          }
          mapCont.push_back(mapSet);
        }
        else {
          mapping.push_back(mapCont);
          mapCont.clear();
        }
      }
      text.push_back(tp);
      line++;
		}
		newfile.close();
  }
  mapping.push_back(mapCont);
  //Output of inputFile
  for(int i = 0; i < text.size(); i++) {
    cout << text.at(i) << endl;
  }
  for(int k = 0; k < mapping.size(); k++) {
    cout << "\n";
    for (int v = 0; v < mapping.at(k).size(); v++) {
      for( auto f : mapping.at(k).at(v)) {
        cout << f << " ";
      }
      cout << "\n";
    }   
  }
}
