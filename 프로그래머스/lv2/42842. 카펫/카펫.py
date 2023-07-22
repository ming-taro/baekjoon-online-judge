def solution(brown, yellow):
    answer = []  #가로X세로
    area = brown + yellow
    
    for number in range(1, area//2 + 1):
        if area%number != 0:
            continue
            
        row = number
        col = area//number
        
        yellow_count = (row - 2)*(col - 2)
        brown_count = 2*(row + col - 2)
        
        if (yellow_count == yellow and brown_count == brown):
            return [col, row]
    
    return answer