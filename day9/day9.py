
with open('sample.txt', 'r') as f:    
    for row in f:
        data = f.readline().splitlines()
        print(data)
        print(data[0] + [1])


