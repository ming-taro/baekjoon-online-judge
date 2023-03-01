def solution(answers):
    answer = []
    
    count = [0]*3
    index = 0
    
    student_1 = [1, 2, 3, 4, 5]
    student_2 = [2, 1, 2, 3, 2, 4, 2, 5]
    student_3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    
    for ans in answers:
        if student_1[index%5] == ans:
            count[0] += 1
        if student_2[index%8] == ans:
            count[1] += 1
        if student_3[index%10] == ans:
            count[2] += 1
        index += 1
    
    max_value = max(count)
    
    for i in range(3):
        if count[i] == max_value:
            answer.append(i + 1)
    
    return answer