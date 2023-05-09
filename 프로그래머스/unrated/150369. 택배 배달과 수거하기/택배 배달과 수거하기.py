def solution(cap, n, deliveries, pickups):
    answer = 0
    
    box = 0
    pick = 0
    
    for i in range(-1, n*(-1) - 1, -1):
        box += deliveries[i]
        pick += pickups[i]
        
        while box > 0 or pick > 0:
            box -= cap
            pick -= cap
            answer += (n + i + 1)*2

    return answer