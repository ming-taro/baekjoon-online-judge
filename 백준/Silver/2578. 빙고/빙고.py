import sys

board = {}

for i in range(5):
    num = list(map(int, sys.stdin.readline().rstrip().split()))
    for j in range(5):
        board[num[j]] = (i, j)

bingo = [[False for i in range(5)] for i in range(5)]
index = 0


def check_bingo():
    bingo_count = 0

    diagonal1 = 0
    diagonal2 = 0

    for i in range(5):
        col_count = 0
        row_count = 0

        if bingo[i][i]:  # 오른쪽 아래를 향하는 대각선 빙고
            diagonal1 += 1
        if bingo[i][4 - i]:  # 오른쪽 위를 향하는 대각선 빙고
            diagonal2 += 1

        for j in range(5):
            if bingo[i][j]:  # 가로 빙고
                row_count += 1
            if bingo[j][i]:  # 세로 빙고
                col_count += 1

        bingo_count += 1 if row_count == 5 else 0
        bingo_count += 1 if col_count == 5 else 0

    bingo_count += 1 if diagonal1 == 5 else 0
    bingo_count += 1 if diagonal2 == 5 else 0

    if bingo_count >= 3:
        return True
    return False


is_bingo = False
bingo_index = 0

for i in range(5):
    num = list(map(int, sys.stdin.readline().rstrip().split()))
    for j in range(5):
        x = board[num[j]][0]
        y = board[num[j]][1]
        bingo[x][y] = True
        index += 1

        if is_bingo == False and check_bingo():
            bingo_index = index
            is_bingo = True

print(bingo_index)