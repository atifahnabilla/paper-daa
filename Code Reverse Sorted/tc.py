import random

f = open('tc6-tabel12.txt', 'w')
sortedList = []
for i in range(100):
    randomlist = random.randint(0,1001)
    sortedList.append(randomlist)
sortedList.sort()
for j in sortedList:
    f.write(str(j) + '\n')
f.close()