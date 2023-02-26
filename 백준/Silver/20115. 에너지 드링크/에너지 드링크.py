import sys

N = sys.stdin.readline().rstrip()

drink_list = list(map(int, sys.stdin.readline().rstrip().split()))

drink_list.sort(reverse = True)
sum = 0

for drink in drink_list[1:]:
  sum += drink

total = drink_list[0] + sum/2
if total == int(total):
  total = int(total)
print(total)