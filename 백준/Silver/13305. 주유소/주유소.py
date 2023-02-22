import sys

N = int(sys.stdin.readline().rstrip())

distance_list = list(map(int, sys.stdin.readline().rstrip().split()))
price_list = list(map(int, sys.stdin.readline().rstrip().split()))

price = 0
start = end = N - 2

while start >= 0:
  if start == 0 or price_list[start] < price_list[start - 1]:
    price += price_list[start]*sum(distance_list[start:end + 1])
    end = start - 1
  
  start -= 1

print(price)
