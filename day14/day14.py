
import numpy as np

def switchRocks(arr=[], richtung=str):
    if (richtung == 'north'):
        for x in range(0, len(arr),1):
            for y in range(x, 0, -1):
                if y != 0 and arr[y] == 'O' and arr[y-1] == '.':
                    halter = arr[y]
                    arr[y] = arr[y-1]
                    arr[y-1] = halter
    return arr




matrix=[]
with open('input.txt', 'r') as f:
    for line in f:
        zeilen=[]
        for x in line.strip():
            zeilen.append(x)
        matrix.append(zeilen)

matrix = np.asanyarray(matrix)

matrix = matrix.transpose()

matrixSwitched=[]
for x in matrix:
    line = switchRocks(x, 'north')
    matrixSwitched.append(line)


matrixSwitched = np.asanyarray(matrixSwitched)
print(matrixSwitched)

matrixSwitched = matrixSwitched.transpose()

lineCounter = len(matrixSwitched)
tot = 0
for x in matrixSwitched:
    for count in x:
        if (count == 'O'):
            tot += lineCounter
    lineCounter -= 1

print("Resultat: " + str(tot))
        
