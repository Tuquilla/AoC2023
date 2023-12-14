import numpy as np

#Galaxydistance calculation
def distance(galaxylist=[], raster=[]):
    totalPaths=0
    for x in range(0, len(galaxylist)):
        shortestPathGalaxy= len(raster) + len(raster[0])
        for y in range(x+1,len(galaxylist)):
            shortestPath = abs(galaxylist[x][0]-galaxylist[y][0]) + abs(galaxylist[x][1]-galaxylist[y][1])
            print("shortest PathI = " + str(shortestPath))
            totalPaths += shortestPath
    return totalPaths
            
def distanceII(galaxylist=[], raster=[], expand=int):
    totalPaths=0
    for x in range(0, len(galaxylist)):
        shortestPathGalaxy= len(raster) + len(raster[0])
        for y in range(x+1,len(galaxylist)):
            xCord = galaxylist[y][0] - galaxylist[x][0]
            yCord = galaxylist[y][1] - galaxylist[x][1]
            distX = 0
            distY = 0
            shortestPath = 0
            for xx in raster:
                print(xx)
            totalPaths += shortestPath
            break
        break
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

expand=2

raster = np.asarray(rasterP)
rasterII = raster

#PartI
counter=0
for x in raster:
    if '#' not in x:
        y = np.copy(x)
        for v in range(expand-1):
            raster = np.insert(raster,counter,y, axis=0)
            counter += 1
    counter += 1
"""
for x in raster:
    print(x)
"""

raster = np.transpose(raster)

counter=0
for x in raster:
    if '#' not in x:
        y = np.copy(x)
        for v in range(expand-1):
            raster = np.insert(raster,counter,y, axis=0)
            counter += 1
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

print(distance(galaxylist,raster))

#PartII
counter=0
for x in rasterII:
    if '#' not in x:
        x[x == '.'] = '?'

for x in rasterII:
    print(x)

rasterII = np.transpose(rasterII)

counter=0
for x in rasterII:
    if '#' not in x:
        x[x == '.'] = '?'

print('\n')
for x in rasterII:
    print(x)

rasterII = np.transpose(rasterII)

print('\n')
for x in rasterII:
    print(x)

index1=0
galaxylist=[]
for x in rasterII:
    index2=0
    for y in x:
        if y == '#':
            galaxy=[index1, index2]
            galaxylist.append(galaxy)
        index2 += 1
    index1 += 1

print('\n')
print(distanceII(galaxylist,rasterII, expand))
