#include <iostream>
#include <string>
#include <fstream>
#include <vector>
using namespace std;

bool adjacencySearch(vector<vector<char>> text, int vertikal, int horizontal) {
  /*
   * 0x0
   * 000
   * 000
   */
  if (vertikal - 1 >= 0 && text.at(vertikal - 1).at(horizontal) != '.' && (text.at(vertikal-1).at(horizontal) < 48 || text.at(vertikal-1).at(horizontal) > 57)) {
     return true;
  } 
  /*
   * 00x
   * 000
   * 000
   */
  else if (vertikal - 1 >= 0 && horizontal + 1 < text.at(vertikal-1).size() && text.at(vertikal - 1).at(horizontal + 1) != '.' && (text.at(vertikal-1).at(horizontal + 1) < 48 || text.at(vertikal-1).at(horizontal + 1) > 57)) {
     return true;
  }
  /*
  * 000
  * 00x
  * 000
  */
  else if (vertikal >= 0 && horizontal + 1 < text.at(vertikal).size() && text.at(vertikal).at(horizontal + 1) != '.' && (text.at(vertikal).at(horizontal + 1) < 48 || text.at(vertikal).at(horizontal + 1) > 57)) {
     return true;
  }
  /*
  * 000
  * 000
  * 00x
  */
  if (vertikal +1 < text.size() && horizontal + 1 < text.at(vertikal+1).size() && text.at(vertikal + 1).at(horizontal + 1) != '.' && (text.at(vertikal + 1).at(horizontal + 1) < 48 || text.at(vertikal + 1).at(horizontal + 1) > 57)) {
     return true;
  /*
  * 000
  * 000
  * 0x0
  */
  } 
  if (vertikal + 1 < text.size() && text.at(vertikal+1).at(horizontal) != '.' && (text.at(vertikal + 1).at(horizontal) < 48 || text.at(vertikal + 1).at(horizontal) > 57)) {
     return true;
  }
  /*
  * 000
  * 000
  * x00
  */
  if (vertikal +1 < text.size() && horizontal -1 >= 0 && text.at(vertikal + 1).at(horizontal -1) != '.' && (text.at(vertikal + 1).at(horizontal - 1) < 48 || text.at(vertikal + 1).at(horizontal - 1) > 57)) {
     return true;
  }
  /*
  * 000
  * x00
  * 000
  */
  if (vertikal < text.size() && horizontal - 1 >= 0 && text.at(vertikal).at(horizontal - 1) != '.' && (text.at(vertikal).at(horizontal - 1) < 48 || text.at(vertikal).at(horizontal - 1) > 57)) {
     return true;
  }
  /*
  * x00
  * 000
  * 000
  */
  if (vertikal - 1 >= 0 && horizontal - 1 >= 0 && text.at(vertikal - 1).at(horizontal - 1) != '.' && (text.at(vertikal - 1).at(horizontal - 1) < 48 || text.at(vertikal - 1).at(horizontal - 1) > 57)) {
     return true;
  }
  else {
    return false;
  }
}
void numberGenerator(vector<vector<char>> text, int vertikal, int horizontal) {

}

