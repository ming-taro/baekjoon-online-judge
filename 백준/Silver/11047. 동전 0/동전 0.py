import sys

str = sys.stdin.readline().rstrip().split()

N = int(str[0])
K = int(str[1])

coin = []
start = 0

for index in range(N):
  value = int(sys.stdin.readline().rstrip())
  coin.append(value)
  
  if K // value > 0:
    start = index

count = 0

for index in reversed(range(start + 1)):
  n = K // coin[index]
  
  if n > 0:
    count += n
    K %= coin[index]
    # print("coin = %d, n = %d, K = %d"%(coin[index], n, K))
    
print(count)