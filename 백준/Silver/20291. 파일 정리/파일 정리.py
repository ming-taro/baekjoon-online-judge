import sys

N = int(sys.stdin.readline().rstrip())
file = {}

for i in range(N):
    input = sys.stdin.readline().rstrip().split(".")
    if input[1] in file:
        file[input[1]] += 1
    else:
        file[input[1]] = 1

for key in sorted(file):
    print(key, file[key])