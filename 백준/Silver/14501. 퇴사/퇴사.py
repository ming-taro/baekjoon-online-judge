import sys

N = int(sys.stdin.readline().rstrip())

plan = []

for _ in range(N):
    T, P = map(int, sys.stdin.readline().rstrip().split())
    plan.append([T, P])

dp = [0 for _ in range(N + 1)]

for i in reversed(range(N)):
    if i + plan[i][0] > N:
        dp[i] = dp[i + 1]
    elif i < N - 1:
        dp[i] = max(plan[i][1] + dp[i + plan[i][0]], dp[i + 1]) 
    else:
        dp[i] = plan[i][1]

print(dp[0])
