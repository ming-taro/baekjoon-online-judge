import sys
from collections import deque


def mark_bomb(row, col, sec):
    move_row = [1, 0, -1, 0]
    move_col = [0, 1, 0, -1]

    q = deque()
    q.append([row, col])
    board[row][col] = "."

    while q:
        row, col = q.popleft()

        for index in range(4):
            next_row = row + move_row[index]
            next_col = col + move_col[index]

            if (
                next_row < 0
                or next_col < 0
                or next_row >= R
                or next_col >= C
                or board[next_row][next_col] == "."
            ):
                continue

            if board[next_row][next_col] == sec or board[next_row][next_col] == "O":
                q.append([next_row, next_col])

            board[next_row][next_col] = "."


R, C, N = map(int, sys.stdin.readline().rstrip().split())

board = []
sec = 1

for _ in range(R):
    board.append(list(sys.stdin.readline().rstrip()))

while True:
    sec += 1
    if sec > N:
        break

    for row in range(R):
        for col in range(C):
            if board[row][col] == ".":
                board[row][col] = sec  # 1초 동안 폭탄이 설치되지 않은 모든 칸에 폭탄 설치

    sec += 1
    if sec > N:
        break

    if sec - 3 < 0:
        continue

    for row in range(R):
        for col in range(C):
            if board[row][col] == "O" or board[row][col] == sec - 3:
                mark_bomb(row, col, sec - 3)

for row in range(R):
    for col in range(C):
        if board[row][col] == ".":
            print(".", end="")
        else:
            print("O", end="")
    print()