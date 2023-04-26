import sys
from collections import deque 


def is_out_of_range(value, max_value):
    if value < 0 or value >= max_value:
        return True
    return False

def bfs(start_point):
    visited[start_point[0]][start_point[1]] = 1
    distance[start_point[0]][start_point[1]] = 0
    
    dx = [0, 0, 1, -1]  #→, ←, ↑, ↓
    dy = [1, -1, 0, 0] 
    
    q = deque()
    q.append(start_point)
    
    while q:
        point = q.popleft()
        
        for i in range(4):
            next_row = point[0] + dx[i]
            next_col = point[1] + dy[i]
            
            if (is_out_of_range(next_row, n) 
                or is_out_of_range(next_col, m)
                or visited[next_row][next_col] == 1
                or distance[next_row][next_col] == 0):
                continue
            
            q.append([next_row, next_col])
            distance[next_row][next_col] = distance[point[0]][point[1]] + 1

            visited[next_row][next_col] = 1


n, m = map(int, sys.stdin.readline().rstrip().split())
distance = []
start_point = []

visited = [[0]*m for _ in range(n)]

for row in range(n):
    input = list(map(int, sys.stdin.readline().rstrip().split()))
    distance.append(input)
    for col in range(m):
        if input[col] == 2:
            start_point = [row, col]

bfs(start_point)

for row in range(n):
    for col in range(m):
        if visited[row][col] == 0 and distance[row][col] == 1:
            print(-1, end=" ")
        else:
            print(distance[row][col], end=" ")
    print()
