import sys

N = int(sys.stdin.readline().rstrip())
number_list = list(map(int, sys.stdin.readline().rstrip().split()))

number_list.sort()

print(number_list[0] * number_list[-1])