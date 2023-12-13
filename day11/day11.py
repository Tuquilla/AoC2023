import numpy as np

#Galaxydistance calculation
def distance(galaxylist=[]):
    counterX=1
    totalPaths=0
    for x in galaxylist:
        print(x)
        for y in range(counterX,len(galaxylist)):
            print(galaxylist[y])
        counterX += 1
        print('\n')
    return totalPaths

raster = np.array([])
rasterP=[]
with open('sample.txt', 'r') as f:
    
    for line in f:
        data = line.strip()
        y=[]
        for x in data:
            y.append(x)
        rasterP.append(y)

raster = np.asarray(rasterP)
counter=0
for x in raster:
    if '#' not in x:
        y = np.copy(x)
        raster = np.insert(raster,counter,y, axis=0)
    counter += 1

print('\n')
for x in raster:
    print(x)

raster = np.transpose(raster)

counter=0
for x in raster:
    if '#' not in x:
        y = np.copy(x)
        raster = np.insert(raster,counter,y, axis=0)
    counter += 1

raster = np.transpose(raster)

print('\n')
for x in raster:
    print(x)

#get coordinates of each galaxy
index1=0
galaxylist=[]
for x in raster:
    index2=0
    for y in x:
        if y == '#':
            galaxy=[index1, index2]
            galaxylist.append(galaxy)
        index2 += 1
    index1 += 1

print(len(galaxylist))

print(distance(galaxylist))