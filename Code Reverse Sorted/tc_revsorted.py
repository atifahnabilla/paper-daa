import random

f = open('tc2-rev.txt', 'w')
sortedList = []
for i in range(1000):
    randomlist = random.randint(0,1001)
    sortedList.append(randomlist)
sortedList.sort(reverse=True)
for j in sortedList:
    f.write(str(j) + '\n')
f.close()