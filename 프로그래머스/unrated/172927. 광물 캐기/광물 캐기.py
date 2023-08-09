def calc_piro(minerals, piro):
    total = 0

    for index in range(3):
        total += minerals[index]*piro[index]
    
    return total

def solution(picks, minerals):
    if len(minerals) > sum(picks)*5:
        minerals = minerals[:sum(picks)*5]
        
    total = 0
    pick_index = 0
    
    piro = [[1, 1, 1], [5, 1, 1], [25, 5, 1]]
    mineral_index = {"diamond": 0, "iron": 1, "stone": 2}
    
    mineral_count = []
    
    for start_index in range(0, len(minerals), 5):
        #마지막 그룹의 광물 개수가 5보다 작은 경우 -> minerals의 길이 - 1이 마지막 광물의 인덱스값
        if start_index + 4 > len(minerals) - 1:
            end_index = len(minerals) - 1
        else:
            end_index = start_index + 4

        mineral_count.append([0, 0, 0])
            
        for index in range(start_index, end_index + 1):
            type_number = mineral_index[minerals[index]]
            mineral_count[-1][type_number] += 1
    
    mineral_count.sort(key=lambda x: (-x[0], -x[1]))
    
    for mineral in mineral_count:
        while pick_index < 3 and picks[pick_index] == 0:
            pick_index += 1
        if pick_index >= 3:
            break
        
        picks[pick_index] -= 1
        total += calc_piro(mineral, piro[pick_index])
        
    return total

#각 곡괭이는 최대 5개의 광물을 캘 수 있음
#한 번 사용한 곡괭이는 사용할 수 없을 때까지 사용
#광물은 주어진 순서대로만 캘 수 있음
#모든 광물을 캠 or 더 사용할 곡괭이가 없을 때까지 과정을 반복
#picks: 곡괭이 개수
#minerals: 광물 순서
#return 최소한의 피로도

#1. 광물을 앞에서부터 5개씩 끊어서 다이아, 철, 돌 개수를 각각 구한 리스트를 만듦 -> mineral_count
#2. mineral_count 리스트의 광물들을 다이아 > 철 > 돌이 많은 순서로 정렬함
#3. 위에서 구한 mineral_count를 순회하며 다이아 -> 철 -> 돌 곡괭이 순서로 사용하면서 피로도를 구함
#(mineral_count로 앞에서부터 5개씩 광물을 끊었기 때문에 광물순서를 위반하지 않음)