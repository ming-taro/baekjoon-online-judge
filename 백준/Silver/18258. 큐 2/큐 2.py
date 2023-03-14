import sys
from collections import deque

N = int(sys.stdin.readline().rstrip())
que = deque()

for i in range(N):
    operation = list(sys.stdin.readline().rstrip().split())
    
    if operation[0] == "push":
        que.append(operation[1])
    elif operation[0] == "size":
        print(len(que))
    elif len(que) == 0:
        if operation[0] == "empty":
            print(1)
        else:
            print(-1)
    elif operation[0] == "empty":
        print(0)
    elif operation[0] == "pop":
        print(que.popleft())
    elif operation[0] == "front":
        print(que[0])
    elif operation[0] == "back":
        print(que[-1])
