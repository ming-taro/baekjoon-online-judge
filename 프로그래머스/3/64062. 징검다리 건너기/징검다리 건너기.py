def solution(stones, k):
    answer = 0

    l = 1
    r = max(stones)  #돌의 숫자 = 건널 수 있는 사람의 숫자 -> 가장 최대로 건널 수 있는 사람의 수를 가정하고 시작함(max명 만큼 건널 수 있다)

    print("시작:", l, r)
    
    #target = 건널 수 있는 사람 수
    #stone을 돌면서 k구간 안에서  target
    while l <= r:
        mid = (l + r) // 2    #mid명이 최대로 건널 수 있는 수 인지 알 수 있는 방법?
        count = 0
        
        for stone in stones: 
            if stone < mid:   #돌다리를 돌면서 현재 건널 수 있는 사람 수보다 적은 경우, 즉 건너지 못하는 연속된 돌을 count하는데 이 돌이 k개가 되면 mid명으로는 돌다리를 건너지 못함
                count += 1
            else:
                count = 0     #mid명이 건널 수 있는 구간이 나옴
            
            if count == k:
                break
    
        if count == k:  #현재 mid명으로는 건널 수 없는 돌다리
            r = mid - 1
        else:
            l = mid + 1   #현재 mid명으로 건널 수 있는 돌다리 -> 최솟값을 1만큼 높임
    
    return l - 1

#완탐으로 해결
# def solution(stones, k):
#     answer = 0
#     max_path = []
#     last_path = 0
    
#     for i in range(0, len(stones) - k + 1, k):
#         if i + k <= len(stones):
#             max_path.append(max(stones[i:i + k]))
        
#     return min(max_path)
#마지막 건너기에서 남은 돌의 개수가 k개보다 작은 경우는 고려할 필요가X(만약 마지막 남은 돌이 훨씬 많은 경우 -> 지금까지 건너온 길 중 각 구간을 최대로 통과하는 사람들 중 가장 적은 경우가 돌다리를 건널 수 있는 경우다. (0~4)구간에 있는 경우 가장 마지막사람이 건너고 나면 해당 구간의 숫자가 모두 0이 된다. 따라서 각 구간에 대한 최댓값을 구하고, 전체에서 가장 작은 값이 돌다리를 건널 수 있는 최대 인원수다