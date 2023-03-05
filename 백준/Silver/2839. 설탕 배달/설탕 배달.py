import sys

N = int(sys.stdin.readline().rstrip())

sugar = N%5
total = N//5

if sugar == 0:
  print(total)
  sys.exit(0)

while sugar <= N:
  if sugar%3 == 0:
    total += sugar//3
    sugar = 0
    break;
  
  sugar += 5
  total -= 1

if sugar == 0:
  print(total)
else:
  print(-1)