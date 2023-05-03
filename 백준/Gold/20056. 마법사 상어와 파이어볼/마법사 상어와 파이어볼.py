import sys
from collections import deque

def calc_index(index):
    while index < 0:
        index += N
    return index % N


def get_direction_list(direction):
    even = 0
    
    for d in direction: #방향 짝수 count
        if d%2 == 0:   
            even += 1

    if even == 0 or even == len(direction):  #모두 짝수 or 모두 홀수
        return [0, 2, 4, 6]
    return [1, 3, 5, 7]


def divide_fire(row, col): #4개의 fire ball 생성 + 현위치 fire ball 소멸
    dx = [0, 1, 1, 1, 0, -1, -1, -1]  #↑  ↗  →  ↘  ↓ ↙  ←  ↖ 
    dy = [-1, -1, 0, 1, 1, 1, 0, -1]
    
    fire_list = fires[row][col]
    
    m = s = 0
    direction = set()
    
    for fire in fire_list: #fire = [m, s, d]
        m += fire[0]
        s += fire[1]
        direction.add(fire[2])
    
    m //= 5
    s = s//len(fire_list)
    direction = get_direction_list(direction)  #나눠지는 4개의 fire ball에 대한 질량, 속력, 방향
    
    fires[row][col] = []  #현 위치의 fire ball 소멸
    
    if m == 0:   #질량이 0인 fire ball은 소멸
        return
    
    for d in direction:   #나눠지는 4개의 fire ball에 대한 정보 리스트 저장
        fires[row][col].append([m, s, d]) 


def move_fire(row, col):
    dx = [0, 1, 1, 1, 0, -1, -1, -1]  #↑  ↗  →  ↘  ↓ ↙  ←  ↖ 
    dy = [-1, -1, 0, 1, 1, 1, 0, -1]
    
    fire_list = fires[row][col]  #fire = [m, s, d]
    
    while fire_list:
        fire = fire_list.pop()
    
        next_row = calc_index(row + dy[fire[2]]*fire[1])
        next_col = calc_index(col + dx[fire[2]]*fire[1])
        
        visited_point.add((next_row, next_col))
        fire_after_move[next_row][next_col].append(fire)


N, M, K = map(int, sys.stdin.readline().rstrip().split())

fires = [[[] for _ in range(N)] for _ in range(N)]
point_to_visit = set()

for _ in range(M):    #초기 fire ball 정보 저장
    r, c, m, s, d = map(int, sys.stdin.readline().rstrip().split())
    fires[r - 1][c - 1].append([m, s, d])
    point_to_visit.add((r - 1, c - 1))  #처음으로 방문할, 즉 fire가 있는 위치 set

for _ in range(K):
    visited_point = set()
    if len(point_to_visit) == 0:  #모든 fire가 소멸된 경우
        break
    
    fire_after_move = [[[] for _ in range(N)] for _ in range(N)]
    for point in point_to_visit:
        move_fire(point[0], point[1])            #1. fire 이동
    fires = fire_after_move
    
    for point in visited_point:
        if len(fires[point[0]][point[1]]) > 1:
            divide_fire(point[0], point[1])      #2. 4개의 불로 나눔(나뉘어진 불은 이동X, 그 자리에 존재)
    
    point_to_visit = visited_point               #3. 이동 후 불의 위치를 저장한 집합은 다음에 검사할 목록이 됨
    
total = 0

for point in visited_point:
    row, col = point
    for fire in fires[row][col]:
        total += fire[0]
print(total)