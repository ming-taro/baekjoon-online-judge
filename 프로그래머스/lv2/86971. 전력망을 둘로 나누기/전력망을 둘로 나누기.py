from collections import deque

def bfs(n, v, exceptionNode):
    visited = [False for _ in range(n + 1)]
    queue = deque([v])
    visited[v] = True
    
    node_count = 1
    
    while queue:
        u = queue.popleft()
        for node in graph[u]:
            if node != exceptionNode and visited[node] == False:
                node_count += 1
                queue.append(node)
                visited[u] = True
                
    return node_count
    
def solution(n, wires):
    answer = n
    
    global graph
    graph = [[] for _ in range(n + 1)]

    for wire in wires:
        graph[wire[0]].append(wire[1])
        graph[wire[1]].append(wire[0])
    
    for wire in wires:
        v1 = bfs(n, wire[0], wire[1])
        v2 = n - v1    
        answer = min(answer, abs(v1 - v2))
        
    return answer

