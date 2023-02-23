import sys

N, K = map(int, sys.stdin.readline().rstrip().split())
K = str(K)
sum = 0

for hour in range(N + 1):
  for minute in range(60):
    for second in range(60):
      time = str(hour*10000 + minute*100 + second)
      if hour < 10:
        time = '0' + time
      if K in time:
        sum += 1

print(sum)