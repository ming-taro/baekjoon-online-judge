import sys
from collections import deque


def bfs(x, y):
    q = deque()

    q.append([x, y])
    visited[x][y] = 1

    while q:
        x, y = q.popleft()

        for index in range(4):
            next_x = x + dx[index]
            next_y = y + dy[index]

            if (
                next_x >= 0
                and next_y >= 0
                and next_x < N
                and next_y < M
                and path[next_x][next_y] == 1
            ):
                if visited[next_x][next_y] == 0:
                    q.append([next_x, next_y])
                    visited[next_x][next_y] = visited[x][y] + 1


N, M = map(int, sys.stdin.readline().rstrip().split())
path = []

for _ in range(N):
    path.append(list(map(int, sys.stdin.readline().rstrip())))

visited = [[0] * M for _ in range(N)]

dx = [0, 0, -1, 1]  # 좌측 상단부터 (0, 0) / →, ←, ↑, ↓
dy = [1, -1, 0, 0]

bfs(0, 0)

print(visited[N - 1][M - 1])
