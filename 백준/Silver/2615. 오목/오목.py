import sys


def is_stone_same(row, col, stone):
    if row >= 0 and row < 19 and col >= 0 and col < 19 and board[row][col] == stone:
        return True
    return False


board = []

for _ in range(19):
    board.append(list(sys.stdin.readline().rstrip().split()))

dx = [1, 0, 1, 1]  # →, ↓, ↗, ↘
dy = [0, 1, -1, 1]

x_point = -1
y_point = -1
stone_count = 0

for row in range(19):
    for col in range(19):
        stone = board[row][col]

        if stone == '0':
            continue

        for k in range(4):
            stone_count = 0

            if (
                is_stone_same(row + dy[k], col + dx[k], stone)
                and is_stone_same(row - dy[k], col - dx[k], stone) == False  #다음 돌이 자신과 같고, 이전 돌이 자신과 같지 않은 경우
            ):
                for move in range(6):  # 연속적인 돌의 개수를 셈(육목의 가능성까지 고려해야 하므로 move = 5까지 검사)
                    if is_stone_same(row + dy[k] * move, col + dx[k] * move, stone):
                        stone_count += 1
                    else:
                        break

                if stone_count == 5:
                    x_point = col
                    y_point = row

        if stone_count == 5:
            break
    if stone_count == 5:
        break

if board[y_point][x_point] == '0':
    print(0)
else:
    print(board[y_point][x_point])
    print(y_point + 1, x_point + 1)