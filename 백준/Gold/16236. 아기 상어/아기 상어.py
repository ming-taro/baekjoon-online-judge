import sys
from collections import deque


def is_able_to_move_fish():
  global N, current_size, eaten_fish, distance, baby_shark
  
  dy = [0, -1, 0, 1]
  dx = [-1, 0, 1, 0]  #↑, ←, ↓, →,
  
  queue = deque([baby_shark])

  move_board = [[0 for _ in range(N)] for _ in range(N)]
  points = [100, 100]
  board[baby_shark[0]][baby_shark[1]] = 0
  min_distance = 400

  while queue:
    row, col = queue.popleft()
    
    if (board[row][col] < current_size
        and board[row][col] > 0):
      if (move_board[row][col] < min_distance
          or move_board[row][col] == min_distance and (row < points[0] or row == points[0] and col < points[1])):
        points = [row, col]
        min_distance = move_board[row][col]
        
    for index in range(4):
      next_row = row + dx[index]
      next_col = col + dy[index]
      
      if (next_row >= 0 and next_row < N
          and next_col >= 0 and next_col < N
          and board[next_row][next_col] <= current_size
          and move_board[next_row][next_col] == 0):
        move_board[next_row][next_col] = move_board[row][col] + 1
        queue.append([next_row, next_col])
  
  if points[0] == 100 and points[1] == 100:      #더 이상 먹을 물고기가 없는 경우
    return False

  baby_shark = points
  board[baby_shark[0]][baby_shark[1]] = 0    #먹은 물고기 자리를 비움
  eaten_fish += 1                            #먹은 물고기 수
  distance += move_board[baby_shark[0]][baby_shark[1]]  #총 이동거리

  if eaten_fish == current_size:
    eaten_fish = 0
    current_size += 1
  
  return True


N = int(sys.stdin.readline())
board = []
baby_shark = []

for row in range(N):
  board.append(list(map(int, sys.stdin.readline().split(" "))))
  
  for col in range(N):
    fish = board[row][col]
    
    if fish == 9:
      baby_shark = [row, col]

current_size = 2
eaten_fish = 0
distance = 0

while True:
  if is_able_to_move_fish() == False:
    break

print(distance)