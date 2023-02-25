import sys
from collections import deque
#sys.setrecursionlimit(10**9) #dfs구현시 파이썬 재귀의 깊이 제한: 1000 -> 런타임 에러 발생

N = int(sys.stdin.readline().rstrip())

graph = [[] for _ in range(N + 1)]
visit = [0]*(N + 1)

for _ in range(N - 1):
  node1, node2 = map(int, sys.stdin.readline().rstrip().split())
  graph[node1].append(node2)
  graph[node2].append(node1)

def dfs(v):
  for i in graph[v]:
    if visit[i] == 0:
      visit[i] = v     #방문 점검시 방문표시가 아닌 부모의 노드값을 저장함
      dfs(i)

def bfs(v):
  q = deque([v])
  visit[v] = v
  
  while q:
    v = q.popleft()
    for i in graph[v]:
      if visit[i] == 0:
        q.append(i)
        visit[i] = v

#dfs(1)
bfs(1)

for i in range(2, N + 1):
  print(visit[i])