import sys

N, M, R = map(int, sys.stdin.readline().rstrip().split(" "))

board = []

for _ in range(N):
  board.append(list(map(str, sys.stdin.readline().rstrip().split(" "))))

for index in range(R):
  for line in range(min(M, N)//2):
    tmp = board[line][line]
    
    for col in range(line, M - 1 - line):     #윗줄
      board[line][col] = board[line][col + 1]
    for row in range(line, N - 1 - line):     #오른쪽줄
      board[row][M - 1 - line] = board[row + 1][M - 1 - line]
    for col in range(M - 1 - line, line, -1): #아랫줄
      board[N - 1 - line][col] = board[N - 1 - line][col - 1]
    for row in range(N - 1 - line, line, -1): #왼쪽줄
      board[row][line] = board[row - 1][line]
      
    board[line + 1][line] = tmp
  
for i in range(N):
  print(" ".join(board[i]))