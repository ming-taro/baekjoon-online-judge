import re
from collections import defaultdict
from itertools import combinations

def solution(orders, course):
    answer = []
    size = len(orders)
    
    orders.sort(key=lambda x:len(x))   #메뉴 길이가 짧은 순서대로 정렬
    
    orders_set = []  #각 메뉴별 집합 리스트
        
    for i in range(size):
        orders_set.append(set(orders[i]))

    result = {}     #음식개수별로 counter리스트를 만듦
    for c in course:
        result[c] = defaultdict(int)
        
    for i in range(size - 1):
        for j in range(i + 1, size):
            menu =  compare_string(orders_set[i], orders_set[j])  #두 사람의 겹치는 코스
            if menu == '' or len(menu) < 2:  #메뉴가 2개 이상이어야 함
                continue
            
            for count in course:
                for m in combinations(menu, count):   #해당 메뉴로 가능한 모든 음식 조합 개수 세기(course 리스트의 값들 만큼)
                    menu_comb = "".join(sorted(m))
                    result[len(menu_comb)][menu_comb] += 1  #result에서 해당하는 메뉴개수의 counter배열에 음식 개수 + 1 업데이트
        
    for menu_count in result:  #모든 조합결과를 비교해봄
        if len(result[menu_count]) == 0:
            continue
            
        max_count = max(result[menu_count].values())  #해당 메뉴개수의 dict에서 가장 큰 값
        
        for menu, count in result[menu_count].items():
            if count == max_count:  #가장 많이 주문한 메뉴에 해당하면 최종주문목록에 추가함
                answer.append(menu)
    
    return sorted(answer)

def compare_string(first, second):
    result = ''
    
    for i in first:
        if i in second:
            result += i

    return "".join(sorted(result))