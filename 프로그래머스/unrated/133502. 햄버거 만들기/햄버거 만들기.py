from collections import deque

def solution(ingredient):
    answer = 0
    
    ingredient = deque(ingredient)
    ingredient_list = []
    
    index = 0
    
    while ingredient:
        ingredient_list.append(ingredient.popleft())
        
        if len(ingredient_list) >= 4 and ingredient_list[-4:] == [1, 2, 3, 1]:
            answer += 1
            del ingredient_list[-4:]
        
    return answer