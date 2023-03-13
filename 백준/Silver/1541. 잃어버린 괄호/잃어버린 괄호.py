import sys

input = list(sys.stdin.readline().rstrip().split("-"))

for index in range(len(input)):
    number_list = map(int, input[index].split("+"))
    input[index] = sum(number_list)

print(input[0] - sum(input[1:]))