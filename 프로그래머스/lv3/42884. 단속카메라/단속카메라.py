def solution(routes):
    answer = 1
    start = -30000
    end = 30000
    
    routes.sort(key=lambda x: x[0])
    
    print(routes)
    
    for route in routes:
        if route[0] > end:
            answer += 1
            start = route[0]
            end = route[1]
            continue
        
        if route[0] >= start:
            start = route[0]
        else: 
            answer += 1
            start = route[0]
            end = route[1]
            continue
        
        if route[1] <= end:
            end = route[1]
    
    return answer
