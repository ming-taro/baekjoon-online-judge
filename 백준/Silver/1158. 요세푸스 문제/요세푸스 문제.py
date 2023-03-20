import sys

N, K = map(int, sys.stdin.readline().rstrip().split())

people = [i for i in range(1, N + 1)]

count = 0
index = -1

print("<", end="")

while True:
    index = (index + 1) % len(people)
    count += 1

    if len(people) == 1:
        print("%d>" % people[0])
        break

    if count == K:
        print("%d, " % people.pop(index), end="")
        index -= 1
        count = 0