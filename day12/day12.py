

def arrangement(geysirs=[], docs=[]):
    
    return 0

matrix=[]
with open('sample.txt', 'r') as f:
    for line in f:
        left = line.split(' ')[0]
        right = line.split(' ')[1].strip()
        right = right.split(',')
        geysirs=[]
        for x in left:
            geysirs.append(x)
        print(geysirs)
        docs=[]
        for x in right:
            docs.append(x)
        print(docs)
