import sys
import itertools

N, K = map(int, sys.stdin.readline().rstrip().split())
K_list = sorted(list(map(str, sys.stdin.readline().rstrip().split())), reverse=True)

for i in reversed(range(len(str(N)) + 1)):
    number_list = list(itertools.product(K_list, repeat=i))
    for number in number_list:
        if int("".join(number)) <= N:
            print("".join(number))
            sys.exit(0)