int main() {

  fstream newfile;
	int totalSum = 0;
  int totalSumPartII = 0;
  int zahlenraster[142][142] = {{0}};
	
	newfile.open("input.txt", ios::in);
  vector<vector<char>> text;
	if (newfile.is_open()){
		string tp;
		while (getline(newfile, tp)){
      vector<char> satz;
      for (int i = 0; i < tp.size(); i++) {
        satz.push_back(tp[i]);
      }
      text.push_back(satz);
		}
		newfile.close();
    //Part II
    // Replace everything except Numbers and * with .
  
    for (int i = 0; i < text.size(); i++) {
      for (int j = 0; j < text.at(i).size(); j++) {
        if(text.at(i).at(j) != '*' && (text.at(i).at(j) < 48 || text.at(i).at(j) > 57)) {
          text.at(i).at(j) = '.';
        }
      }
    }

    //Verarbeitung
    for (int i = 0; i < text.size(); i++) {
      //find special characters
      string zahl;
      int truthCounter = 0;
      for (int j = 0; j < text.at(i).size(); j++) {
        //cout << text.at(i).at(j) << "";
        if (text.at(i).at(j) >= 48 && text.at(i).at(j) <= 57) {
          //cout << text.at(i).at(j);
          if (adjacencySearch(text, i, j)) {
            truthCounter++;
          }
          zahl += text.at(i).at(j);
          if (j == text.at(i).size() - 1) {
            if (truthCounter > 0) {
              int plus = 1;
              int zwischenRes = 0;
              for (int v = 0; v < zahl.size(); v++) {
                zwischenRes += zahlenraster[i+1][j+plus] = stoi(zahl);
                plus--;
              }
              /*
              zahlenraster[i+1][j+1] = stoi(zahl);
              zahlenraster[i+1][j-1+1] = stoi(zahl);
              zahlenraster[i+1][j-2+1] = stoi(zahl);
              */
              totalSum += stoi(zahl);
            }
            truthCounter = 0;
            zahl.clear();
          }
        }
        else {
          if (truthCounter > 0) {
            int plus = 0;
            int zwischenRes = 0;
            for (int v = 0; v < zahl.size(); v++) {
              zwischenRes += zahlenraster[i+1][j+plus] = stoi(zahl);
              plus--;
            }
            /*
            zahlenraster[i+1][j+1] = stoi(zahl);
            zahlenraster[i+1][j-1+1] = stoi(zahl);
            zahlenraster[i+1][j-2+1] = stoi(zahl);
            */
            totalSum += stoi(zahl);
          }
          truthCounter = 0;
          zahl.clear();
        }
      }
      cout << "\n";
    }
	}	
  int first = 0;
  int second = 0;
  int lineCounter = 1;
  vector<int> res;
  for (int i = 0; i < text.size(); i++) {
    cout << "Zeile" << lineCounter << "\n";
    lineCounter++;
    for (int j = 0; j < text.at(i).size(); j++) {
      if (text.at(i).at(j) == '*') {
        
        for (int pre = j; pre < j+3; pre++) {
          res.push_back(zahlenraster[i][pre]);
        }
        
        res.push_back(0);
        //vorergehende Reihe
        res.push_back(zahlenraster[i+1][j]);
        res.push_back(0);
        res.push_back(zahlenraster[i+1][j+2]);
        res.push_back(0);
        for (int prae = j; prae < j+3; prae++) {
          res.push_back(zahlenraster[i+2][prae]);
        }
      for (int v = 0; v < res.size(); v++) {
        cout << res.at(v) << " ";
      } 
      //Remove conecutive Numbers
      //cout << "\n";
      for (int x = 0; x < res.size()-1; x++) {
        if (res.at(x+1) != 0) {
          res.at(x) = 0;
        }  
      }
      int sum = 1;
      int sum1 = 0;
      int sum2 = 0;
      for (int x = 0; x < res.size(); x++) {
          if (res.at(x) != 0) {
            if (sum1 == 0) {
              sum1 = res.at(x);
            }
            else {
              sum2 = res.at(x);
            }
          }
          //cout << res.at(x) << " ";
      }
      cout << " Summe = " << sum1*sum2;
      totalSumPartII += sum1*sum2;
      cout << " Totale Summe = " << totalSumPartII;
      res.clear();
      cout << "\n";
      }
    }
  }
  cout << "\n\n" << endl;
  /*
  int line = 1;
  for (int i = 0; i < end(zahlenraster) - begin(zahlenraster); i++) {
    cout << "Zeile " << line << "\n";
    line++;
    for (int j = 0; j < end(zahlenraster) - begin(zahlenraster); j++) {
      cout << zahlenraster[i][j] << " ";
    }
    cout << "\n";
  }
 */ 
  cout << "TotalSum Part II = " << totalSumPartII << endl;
  cout << "TotalSum = " << totalSum << endl;
}	
