def solution(numbers, hand):
    answer = ''
    
    phone = {1: [0, 0], 2:[0, 1], 3:[0, 2], 4:[1, 0], 5:[1, 1], 6:[1, 2], 7:[2, 0], 8:[2, 1], 9:[2, 2], 0:[3, 1]}
    left = [3, 0]
    right = [3, 2]
    
    for number in numbers:
        position = phone[number]
        
        if(number == 1 or number == 4 or number == 7):
            answer += 'L'
            left = position
        elif(number == 3 or number == 6 or number == 9):
            answer += 'R'
            right = position
        else:
            right_distance = abs(position[0] - right[0]) + abs(position[1] - right[1])
            left_distance = abs(position[0] - left[0]) + abs(position[1] - left[1])
            if right_distance < left_distance or (right_distance == left_distance and hand == "right"):
                right = position
                answer += 'R'
            else:
                left = position
                answer += 'L'
                
            
    return answer

# def move():
#     dx = []  #상하좌우

#1,4,7 -> 왼손
#3,6,9 -> 오른손