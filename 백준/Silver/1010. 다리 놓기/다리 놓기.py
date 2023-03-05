import sys
import math

T = int(sys.stdin.readline().rstrip())

for i in range(T):
  N, M = map(int, sys.stdin.readline().rstrip().split())
  print(math.factorial(M)//(math.factorial(M - N)*math.factorial(N)))

#mCn = m!/(m - n)!n!