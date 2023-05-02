import sys
from collections import deque

def is_within_range(v):
    if v >= 0 and v < N:
        return True
    return False


def is_possible_to_open(row, col, next_row, next_col):
    difference = abs(country[row][col] - country[next_row][next_col])
    
    if difference >= L and difference <= R:
        return True
    return False


def set_population(people, nodes):
    population = people//len(nodes)
    for node in nodes:
        country[node[0]][node[1]] = population


def bfs(row, col):
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    
    q = deque()
    q.append([row, col])
    visited_node[row][col] = 1
    
    nodes = []
    people = 0
    
    while(q):
        row, col = q.popleft()
        
        nodes.append([row, col])
        people += country[row][col]
        
        for index in range(4):
            next_row = row + dy[index]
            next_col = col + dx[index]
            if (is_within_range(next_row)
                and is_within_range(next_col)
                and is_possible_to_open(row, col, next_row, next_col)
                and visited_node[next_row][next_col] == 0):
                q.append([next_row, next_col])
                visited_node[next_row][next_col] = 1
    
    if len(nodes) == 1:
        return 0
    
    set_population(people, nodes)
    return 1


N, L, R = map(int, sys.stdin.readline().rstrip().split())

country = []
node_list = []

for _ in range(N):
    country.append(list(map(int, sys.stdin.readline().rstrip().split())))

day = 0
is_possible_to_move = [0]

while True:
    visited_node = [[0 for _ in range(N)] for _ in range(N)]
    sum = 0
    
    for row in range(N):
        for col in range(N):
            if visited_node[row][col] == 0:
                sum += bfs(row, col)
            
    if sum == 0:
        break
    day += 1
    
print(day)
