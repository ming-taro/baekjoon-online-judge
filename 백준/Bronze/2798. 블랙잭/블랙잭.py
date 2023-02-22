import sys

N, M = map(int, sys.stdin.readline().rstrip().split())

card = list(map(int, sys.stdin.readline().rstrip().split()))
result = 0

for i in range(N):
  for j in range(i + 1, N):
    for k in range(j + 1, N):
      if i == j or j == k or k == i:
        continue
      sum = card[i] + card[j] + card[k]
      if result < sum and sum <= M:
        result = sum

print(result)