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

#Checkt von wo aus nach dem Start gegangen werden muss
def matcherStart(raster=[], start=[]):
    #oben
    if raster[start[0]-1] <= 0 and ('|' in raster[start[0]-1] or 'F' in raster[start[0]-1]):
        start[0] = start[0]-1
        return start
    #rechts
    if raster[start[0]+1] <= len(raster[0]):
        
        return start
    else:
        return -1

def search(raster=[], start=[]):
    
    return

#Puzzle abfüllen
raster=[]
with open('sample.txt', 'r') as f:
    resultat = 0 
    resultatII = 0  
    for line in f:
        dataN=[]
        data = line.strip()
        for x in data:
            dataN.append(x)
        #print(dataN)
        raster.append(dataN)

#Finaler Raster
y=[]
counter=0;
for x in raster:
    print(x)
    if 'S' in x:
        y.append(counter)
        y.append(x.index('S'))
    counter += 1
print(y)
        
