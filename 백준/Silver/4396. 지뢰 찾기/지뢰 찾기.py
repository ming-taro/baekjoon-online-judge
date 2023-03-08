import sys

n = int(sys.stdin.readline().rstrip())

bomb = []
bomb_list = []
board = [["." for i in range(n)] for j in range(n)]

for y in range(n):
    bomb.append(sys.stdin.readline().rstrip())
    for x in range(n):
        if bomb[-1][x] == "*":
            bomb_list.append((x, y))


def get_start_index(point):
    return 0 if point == 0 else point - 1


def get_end_index(point, n):
    return n - 1 if point == n - 1 else point + 1


def check_bomb(x, y, n):
    count = 0

    for row in range(get_start_index(y), get_end_index(y, n) + 1):
        for col in range(get_start_index(x), get_end_index(x, n) + 1):
            if bomb[row][col] == "*":
                count += 1

    board[y][x] = str(count)


flag = True

for y in range(n):
    input = sys.stdin.readline().rstrip()

    for x in range(n):
        if flag and input[x] == "x" and bomb[y][x] == "*":
            for b in bomb_list:
                board[b[1]][b[0]] = "*"
            flag = False
        if board[y][x] != "*" and input[x] == "x":
            check_bomb(x, y, n)

for line in board:
    print("".join(line))