def is_point_within_park(x, y, width, height):
    if x < 0 or y < 0 or x >= width or y >= height:
        return False
    return True

def solution(park, routes):
    current_point = [0, 0]
    
    dx = [1, -1, 0, 0]   #E, W, N, S 
    dy = [0, 0, -1, 1]   
    
    direction_index = {'E': 0, 'W': 1, 'N': 2, 'S': 3}

    width = len(park[0])
    height = len(park)
    
    for y in range(height):
        x = park[y].find('S')
        if x >= 0:
            current_point = [y, x]
            break
        
    for route in routes:
        direction, move_count = route.split(" ")
        move_index = direction_index[direction]
        
        next_x = current_point[1]
        next_y = current_point[0]
        
        for count in range(int(move_count)):
            next_x += dx[move_index]
            next_y += dy[move_index]
            
            if (is_point_within_park(next_x, next_y, width, height) == False
                or park[next_y][next_x] == "X"):
                next_x = current_point[1]
                next_y = current_point[0]
                break
        
        current_point = [next_y, next_x]
        
    return current_point

#지나는 길: 'O', 장애물: 'X'
#명령수행 전 검사 조건: 주어진 방향 이동시 공원을 벗어나는가, 장애물을 만나는가
#->위의 조건 중 하나라도 무시하면 해당 명령어를 무시하고 다음 명령을 수행함
#park: 공원 -> S(시작지점), O(이동가능), X(장애물)
#routes: 로봇 강아지가 수행할 명령(E, W, S, N)
#return [모든 명령 수행 후 높인 위치의 y좌표, x좌표]