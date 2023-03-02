from collections import deque

def solution(people, limit):
    answer = 0
    
    deq = deque(sorted(people, reverse=True))
    
    while deq:
        people1 = deq.popleft()
        people2 = deq.pop() if deq else 0
        
        if people1 + people2 > limit:
            deq.append(people2)
            
        answer += 1
    
    return answer