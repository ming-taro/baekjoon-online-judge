import heapq

def solution(scoville, K):
    answer = 0
    
    heapq.heapify(scoville)
    
    while scoville:
        if scoville[0] >= K:
            return answer
        
        if len(scoville) == 1:
            answer = -1
            break
        
        result = heapq.heappop(scoville) + heapq.heappop(scoville)*2
        heapq.heappush(scoville, result)
        
        answer += 1
    
    return answer