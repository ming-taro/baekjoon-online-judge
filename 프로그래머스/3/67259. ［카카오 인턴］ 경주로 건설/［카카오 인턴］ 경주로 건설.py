from collections import deque

def solution(board):
    return bfs(board)

def bfs(board):
    dx = [-1, 0, 1, 0]   #위, 오, 아, 왼
    dy = [0, 1, 0, -1]
    n = len(board[0])
    
    q = deque([])
    costs = [[[-1 for _ in range(n)] for _ in range(n)] for _ in range(4)]
    costs[0][0][0] = 0
    costs[1][0][0] = 0
    costs[2][0][0] = 0
    costs[3][0][0] = 0
    
    if board[0][1] == 0:
        q.append([0, 1, 1, 100])
        costs[1][0][1] = 100
    if board[1][0] == 0:
        q.append([1, 0, 2, 100])
        costs[2][1][0] = 100
    
    while q:
        x, y, d, cost = q.popleft()
        
        if x == n - 1 and y == n - 1:  #종점 도착시 비용 체크
            continue

        for i in range(4):
            next_x = x + dx[i]  #다음으로 이동할 칸
            next_y = y + dy[i]
            
            if is_valid_path(n, next_x, next_y) == False or board[next_x][next_y] == 1:
                continue
            
            if i == d or is_opposite_direction(i, d):  #직선로
                new_cost = cost + 100
            else:  #코너길
                new_cost = cost + 600
                
            if costs[i][next_x][next_y] == -1 or new_cost < costs[i][next_x][next_y]:
                costs[i][next_x][next_y] = new_cost
                q.append([next_x, next_y, i, new_cost]) 
        
    min_value = 2147483647
    for i in range(4):
        if(costs[i][-1][-1] != -1 and costs[i][-1][-1] < min_value):
            min_value = costs[i][-1][-1]
    return min_value

def is_valid_path(n, x, y):
    return x >= 0 and x < n and y >= 0 and y < n
    
def is_opposite_direction(direction, compare):
    return direction == 1 and compare == 3 or direction == 3 and compare == 1 or direction == 0 and compare == 2 or direction == 2 and compare == 0
        
#N * N 크기의 정사각형 격자
#벽 = 1
#(0, 0)출발 -> (N - 1, N - 1)도착
#직선도로 건설(방향 그대로 직진) = 100원 / 코너 건설(방향 변경) = 500원