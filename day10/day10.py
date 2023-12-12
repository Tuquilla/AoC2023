import sys

"""
| is a vertical pipe connecting north and south.
- is a horizontal pipe connecting east and west.
L is a 90-degree bend connecting north and east.
J is a 90-degree bend connecting north and west.
7 is a 90-degree bend connecting south and west.
F is a 90-degree bend connecting south and east.
. is ground; there is no pipe in this tile.
S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.
"""

thisdict = {
  '|': [1,0],
  '-': [0,1],
  'L': [1,1],
  'J': [1,-1],
  '7': [-1,-1],
  'F': [-1,1],
  '.': [],
  'S': [0,0]
}
sys.setrecursionlimit(100000)

#
def start(raster=[],start=[]):
    if raster[start[0]][start[1]-1] == '-' or raster[start[0]][start[1]-1] == 'L' or raster[start[0]][start[1]-1] == 'F':
        start.append('osten')
        start[1] = start[1]-1
        return start
    if raster[start[0]][start[1]+1] == '-' or raster[start[0]][start[1]+1] == 'J' or raster[start[0]][start[1]+1] == '7':
        start.append('westen')
        start[1] = start[1]+1
        return start
    if raster[start[0]+1][start[1]] == '|' or raster[start[0]+1][start[1]] == 'L' or raster[start[0]+1][start[1]] == 'J':
        start.append('norden')
        start[0] = start[0]+1
        return start
    if raster[start[0]-1][start[1]] == '|' or raster[start[0]-1][start[1]] == 'F' or raster[start[0]-1][start[1]] == '7':
        start.append('sueden')
        start[0] = start[0]+1
        return start


#Checkt von wo aus nach dem Start gegangen werden muss
def matcherStart(anzahl, herkunft, raster=[], start=[]):
    #print(raster[start[0]][start[1]])
    #Abbruchbedingung
    anzahl += 1
    if (raster[start[0]][start[1]] == 'S' and anzahl != 0):
        return anzahl

    if(raster[start[0]][start[1]] == '|'):
        if (herkunft == 'norden'):
            start[0] = start[0]+1
            return matcherStart(anzahl, 'norden', raster, start)
        else:
            start[0] = start[0]-1
            return matcherStart(anzahl, 'sueden', raster, start)
        
    elif(raster[start[0]][start[1]] == '-'):
        if (herkunft == 'osten'):
            start[1] = start[1]-1
            return matcherStart(anzahl, 'osten', raster, start)
        else:
            start[1] = start[1]+1
            return matcherStart(anzahl, 'westen', raster, start)
        
    elif(raster[start[0]][start[1]] == 'L'):
        if (herkunft == 'norden'):
            start[1] = start[1]+1
            return matcherStart(anzahl, 'westen', raster, start)
        else:
            start[0] = start[0]-1
            return matcherStart(anzahl, 'sueden', raster, start)
        
    elif(raster[start[0]][start[1]] == 'J'):
        if (herkunft == 'norden'):
            start[1] = start[1]-1
            return matcherStart(anzahl, 'osten', raster, start)
        else:
            start[0] = start[0]-1
            return matcherStart(anzahl, 'sueden', raster, start)
        
    elif(raster[start[0]][start[1]] == '7'):
        if (herkunft == 'sueden'):
            start[1] = start[1]-1
            return matcherStart(anzahl, 'osten', raster, start)
        else:
            start[0] = start[0]+1
            return matcherStart(anzahl, 'norden', raster, start)
        
    elif(raster[start[0]][start[1]] == 'F'):
        if (herkunft == 'sueden'):
            start[1] = start[1]+1
            return matcherStart(anzahl, 'westen', raster, start)
        else:
            start[0] = start[0]+1
            return matcherStart(anzahl, 'norden', raster, start)

def search(raster=[], start=[]):
    
    return

#Puzzle abfüllen
raster=[]
with open('input.txt', 'r') as f:
    resultat = 0 
    resultatII = 0  
    for line in f:
        dataN=[]
        dataN.append('.')
        data = line.strip()
        for x in data:
            dataN.append(x)
        #print(dataN)
        dataN.append('.')
        raster.append(dataN)

#Finaler Raster
y=[]
counter=0
for x in raster:
    print(x)
    if 'S' in x:
        y.append(counter)
        y.append(x.index('S'))
    counter += 1
#Position von Punkt S bestimmen
print(y)

#Start der Rekursion bestimmen
test = start(raster,y)
print(test)
y[0] = test[0]
y[1] = test[1]
herkunft = test[2]
anzahl = 0
#Rekursion starten
print(matcherStart(anzahl, herkunft, raster, y)/2)

        
