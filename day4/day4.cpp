#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <unordered_set>
#include <sstream>
#include <climits>
using namespace std;

string sample = "test.txt";
string full = "input.txt";

int main() {
  
  vector<long long> seeds;
  vector<vector<vector<long long>>> mapping;
  long long line = 1;
  //Read Input
  fstream newfile;
	newfile.open(sample, ios::in);
  vector<string> text;
  vector<vector<long long>> mapCont;
	if (newfile.is_open()){
		string tp;
		while (getline(newfile, tp)){
      //Split line at :
      //Split first line for Seeds
      
      if(line == 1) {
        stringstream tpss(tp);
        string split;
        long long counter = 0;
        while(getline(tpss, split, ':')) {
          cout << split << endl;
          stringstream seedNumber(split);
          string number;
          if (counter == 1) { 
            while(getline(seedNumber, number, ' ')) {              
              if(number != "" && number != " ") {
                seeds.push_back(stoll(number));
              }
            }
          }
          counter++;
        }
      }
      
      else {
        if (tp[tp.size()-2] != ':' && tp.size()-1 != 0) {
          vector<long long> mapSet;
          stringstream map(tp);
          string number;
          cout << tp << endl;
          while(getline(map, number, ' ')) {
            cout << tp << endl;
            if (number != "") {
              mapSet.push_back(stoll(number));
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
  for (long long i = 0; i < text.size(); i++) {
    cout << text.at(i) << endl;
  }
  for (long long k = 0; k < mapping.size(); k++) {
    cout << "\n";
    for (long long v = 0; v < mapping.at(k).size(); v++) {
      for (auto f : mapping.at(k).at(v)) {
        cout << f << " ";
      }
      cout << "\n";
    }   
  }
  //Part I
  long long resultatPartI = UINT_MAX;
  vector<long long> location;
  for (long long seed = 0; seed < seeds.size(); seed++) {
    long long nextVal = seeds.at(seed);
    for (long long conditions = 0; conditions < mapping.size(); conditions++) {
      for (long long certainCondition = 0; certainCondition < mapping.at(conditions).size(); certainCondition++) {
        long long dest = -1;
        long long source = -1;
        long long range = -1;
        for (long long numbers = 0; numbers < mapping.at(conditions).at(certainCondition).size(); numbers++) {
          if (numbers == 0) {
            dest = mapping.at(conditions).at(certainCondition).at(numbers);
          }
          else if (numbers == mapping.at(conditions).at(certainCondition).size()-1) {
            range = mapping.at(conditions).at(certainCondition).at(numbers);
          }
          else {
            source = mapping.at(conditions).at(certainCondition).at(numbers);
          }
        }
        if (nextVal <= source + range && nextVal >= source) {
          nextVal = dest + (nextVal - source);
          break;
        }
      }
      //cout << endl;
    }
    if (nextVal < resultatPartI) {
      resultatPartI = nextVal;
    }
  }
  cout << "Soil min: " << resultatPartI << endl;
  //Part II (too long)
  /*
  long long resultatPartII = LLONG_MAX;
  location.clear();
  for (long long seed = 0; seed < seeds.size()-1; seed++) {
    long long nextValII = seeds.at(seed);
    long long nextValRange = seeds.at(seed+1);
    if (seed%2 == 0) {
      //cout << "nextValII: " << nextValII << endl;
      int v = nextValII;
      while (v < nextValRange+nextValII) {
        long long nextVal = v;
        for (long long conditions = 0; conditions < mapping.size(); conditions++) {
          for (long long certainCondition = 0; certainCondition < mapping.at(conditions).size(); certainCondition++) {
            long long dest = -1;
            long long source = -1;
            long long range = -1;
            for (long long numbers = 0; numbers < mapping.at(conditions).at(certainCondition).size(); numbers++) {
              if (numbers == 0) {
                dest = mapping.at(conditions).at(certainCondition).at(numbers);
              }
              else if (numbers == mapping.at(conditions).at(certainCondition).size()-1) {
                range = mapping.at(conditions).at(certainCondition).at(numbers);
              }
              else {
                source = mapping.at(conditions).at(certainCondition).at(numbers);
              }
            }
            if (nextVal <= source + range && nextVal >= source) {
              nextVal = dest + (nextVal - source);
              break;
            }
          }
          //cout << endl;
        }
        if (nextVal < resultatPartII) {
          resultatPartII = nextVal;
        }
        v++;
      }
    }
  }
  */
  cout << endl;
  //Part II 
  long long resultatPartII = LLONG_MAX;
  location.clear();
  long long lowestNumber = 0;
  bool repeat = true;
  long long repeatCounter = 0;
  while(repeat) {
    long long nextVal = repeatCounter;
    for (long long conditions = mapping.size()-1; conditions >= 0; conditions--) {
      for (long long exactCondition = 0; exactCondition < mapping.at(conditions).size(); exactCondition++) {
        long long dest = -1;
        long long source = -1;
        long long range = -1;
        for (long long numberCond = 0; numberCond < mapping.at(conditions).at(exactCondition).size(); numberCond++) {
          //cout << mapping.at(conditions).at(exactCondition).at(numberCond);
          if (numberCond == 0) {
            dest = mapping.at(conditions).at(exactCondition).at(numberCond);
          }
          else if (numberCond == mapping.at(conditions).at(exactCondition).size()-1) {
            range = mapping.at(conditions).at(exactCondition).at(numberCond);
          }
          else {
            source = mapping.at(conditions).at(exactCondition).at(numberCond);
          }
        }
        //cout << dest << " " << source << " " << range << endl;
        if (nextVal >= dest && nextVal <= dest + range) {
          //cout << "Nextval I: " << nextVal << endl;
          nextVal = source + (nextVal - dest);
          //cout << "Nextval II: " << nextVal << endl;
          break;
        }
      }
    }
    //Check Seeds
    /*
    cout << "#####\n" << "nextVal: " << nextVal << endl;
    if (repeatCounter == 46) {
      repeat = false;
    }
    */
    for (long long seed = 0; seed < seeds.size()-1; seed++) {
      if (seed%2 == 0) {
        if (nextVal >= seeds.at(seed) && nextVal <= seeds.at(seed)+seeds.at(seed+1)-1) {
          lowestNumber = repeatCounter;
          cout << "start: " << seeds.at(seed) << " Ende: " << seeds.at(seed) + seeds.at(seed+1)-1 << "\n";
          cout << "lowestNumber: " << lowestNumber << endl;
          cout << "corresponding seed: " << nextVal << endl;
          repeat = false;;
        }
      }
    }
    repeatCounter++;
  }

  cout << "Soil min PartII: " << lowestNumber << endl;
}
