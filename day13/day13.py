import numpy as np

allMatrix=[]

def horizontalStart(matrix=[]):
    overallStarts=[]
    for x in range (0, len(matrix)-1,1):

        if (compareLine(matrix[x], matrix[x+1])):
            lines=[]
            lines.append(x)
            lines.append(x+1)
            overallStarts.append(lines)
    return overallStarts

def horizontal(matrix=[], start1=int, start2=int):
    back = start1
    forward = start2
    while(True):
        #print(back)
        #print(forward)
        #print(len(matrix))
        if ((back <= 0 or forward >= len(matrix)-1)):
            if (back < 0 or forward > len(matrix)-1):
                return 0
            else:
                if (compareLine(matrix[back], matrix[forward]) == True):
                    return (start1+1)*100
                else:
                    return 0
        if (compareLine(matrix[back], matrix[forward]) == True):
            back -= 1
            forward += 1
        else:
            return 0

def compareLine(a=[], b=[]):
    for x in range(0, len(a),1):
        if (a[x] != b[x]):
            return False
    return True

with open('sample.txt', 'r') as f:
    matrix = []
    for line in f:
        if line == '\n':
            allMatrix.append(np.asarray(matrix))
            matrix=[]
        else:
            line = line.strip().split()
            zeile=[]
            for x in line:
                for y in x:
                    zeile.append(y)
            matrix.append(zeile)
            #print(matrix)
"""
horzi = horizontalStart(allMatrix[1])
print(horizontal(allMatrix[1], horzi[0], horzi[1]))
horzi = horizontalStart(allMatrix[0].transpose())
print(horizontal(allMatrix[1].transpose(), horzi[0], horzi[1]))
"""

totalSum = 0
counter = 0
for x in allMatrix:
    print(x)
    start = horizontalStart(x)
    for y in start:
        if start != None:
            totalSum += horizontal(x, y[0], y[1])
    start = horizontalStart(x.transpose())
    print(x.transpose())
    for y in start:
        if start != None:
            totalSum += int(horizontal(x.transpose(), y[0], y[1])/100)
    """
    if (counter == 1):
        break
    counter += 1
    """

print("Summe Total: " + str(totalSum))
