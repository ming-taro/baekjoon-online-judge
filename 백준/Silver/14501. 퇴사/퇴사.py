import sys

N = int(sys.stdin.readline().rstrip())

plan = []

for _ in range(N):
    T, P = map(int, sys.stdin.readline().rstrip().split())
    plan.append([T, P])

dp = [0 for _ in range(N + 1)]

for i in reversed(range(N)):
    if i + plan[i][0] > N:   #기간이 N을 넘어가는 경우 오늘 상담 포기, 다음날 상담으로 대체
        dp[i] = dp[i + 1]
    else:                    #(오늘 상담) + (다음 상담부터 마지막날 상담) VS 오늘 상담을 포기한 경우(=다음날 상담~마지막날 상담)
        dp[i] = max(plan[i][1] + dp[i + plan[i][0]], dp[i + 1]) 

print(dp[0])
