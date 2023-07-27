def move_right(index, queue_length):
    index += 1
    if index == queue_length:
        return 0
    return index

def solution(queue1, queue2):
    answer = -2
    
    queue1_sum = sum(queue1)
    queue2_sum = sum(queue2)
    
    if queue1_sum == queue2_sum:              #양쪽 큐의 합이 같음
        return 0 
    
    queue_sum = (queue1_sum + queue2_sum)//2  #한 큐의 합
    
    #계산할 수 없는 경우 : 큐 하나의 합보다 더 큰 원소가 존재하는 경우 or 합이 2로 나눠떨어지지 않는 경우
    if (max(queue1) > queue_sum or max(queue2) > queue_sum
        or (queue1_sum + queue2_sum) % 2 != 0):
        return -1
    
    queue = queue1 + queue2
    length = len(queue)
    
    queue1_index = [0, len(queue1) - 1]    #[start_index, end_index]
    queue2_index = [len(queue1), len(queue) - 1]
    
    move = 0
    
    while True:   
        if queue1_sum > queue2_sum:  #큐1의 첫번째 원소를 큐2의 마지막에 삽입
            queue1_sum -= queue[queue1_index[0]]
            queue2_sum += queue[queue1_index[0]]
            queue1_index[0] = move_right(queue1_index[0], length)
            queue2_index[1] = move_right(queue2_index[1], length)
        else:                          #큐2의 첫번째 원소를 큐1의 마지막에 삽입
            queue2_sum -= queue[queue2_index[0]]
            queue1_sum += queue[queue2_index[0]]
            queue2_index[0] = move_right(queue2_index[0], length)
            queue1_index[1] = move_right(queue1_index[1], length)
        move += 1
        
        if queue1_sum == queue2_sum:   #양쪽 큐의 합이 같음
            return move
        if (queue1_index[0] == 0 and queue1_index[1] == len(queue1) - 1
            or move >= 300000):        #원래대로 돌아온 경우
            return -1
    
    return answer

#길이가 같은 두 개의 큐
#두 큐의 원소 합이 같도록 만들려고 함
#pop + insert = 작업 1회
#예제1)
#q1 = [3, 2, 7, 2]
#q2 = [4, 6, 5, 1]
#두 큐의 원소 합이 30이므로 각 큐의 합을 15로 만들어야 함
#풀이)
#두 큐를 하나의 큐로 생각하고 합이 15가 되는 지점을 구한다면?
#1. [(3, 2, 7, 2,) / (4, 6, 5, 1)]
#0~3까지의 원소 합은 14, 4~7까지의 원소 합은 16 -> 왼쪽이 작으니까 오른쪽에서 하나 땡겨옴
#2. [(3, 2, 7, 2, 4), / (6, 5, 1)]
#0~4까지의 원소 합은 18, 5~7까지의 원소 합은 12 -> 오른쪽이 작으니까 왼쪽에서 하나 줌
#3. [3,) (2, 7, 2, 4,) / (6, 5, 1]
#1~4까지의 원소 합은 15, 5~7과 1의 원소 합은 15이므로 찾음!
#이동이 총 두 번 이루어졌으므로 return 2
#코드로 구현)
#큐1, 2의 시작점 인덱스를 가지고 있자 이때 
#계산할 수 없는 경우 : 큐 하나의 합보다 더 큰 원소가 존재하는 경우