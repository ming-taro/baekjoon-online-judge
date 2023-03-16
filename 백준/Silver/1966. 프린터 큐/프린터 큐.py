import sys
from collections import deque

N = int(sys.stdin.readline().rstrip())

for i in range(N):
    N, M = map(int, sys.stdin.readline().rstrip().split())
    input = list(map(int, sys.stdin.readline().rstrip().split()))
    priority = {i: input[i] for i in range(N)}  # 각 문서별 중요도 딕셔너리

    count = 0
    que = deque([i for i in range(N)])
    input.sort()

    while True:
        top = que.popleft()

        if len(que) == 0:  # top이 가장 마지막 문자인 경우
            count += 1
            break
        elif top == M and priority[top] == input[-1]:
            count += 1
            break
        elif priority[top] == input[-1]:
            count += 1
            input.pop()
        else:  # 가장 앞에 있던 문자의 우선순위가 바로 뒤에 있는 문자보다 높은 경우
            que.append(top)

    print(count)