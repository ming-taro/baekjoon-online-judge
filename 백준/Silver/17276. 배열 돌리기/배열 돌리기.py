import sys

def move_number(value, n, diagonal_number):
  if diagonal_number == 0:
    for index in range(n):
      board[index][index] = value[index]
  elif diagonal_number == 1:
    for index in range(n):
      board[index][(n + 1)//2 - 1] = value[index]
  elif diagonal_number == 2:
    for index in range(n):
      board[index][ n - index - 1] = value[index]
  elif diagonal_number == 3:
    for index in range(n):
      board[(n + 1)//2 - 1][n - index - 1] = value[index]
  elif diagonal_number == 4:
    for index in range(n):
      board[n - index - 1][n - index - 1] = value[index]
  elif diagonal_number == 5:
    for index in range(n):
      board[n - index - 1][(n + 1)//2 - 1] = value[index]
  elif diagonal_number == 6:
    for index in range(n):
      board[n - index - 1][index] = value[index]
  else:
    for index in range(n):
      board[(n + 1)//2 - 1][index] = value[index]

global board

T = int(sys.stdin.readline().rstrip())

for _ in range(T):
  n, d = map(int, sys.stdin.readline().rstrip().split(" "))
  d %= 360
  
  board = []
  
  for _ in range(n):
    board.append(list(map(int, sys.stdin.readline().rstrip().split(" "))))

  main_diagonal = [board[index][index] for index in range(n)]  #주대각선
  center_col = [board[index][(n + 1)//2 - 1] for index in range(n)]     #가운데 열
  sub_diagonal = [board[index][n - index - 1] for index in range(n)]   #부대각선
  center_row = [board[(n + 1)//2 - 1][n - index - 1] for index in range(n)]     #가운데 행
  
  d = 360 + d if d < 0 else d  #ex) 45도와 -315도 회전결과는 같다

  move_number(main_diagonal, n, d//45%8)
  move_number(center_col, n, (d//45 + 1)%8)
  move_number(sub_diagonal, n, (d//45 + 2)%8)
  move_number(center_row, n, (d//45 + 3)%8)
  
  #.join()은 리스트값이 문자열인 경우에만 가능
  for row in range(n):
    for col in range(n):
      print(board[row][col], end=" ")
    print()