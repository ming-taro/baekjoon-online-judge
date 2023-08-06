from collections import Counter

def solution(topping):
    answer = 0
    
    first_cake_list = Counter(topping[0:1])
    second_cake_list = Counter(topping[1:])
    
    for index in range(1, len(topping) - 1):
        if len(first_cake_list) == len(second_cake_list):
            answer += 1
        
        if topping[index] in first_cake_list:
            first_cake_list[topping[index]] += 1
        else:
            first_cake_list[topping[index]] = 1
        
        second_cake_list[topping[index]] -= 1
        
        if second_cake_list[topping[index]] == 0:
            second_cake_list.pop(topping[index])
    
    return answer

#롤케이크 크기와 토핑 개수상관X -> 각 조각에 동일한 가짓수의 토핑이 올라가야 함
#ex) 4가지 토핑 [1, 2, 3, 4]일 때 [1, 2, 1, 3, 1, 4, 1, 2]순서로
#토핑이 올려져있다면 ->[1, 2, 1, 3] / [1, 4, 1, 2]로 잘라야 각 3가지씩 맛볼 수 있음
#return 롤케이크를 공평하게 자르는 방법의 수