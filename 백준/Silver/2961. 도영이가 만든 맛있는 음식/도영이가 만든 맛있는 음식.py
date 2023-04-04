import sys
from itertools import combinations

N = int(sys.stdin.readline().rstrip())

s_list = []
b_list = []

for _ in range(N):
    S, B = map(int, sys.stdin.readline().rstrip().split())
    s_list.append(S)
    b_list.append(B)


def multiply(index_list, number_list):
    result = 1

    for index in index_list:
        result *= number_list[index]

    return result


def sum(index_list, number_list):
    result = 0

    for index in index_list:
        result += number_list[index]

    return result


number_list = [i for i in range(N)]
min_difference = 1000000000

for n in range(1, N + 1):
    index_list = list(combinations(number_list, n))
    
    for index in index_list:
        min_difference = min(
            min_difference, abs(multiply(index, s_list) - sum(index, b_list))
        )

print(min_difference)