import math
from itertools import permutations

def is_prime(number):
    if number < 2:
        return False
    if number == 2:
        return True
    
    for i in range(2, number):
        if number%i == 0:
            return False
    
    return True

def solution(numbers):
    answer = 0
    
    numbers = [number for number in numbers]
    num_list = set()
    
    for i in range(1, len(numbers) + 1):
        perm_list = list(permutations(numbers, i))
        for num in perm_list:
            num_list.add(int("".join(num)))
                         
    for num in num_list:
        print(num)
        if is_prime(num):
            answer += 1
        
    return answer