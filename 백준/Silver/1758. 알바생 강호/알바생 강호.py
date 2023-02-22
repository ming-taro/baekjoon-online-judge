import sys

N = int(sys.stdin.readline().rstrip())
tip_list = []

for i in range(N):
  tip_list.append(int(sys.stdin.readline().rstrip()))

tip_list.sort(reverse = True)

sum = 0

for index in range(N):
  tip = tip_list[index] - index 
  sum += tip if tip > 0 else 0
  
print(sum)