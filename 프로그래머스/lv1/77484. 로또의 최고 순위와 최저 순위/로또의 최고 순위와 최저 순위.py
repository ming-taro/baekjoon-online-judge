def solution(lottos, win_nums):
    match_count = 0
    zero_count = lottos.count(0)
    
    for num in win_nums:
        if num in lottos:
            match_count += 1
    
    max_rank = 7 - (match_count + zero_count)
    if max_rank >= 6:
        max_rank = 6
        
    min_rank = 7 - match_count if match_count > 1 else 6  
    
    return [max_rank, min_rank]