#sys.setrecursionlimit(10**9) #dfs구현시 파이썬 재귀의 깊이 제한: 1000 -> 런타임 에러 발생
import sys
from collections import deque

N, M, V = map(int, sys.stdin.readline().rstrip().split())

#(정점 번호가 1부터 시작하므로 1~N인덱스를 사용하기위해 N+1 크기만큼 생성)
graph = [[] for _ in range(N + 1)]
visit_dfs = [0]*(N + 1) 
visit_bfs = [0]*(N + 1) 

for _ in range(M):     #간선을 입력받음
  v1, v2 = map(int, sys.stdin.readline().rstrip().split())
  graph[v1].append(v2) #양방향 연결
  graph[v2].append(v1)

for v in range(N + 1):
  graph[v].sort()  #정점 번호가 작은 순으로 미리 정렬

def dfs(v):
  visit_dfs[v] = 1
  print(v, end=" ")
  for i in graph[v]:
    if visit_dfs[i] == 0:
      dfs(i)

def bfs(v):
  q = deque([v])   #큐 생성
  visit_bfs[v] = 1     #처음 시작 정점부터 방문처리
  
  while q:             #큐에 값이 더 이상 없을 때까지 순회
    v = q.popleft()    #큐의 가장 앞에 있는 값을 꺼냄
    print(v, end=" ") 
    for i in graph[v]: #v와 연결된 모든 정점을 작은값부터 순서대로 queue에 삽입
      if visit_bfs[i] == 0:
        q.append(i)
        visit_bfs[i] = 1

dfs(V)
print()
bfs(V)