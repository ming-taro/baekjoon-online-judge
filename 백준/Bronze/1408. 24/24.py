import sys

start = list(map(int, sys.stdin.readline().rstrip().split(":")))
end = list(map(int, sys.stdin.readline().rstrip().split(":")))


def get_time(time):
    return time[0] * 3600 + time[1] * 60 + time[2]


time = get_time(end) - get_time(start)
if time < 0:
    time += 24*3600

print("%02d:%02d:%02d" % (time // 3600, time // 60 % 60, time % 60))