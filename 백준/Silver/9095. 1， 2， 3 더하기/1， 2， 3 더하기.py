import sys

T = int(sys.stdin.readline().rstrip())
dp = [1, 2, 4]

for i in range(3, 11):
  dp.append(dp[i - 1] + dp[i - 2] + dp[i - 3])

for i in range(T):
  n = int(sys.stdin.readline().rstrip()) - 1
  print(dp[n])