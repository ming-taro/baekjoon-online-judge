import sys

computer = int(sys.stdin.readline().rstrip())   #컴퓨터 수
n = int(sys.stdin.readline().rstrip())          #간선 개수

graph = [[] for _ in range(computer + 1)]       #연결된 노드간 관계를 2차원 배열로 나타냄

for _ in range(n):
  computer1, computer2 = map(int, sys.stdin.readline().rstrip().split())
  graph[computer1].append(computer2)   #연결된 노드 양방향 연결
  graph[computer2].append(computer1)

visit = [0]*(computer + 1)  #방문 여부를 확인하기위한 리스트(방문한 경우 value = 1)

def dfs(graph, v, visit):
  visit[v] = 1         #방문한 노드는 1로 바꿈(= 현재 위치한 노드)
  for i in graph[v]:   #v와 연결되어있는 노드리스트들을 방문함
    if visit[i] == 0:  #만약 해당 노드를 아직 방문한적이 없다면 해당 노드에 대한 dfs수행
      dfs(graph, i, visit)

dfs(graph, 1, visit)
visit[1] = 0 #자기 자신, 즉 처음 시작시 '1'을 방문하면서 시작하므로 1에 대한 방문기록을 초기화함

print(visit.count(1))