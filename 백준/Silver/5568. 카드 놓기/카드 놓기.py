import sys
import itertools

N = int(sys.stdin.readline().rstrip())
K = int(sys.stdin.readline().rstrip())
card = [sys.stdin.readline().rstrip() for _ in range(N)]

number_list = set((itertools.permutations(card, K)))
result = set()

for number in number_list:
    result.add(int("".join(number)))

print(len(result))
