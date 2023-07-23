from collections import deque

def solution(priorities, location):
    maxList = sorted(priorities)
    maxPriority = maxList.pop()
    order = 1
    
    for index in range(len(priorities)):
        priorities[index] = [index, priorities[index]]
    
    priorities = deque(priorities)
    
    while priorities:
        priority = priorities.popleft()     #(index, 우선순위)
        
        if priority[1] == maxPriority:
            if priority[0] == location: 
                return order
            
            maxPriority = maxList.pop()
            order += 1
        else:
            priorities.append(priority)