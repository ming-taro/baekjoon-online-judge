import sys

A = int(sys.stdin.readline().rstrip())
T = int(sys.stdin.readline().rstrip())
selection = int(sys.stdin.readline().rstrip())

selection_1 = 0
selection_2 = 0
index = 0
lap = 1

while True:
    for i in range(2):
        selection_1 += 1
        if selection == 0 and selection_1 == T:
            print(index % A)
            sys.exit(0)

        selection_2 += 1
        if selection == 1 and selection_2 == T:
            print((index + 1) % A)
            sys.exit(0)

        index += 2

    for i in range(lap + 1):
        selection_1 += 1
        if selection == 0 and selection_1 == T:
            print(index % A)
            sys.exit(0)
        index += 1

    for i in range(lap + 1):
        selection_2 += 1
        if selection == 1 and selection_2 == T:
            print(index % A)
            sys.exit(0)
        index += 1

    lap += 1
