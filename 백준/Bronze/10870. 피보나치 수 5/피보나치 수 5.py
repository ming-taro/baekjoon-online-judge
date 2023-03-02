import sys

N = int(sys.stdin.readline().rstrip())

def fibo(N):
  if N == 0:
    return 0
  if N == 1 or N == 2:
    return 1
  
  return fibo(N - 1) + fibo(N - 2)

print(fibo(N))