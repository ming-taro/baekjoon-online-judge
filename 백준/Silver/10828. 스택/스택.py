import sys

N = int(sys.stdin.readline().rstrip())
stack = []

for i in range(N):
    operation = list(sys.stdin.readline().rstrip().split())
    
    if operation[0] == "push":
        stack.append(operation[1])
    elif operation[0] == "empty":
        if len(stack) == 0:
            print(1)
        else:
            print(0)
    elif operation[0] == "size":
        print(len(stack))
    elif len(stack) == 0:
        print(-1)
    elif operation[0] == "pop":
        print(stack.pop())
    elif operation[0] == "top":
        print(stack[-1])