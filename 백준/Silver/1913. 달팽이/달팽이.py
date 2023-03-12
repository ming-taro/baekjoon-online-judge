import sys
import math

N = int(sys.stdin.readline().rstrip())
number_to_find = int(sys.stdin.readline().rstrip())
pow_num = N**2

number_list = [[[0] for _ in range(N)] for _ in range(N)]
point = [0, 0]

for i in range((N + 1) // 2):  # N=1일 때 1겹, N=3일 때 2겹, N=5일 때 3겹..
    for j in range(i, N - i):
        number_list[j][i] = pow_num
        if pow_num == number_to_find:
            point[0] = j
            point[1] = i
        pow_num -= 1
    for j in range(i + 1, N - i):
        number_list[N - i - 1][j] = pow_num
        if pow_num == number_to_find:
            point[0] = N - i - 1
            point[1] = j
        pow_num -= 1
    for j in reversed(range(i, N - i - 1)):
        number_list[j][N - i - 1] = pow_num
        if pow_num == number_to_find:
            point[0] = j
            point[1] = N - i - 1
        pow_num -= 1
    for j in reversed(range(i + 1, N - i - 1)):
        number_list[i][j] = pow_num
        if pow_num == number_to_find:
            point[0] = i
            point[1] = j
        pow_num -= 1

for i in range(N):
    print(*number_list[i], sep=" ")

print("%d %d"%(point[0] + 1, point[1] + 1))
