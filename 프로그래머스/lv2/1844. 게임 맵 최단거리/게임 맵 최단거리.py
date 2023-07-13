from collections import deque

def is_within_range(row, col, max_row, max_col):
    if (row < 0 or row >= max_row 
        or col < 0 or col >= max_col):
        return False
    return True

def solution(maps):
    answer = 0
    queue = deque([[0, 0]])
    
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    
    max_row = len(maps)
    max_col = len(maps[0])
    
    while queue:
        row, col = queue.popleft()

        for index in range(4):
            next_row = row + dx[index]
            next_col = col + dy[index]
            
            if (is_within_range(next_row, next_col, max_row, max_col)
                and maps[next_row][next_col] == 1):
                maps[next_row][next_col] += maps[row][col]
                queue.append([next_row, next_col])
    
    if maps[max_row - 1][max_col - 1] <= 1:
        return -1
    
    return maps[max_row - 1][max_col - 1]