import sys
from collections import deque


def is_within_range(row, col):
    if (row >= 0 and row < N 
        and col >=0 and col < N):
        return True
    return False


def add_water(row, col):
    dx = [-1, -1, 1, 1]   #↖, ↙, ↗, ↘
    dy = [-1, 1, -1, 1]
    water = 1
    
    for index in range(4):
        next_row = row + dy[index]
        next_col = col + dx[index]
        
        if (is_within_range(next_row, next_col)
            and waters[next_row][next_col] > 0):
            water += 1
            if (waters[row][col] == 0
                and (index == 0 or index == 2)  #↖, ↗
                and clouds[next_row][next_col]):
                waters[next_row][next_col] += 1

    waters[row][col] += water


def mark_cloud(clouds, marker: bool):
    cloud = [[not marker for _ in range(N)] for _ in range(N)]
    
    for point in clouds:   #이전에 구름이 있던 자리는 제외함(연달아 구름생성X)
        cloud[point[0]][point[1]] = marker
    
    return cloud


def remove_cloud():
    for row in range(N):
        for col in range(N):
            if clouds[row][col] or waters[row][col] < 2:
                clouds[row][col] = False
            else:
                clouds[row][col] = True
                waters[row][col] -= 2


def calculate_index(index):
    if index < 0:
        index = N + index
    return index % N


def move_cloud(direction, move):
    cloud = [[False for _ in range(N)] for _ in range(N)]
    dx = dy = 0
    
    if direction == 1:    #←, ↖, ↑, ↗, →, ↘, ↓, ↙
        dx = -1
    elif direction == 2:
        dx = -1; dy = -1;
    elif direction == 3:
        dy = -1 
    elif direction == 4:
        dx = 1; dy = -1;
    elif direction == 5:
        dx = 1
    elif direction == 6:
        dx = 1; dy = 1
    elif direction == 7:
        dy = 1
    else:
        dx = -1; dy = 1
    
    dx *= move; dy *= move;
    
    for row in range(N):
        for col in range(N):
            if clouds[row][col]:
                next_row = calculate_index(row + dy)
                next_col = calculate_index(col + dx)
                cloud[next_row][next_col] = True

    return cloud


N, M = map(int, sys.stdin.readline().rstrip().split())
waters = []

for _ in range(N):
    waters.append(list(map(int, sys.stdin.readline().rstrip().split())))

clouds = [[N - 1, 0], [N - 1, 1], [N - 2, 0], [N - 2, 1]]
clouds = mark_cloud(clouds, True)    #초기 구름 생성
    
for _ in range(M):
    d, s = map(int, sys.stdin.readline().rstrip().split())
    clouds = move_cloud(d, s)      #구름 이동
    
    for row in range(N):           #비 내림 -> 물 저장
        for col in range(N):
            if clouds[row][col]:
                add_water(row, col)
    
    remove_cloud()   #직전과 다른 곳에 구름 생성, 물 양이 2 미만인 곳은 구름X

total = 0
for w in waters:
    total += sum(w)
print(total)

#d방향으로 s칸만큼 이동한 곳에 물의 양 1만큼 증가
#이후 구름이 모두 사라짐
#물이 증가한 칸 :  대각선 방향 거리가 1인 칸의 물 바구니 수 만큼 물의 양 증가
#물의 양이 2 이상인 모든 칸에 구름이 생김 + 물의양 -2(직전에 구름이 사라진 칸이 아니어야 함)