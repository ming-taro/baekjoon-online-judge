import sys

N = int(sys.stdin.readline().rstrip())

time = []

for i in range(N):
    start_time, end_time = map(int, sys.stdin.readline().rstrip().split())
    time.append((start_time, end_time))

time.sort(key=lambda x: (x[1], x[0]))
end_time = time[0][1]  # 가장 처음에 사용할 회의실의 종료 시각
room = 1

for i in range(1, len(time)):
    if time[i][0] >= end_time:
        end_time = time[i][1]
        room += 1

print(room)