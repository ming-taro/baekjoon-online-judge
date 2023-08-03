def solution(progresses, speeds):
    answer = []
    days = []
    size = len(progresses)
    
    for index in range(size):
        remain = 100 - progresses[index]
        days.append(remain//speeds[index])
        
        if remain % speeds[index] > 0:
            days[index] += 1
    
    index = 0
    
    while index < size:
        today = days[index]
        task = 0
        
        while index < size and days[index] <= today:
            task += 1
            index += 1
        
        answer.append(task)
    
    return answer

#[7, 3, 9] -> (7일)7, 3 / (9일)9
#[5, 10, 1, 1, 20, 1] -> (5일)5 / (10일)10, 1, 1 / (20일)20, 1