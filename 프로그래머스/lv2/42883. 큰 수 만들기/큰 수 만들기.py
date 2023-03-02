def solution(number, k):
    answer = ''
    
    number_list = [num for num in reversed(number)]
    result = []
    index = 0
    
    for num in number:
        while k > 0 and result and result[-1] < num:
            result.pop()
            k -= 1

        result.append(num)
    
    while k > 0:
        result.pop()
        k -= 1
    
    answer = "".join(result)
        
    return answer