import sys

N = int(sys.stdin.readline().rstrip())
time = list(map(int, sys.stdin.readline().rstrip().split()))
  
time.sort()

for index in range(1, N):
  time[index] += time[index - 1]
  
print(sum(time))