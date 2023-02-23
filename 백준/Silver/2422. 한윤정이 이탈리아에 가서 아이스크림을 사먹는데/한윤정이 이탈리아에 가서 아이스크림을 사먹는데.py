import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
ice_cream_list = [[True for _ in range(N)] for _ in range(N)]

for i in range(M):
  ice_cream_1, ice_cream_2 = map(int, sys.stdin.readline().rstrip().split())
  ice_cream_list[ice_cream_1 - 1][ice_cream_2 - 1] = False
  ice_cream_list[ice_cream_2 - 1][ice_cream_1 - 1] = False

count = 0

for i in range(N - 2):
  for j in range(i + 1, N - 1):
    for k in range(j + 1, N):
      if ice_cream_list[i][j] and ice_cream_list[i][k] and ice_cream_list[j][k]:
        count += 1

print(count)