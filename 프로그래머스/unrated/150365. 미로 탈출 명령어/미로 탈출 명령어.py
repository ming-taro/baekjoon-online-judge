def solution(n, m, x, y, r, c, k):
    #(k - 최소이동) % 2 != 0 -> impossible
    if (k - abs(x - r) - abs(y - c)) % 2 != 0 or k < abs(x - r) + abs(y - c):
        return "impossible"
    
    #d < l < r < u
    #1. 위아래 이동
    down = 'd'*(n - x)  #맨 밑으로 최대한 down
    up = 'u'*(n - r)    #E위치까지 up
        
    move_count = (n - x) + (n - r)
    difference = (move_count - (k - abs(y - c)))//2
    
    #위아래 이동 횟수 < (k - S와 E의 가로거리)
    if difference > 0:
        down = down[:len(down) - difference]  #초과되는 이동횟수만큼을 빼준다(down + up)
        up = up[:len(up) - difference]
    
    move_count = len(down) + len(up)
    
    #2. 왼오 이동
    #ex) S..E의 경우 right이동값이 E의 (y좌표 - 1) = 3이므로 각각의 이동에 대해 -1을 해주어야 한다
    left = 'l'*(y - 1)    #맨 왼쪽으로 최대한 left
    right = 'r'*(c - 1)   #E위치까지 right

    move_count += y + c - 2 
    difference = (move_count - k)//2
    
    if difference > 0:
        left = left[:len(left) - difference]
        right = right[:len(right) - difference]
    
    move_count = len(down) + len(left) + len(right) + len(up)
    
    print(down, left, right, up)
    
    #3. 총 이동 = down + left + rl + right + up
    return down + left + 'rl'*((k - move_count)//2) + right + up
    
    
#n*m 격자 미로,  d < l < r < u
#(x, y)에서 출발해 (r, c)로 이동해서 탈출 -> 이동거리 k
#.S.    k=4, S=(0,1), E=(2,2)
#...    가로거리=|1-2|=1
#..E    세로거리=|2-0|=2, 최소이동3회
#       (k-최소이동)%2 != 0 -> impossible

#위아래 이동을 다 끝내고 왼오 이동을 한다면?
#ddd + uu, 즉 아래이동을 최대로 하고  E위치까지 u를 한다
#그런데 ud보다 rl 또는 lr이 우선이므로 중간에 가능한 ud를 신경쓸 필요X
#1. 위아래 이동 : d를 최대로 내려갔다가, u로 E위치까지 끌어올림
#   횟수는 (k - S와 E의 가로거리)보다 작은 범위 내에서
#   (결국 d의 최대 개수는 격자 높이 - 1)
#2. 왼오 이동 : l을 최대로 이동했다가 r로 E위치까지 끌어당김
#   횟수는 위아래 이동 후 남은 횟수 안에서, 단 rrll보다 rlrl이 우선이므로
#   rl*(남은횟수//2)를 더해주면 됨
#3. 총 이동은 d와 u 사이에 l, r이동을 넣어주면 됨
#....   
#..S.  k=11일 때,
#E...  위아래: 11-2=9 이내, d*3번 이동 + u*2번 = E와 같은 x값 -> 이동횟수 6번남음
#....  왼오: 왼쪽 끝까지 이동 l*2번 이동 -> 이동횟수 4번남음
#....  나머지: rl*(남은횟수 4번//2 = 2)
#위아래 ddduu, 왼오llrlrl => dddllrlrluu 완성!