import sys
import math

n = int(sys.stdin.readline().rstrip())
dp = [0, 1]

for i in range(2, n + 1):
  sqrt_num = int(math.sqrt(i))
  dp_num = 1 + dp[i - int(math.pow(sqrt_num, 2))]
  
  for j in reversed(range(1, sqrt_num)):
    dp_num = min(dp_num,  1 + dp[i - int(math.pow(j, 2))])
  dp.append(dp_num)
  
print(dp[n])