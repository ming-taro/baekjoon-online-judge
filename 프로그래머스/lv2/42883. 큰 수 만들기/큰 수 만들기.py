def solution(number, k):
    answer = ''
    
    result = []
    
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