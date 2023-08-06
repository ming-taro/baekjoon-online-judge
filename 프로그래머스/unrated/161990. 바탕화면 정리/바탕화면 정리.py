def solution(wallpaper):
    answer = []
    
    row_length = len(wallpaper)
    col_length = len(wallpaper[0])
    
    x_point = []
    y_point = []
    
    for row in range(row_length):
        for col in range(col_length):
            if wallpaper[row][col] == "#":
                x_point.append(row)
                y_point.append(col)
    
    x_point.sort()
    y_point.sort()
    
    return [x_point[0], y_point[0], x_point[-1] + 1, y_point[-1] + 1]  

#컴퓨터 바탕화면: 각 칸이 정사각형인 격자판
#wallpaper: 바탕화면의 상태
#가장 왼쪽 위 = (0, 0)
#빈칸: ".", 파일이 있는 칸: "#"
#드래그시 파일 선택 가능 / 선택된 파일들은 삭제 가능
#=>목표: 최소한의 이동거리를 갖는 한 번의 드래그로 모든 파일을 선택해서 한 번에 지우려고 함
#return 

#예제1)
# .#... 
# ..#..
# ...#.
#(0,1)~(3,4)까지의 격자를 드래그해야함
#가장 왼쪽의 파일위치 = (0,1) / 가장 위쪽의 파일위치 = (0,1)
#가장 오른쪽의 파일위치 = (2,3) / 가장 아래쪽의 파일위치 = (2,3)
#S = (가장 위쪽의 파일x값, 가장 왼쪽의 파일y값)
#  = (가장 작은 x값, 가장 작은 y값)
#E = (가장 아래쪽의 파일x값 + 1, 가장 오른쪽의 파일y값 + 1)
#  = (가장 큰 x값 + 1, 가장 작은 y값 + 1)