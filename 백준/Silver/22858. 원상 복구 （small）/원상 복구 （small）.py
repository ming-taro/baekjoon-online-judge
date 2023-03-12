import sys

N, K = map(int, sys.stdin.readline().rstrip().split())

card_after_k = list(map(int, sys.stdin.readline().rstrip().split()))
card_after_n = list(map(int, sys.stdin.readline().rstrip().split()))

card = card_after_k[:]

flag = 1

for _ in range(K):
    if flag == 1:
        for i in range(N):
            index = card_after_n[i] - 1
            card[index] = card_after_k[i]
        flag = 0
    else:
        for i in range(N):
            index = card_after_n[i] - 1
            card_after_k[index] = card[i]
        flag = 1


if K % 2 == 1:
    print(*card, sep=' ')
else:
    print(*card_after_k, sep=' ')