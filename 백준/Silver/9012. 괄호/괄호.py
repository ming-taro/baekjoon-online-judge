import sys

N = int(sys.stdin.readline().rstrip())

for i in range(N):
    input = sys.stdin.readline().rstrip()
    stack = []
    for j in input:
        if j == "(":
            stack.append(j)
        elif stack:
            stack.pop()
        else:
            stack.append(j)  #밑의 if stack에서 "NO"를 출력하기위해 더미데이터로 넣음
            break

    if stack:
        print("NO")
    else:
        print("YES")