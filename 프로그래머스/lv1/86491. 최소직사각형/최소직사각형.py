def solution(sizes):
    answer = 0
    
    max_x = 0
    max_y = 0
    
    for size_list in sizes:
        x = max(size_list)
        y = min(size_list)
        
        max_x = max(max_x, x)
        max_y = max(max_y, y)
        
    answer = max_x*max_y 
    
    return answer