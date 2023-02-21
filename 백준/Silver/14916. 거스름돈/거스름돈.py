import sys

n = int(sys.stdin.readline().rstrip())

if n%5 == 0:
  print(n//5)
elif n%5%2 == 0 and n >= 7 :
  print(n//5 + n%5//2)
elif n%5%2 == 1 and n >= 7:
  print(n//5 + n%5//2 + 2)
elif n%2 == 0:
  print(n//2)
else:
  print(-1)
