import sys
from collections import deque


def is_within_range(row, col):
    if row >= 0 and col >= 0 and row < N and col < N:
        return True

    return False


def bfs(row, col):
    visited[row][col] = 1

    q = deque()
    q.append([row, col])
    count = 1

    while q:
        row, col = q.popleft()

        for i in range(4):
            next_row = row + dx[i]
            next_col = col + dy[i]

            if (
                is_within_range(next_row, next_col)
                and visited[next_row][next_col] == 0
                and house[next_row][next_col] == 1
            ):
                q.append([next_row, next_col])
                visited[next_row][next_col] = 1
                count += 1

    return count


N = int(sys.stdin.readline().rstrip())

house = []

for _ in range(N):
    house.append(list(map(int, sys.stdin.readline().rstrip())))

house_count = []

visited = [[0] * N for _ in range(N)]

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

for i in range(N):
    for j in range(N):
        if visited[i][j] == 0 and house[i][j] == 1:
            house_count.append(bfs(i, j))

house_count.sort()

print(len(house_count))

for count in house_count:
    print(count)
