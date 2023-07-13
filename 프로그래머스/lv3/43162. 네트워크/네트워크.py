from collections import deque

def bfs(graph, v, n):
    queue = deque([v])
    visited[v] = True
        
    while queue:
        v = queue.popleft()
        
        for node in graph[v]:
            if visited[node] == False:
                queue.append(node)
                visited[node] = True
            
def solution(n, computers):
    global visited, graph
    
    answer = 0
    graph = [[] for _ in range(n)]
    visited = [False for _ in range(n)]
    
    for i in range(n):
        for j in range(n): 
            if i != j and computers[i][j] == 1:
                graph[i].append(j)
                graph[j].append(i)
    
    for i in range(len(graph)):
        graph[i] = list(set(graph[i]))
    
    for v in range(n):
        if visited[v] == False:
            answer += 1
            bfs(graph, v, n)
    
    return answer

#n개의 컴퓨터
#computers : 컴퓨터 연결에 대한 정보가 담긴 배열
#return 네트워크 개수

#예제1: [1] -> popleft:1, append:2 -> [2] -> popleft:2, append(3) -> [3] -> popleft:3