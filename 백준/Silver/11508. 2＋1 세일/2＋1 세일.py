import sys

N = int(sys.stdin.readline().rstrip())
price = []

for index in range(N):
  price.append(int(sys.stdin.readline().rstrip()))
  
price.sort(reverse = True)

sum = 0

for index in range(0, N, 3):
  if N - index == 1:
    sum += price[index]
  else:
    sum += price[index] + price[index + 1]

print(sum)