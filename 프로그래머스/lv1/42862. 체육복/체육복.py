def solution(n, lost, reserve):
    answer = 0
    
    student = [1]*n
    
    for i in lost:
        index = i - 1
        student[index] = 0   #체육복을 도난당한 학생이 가진 체육복 = 0
    
    for i in reserve:
        index = i - 1
        student[index] += 1   #체육복 여분이 있는 학생의 체육복 +1

    for i in range(n):
        if student[i] != 0:
            continue
        
        if i == 0 and student[1] == 2:  #0번은 1번에게만 빌릴 수 있음
            student[1] -= 1
            student[0] += 1
        elif i == n - 1 and student[i - 1] == 2:  #마지막 번호는 전 번호 친구에게만 빌릴 수 있음
            student[i - 1] -= 1
            student[i] += 1
        elif i > 0 and i < n - 1:
            if student[i - 1] == 2:
                student[i - 1] -= 1
                student[i] += 1
            elif student[i + 1] == 2:
                student[i + 1] -= 1
                student[i] += 1
            
    for i in student:
        if i > 0:
            answer += 1
    
    return answer