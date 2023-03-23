import sys
import itertools

N, M = map(int, sys.stdin.readline().rstrip().split())

selection = [[True for _ in range(N)] for _ in range(N)]

for _ in range(M):
    ice_cream_1, ice_cream_2 = map(int, sys.stdin.readline().rstrip().split())
    selection[ice_cream_1 - 1][ice_cream_2 - 1] = False
    selection[ice_cream_2 - 1][ice_cream_1 - 1] = False

count = 0

for i in range(N - 2):
    for j in range(i + 1, N - 1):
        for k in range(j + 1, N):
            if selection[i][j] and selection[i][k] and selection[j][k]:
                count += 1

print(count)
