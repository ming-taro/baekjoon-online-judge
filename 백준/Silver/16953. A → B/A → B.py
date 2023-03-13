import sys

A, B = map(int, sys.stdin.readline().rstrip().split())

count = 1

while B > 0:
    if B % 10 == 1:
        B //= 10
    elif (B / 2).is_integer():
        B //= 2
    else:
        break
    count += 1

    if A == B:
        print(count)
        sys.exit(0)

print(-1)

#B부터 시작해서 끝 자리수가 1이면 10으로 나눈 값, 이 외에는 2로 나눈 값으로 크기를 줄여나간다