import sys

N = int(sys.stdin.readline().rstrip())
F = int(sys.stdin.readline().rstrip())

for i in range(10):
    for j in range(10):
        number = N//100*100 + (i*10 + j)
        if number % F == 0:
            print(str(i) + str(j))
            sys.exit(0)