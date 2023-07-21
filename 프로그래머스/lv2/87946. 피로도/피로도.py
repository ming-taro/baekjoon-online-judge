from itertools import permutations

def solution(k, dungeons):
    answer = 0
    dungeon_count = len(dungeons)
    
    index_list = [index for index in range(dungeon_count)]
    
    dungeon_list = list(permutations(index_list, dungeon_count))

    for dungeon in dungeon_list:
        dungeon_count = 0
        hp = k

        for number in dungeon:
            if hp < dungeons[number][0]:  #최소 필요 피로도 만족X
                break
            hp -= dungeons[number][1]     #소모 피로도만큼 감소
            dungeon_count += 1
        
        if answer < dungeon_count:
            answer = dungeon_count
    
    return answer