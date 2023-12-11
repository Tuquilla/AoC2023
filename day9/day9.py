
import statistics

def search(data=[]):
    counter = 0
    for y in range(len(data)-1):
        if data[y] == data[y+1]:
            counter += 1

    if (counter == len(data)-1):
        return data[len(data)-1]
    else:
        dataN=[]
        for x in range(0, len(data)-1, 1): 
            dataN.append(-data[x]+data[x+1])
        #print(dataN)
        return search(dataN)+data[len(data)-1]
    
def searchForward(data=[]):
    counter = 0
    for y in range(len(data)-1):
        if data[y] == data[y+1]:
            counter += 1

    if (counter == len(data)-1):
        return data[0]
    else:
        dataN=[]
        for x in range(0, len(data)-1, 1): 
            dataN.append(-data[x]+data[x+1])
        #print(dataN)
        return (data[0]-searchForward(dataN))

with open('input.txt', 'r') as f:
    resultat = 0 
    resultatII = 0  
    for line in f:
        dataN=[]
        data = line.split()
        #Werte als Zahl in Liste verpacken
        for x in data:
            dataN.append(int(x))
        resultat += int(search(dataN))
        resultatII += int(searchForward(dataN))
        
    print("ValueI = " + str(resultat))
    print("ValueII = " + str(resultatII))

        


