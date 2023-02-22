import sys

N = int(sys.stdin.readline().rstrip())

weight = []

for i in range(N):
  weight.append(int(sys.stdin.readline().rstrip()))

weight.sort(reverse = True)
sum = weight[0]

for index in range(1, N):
  if (index + 1)*weight[index] > sum:
    sum = (index + 1)*weight[index]

print(sum)