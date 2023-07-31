def solution(numbers, target):
    answer = 0
    sum_list = [0]
    
    for index in range(len(numbers)):
        sub_list = []
        for item in sum_list:
            item1 = item + numbers[index]
            item2 = item - numbers[index]
            
            if index == len(numbers) - 1:
                answer += 1 if item1 == target else 0
                answer += 1 if item2 == target else 0
            sub_list.append(item1)
            sub_list.append(item2)
            
        sum_list = sub_list

    return answer

#4, 1, 2, 1
#1. [4] -> [4]                     => [4, -4]
#2. [4, 1] / [4, -1]               => [5, 3, -3, -5]
#3. [4, 1, 2] [4, 1, -2]           => [7, 3, 5, 1]
#   / [4, -1, 2] [4, -1, -2]       
#4. [4, 1, 2, 1] [4, 1, 2, -1] [4, 1, -2, 1] [4, 1, -2, -1]   => [8, 6, ..]
#   / [4, -1, 2, 1] [4, -1, -2, -1] [4, -1, 2, 1] [4, -1, -2, -1]