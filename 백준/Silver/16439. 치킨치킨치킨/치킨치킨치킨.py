import sys
import itertools

N, M = map(int, sys.stdin.readline().rstrip().split())
chicken = []

for _ in range(N):
    input = list(map(int, sys.stdin.readline().rstrip().split()))
    chicken.append(input)

index_list = [i for i in range(M)]
selection = set(itertools.combinations(index_list, 3))

max_sum = 0

for index in selection:
    sum = 0
    i = index[0]
    j = index[1]
    k = index[2]
    for n in range(N):
        sum += max(chicken[n][i], chicken[n][j], chicken[n][k])
    max_sum = max(max_sum, sum)

print(max_sum)
