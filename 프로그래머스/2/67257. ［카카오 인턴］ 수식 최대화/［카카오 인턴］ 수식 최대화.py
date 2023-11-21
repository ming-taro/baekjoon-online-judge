def solution(expression):
    answer = 0
    result = []
    
    standard = [['*', '+', '-'], ['*', '-', '+'], ['+', '*', '-'], ['+', '-', '*'], ['-', '*', '+'], ['-', '+', '*']]
    
    for s in standard:
        result.append(abs(calc(expression, s)))
    
    print(result)
    return max(result)

def calc(expression, standard):
    a = expression.replace('*', ' ').replace('+', ' ').replace('-', ' ')
    numbers = a.split(' ')
    
    operator = expression
    
    for i in range(10):
        ch = (chr(ord('0') + i))
        operator = operator.replace(ch, '')
    
    for s in standard:
        while(True):
            index = operator.find(s)

            if index == -1:
                break

            result = calcNum(numbers[index], numbers[index + 1], s)

            numbers = creatList(index, numbers, result)
            operator = sliceString(index, operator)

    return numbers[0]
    
def calcNum(first, second, operator):
    if operator == '+':
        return int(first) + int(second)
    
    if operator == '-':
        return int(first) - int(second)

    return int(first) * int(second)

def creatList(index, numbers, insertNumber):
    left = []
    right = []
    for i in range(len(numbers)):
        if i < index:
            left.append(numbers[i])
        elif i > index + 1:
            right.append(numbers[i])
            
    return left + [insertNumber] + right    
    
def sliceString(index, operator):
    result = ''
    for i in range(len(operator)):
        if i != index:
            result += operator[i]
    return result
        
    
    
#return 전달받은 수식에 포함된 연산자의 우선순위를 재정의하여 가장 큰 숫자 만들기

