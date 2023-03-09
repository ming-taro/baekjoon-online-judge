import sys

N = int(sys.stdin.readline().rstrip())


def get_length(N):
    return 1 + (N - 1) * 4

for i in range(N - 1):
    print(("*" + " ") * i, end="")
    print("*" * get_length(N - i), end="")
    print((" " + "*") * i)

    print(("*" + " ") * (i + 1), end="")
    print(" " * get_length(N - i - 1), end="")
    print((" " + "*") * (i + 1))

print("* " * (1 + (N - 1) * 2))

for i in reversed(range(N - 1)):
    print(("*" + " ") * (i + 1), end="")
    print(" " * get_length(N - i - 1), end="")
    print((" " + "*") * (i + 1))

    print(("*" + " ") * i, end="")
    print("*" * get_length(N - i), end="")
    print((" " + "*") * i)
