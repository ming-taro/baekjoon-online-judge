import sys
import math

input = sys.stdin.readline().rstrip()

index = 0
max_num = ""
min_num = ""

while index < len(input):
    pow_num = 0

    while index < len(input) and input[index] == "M":
        pow_num += 1
        index += 1

    if pow_num != 0 and index < len(input):  # M다음에 최소 1개 이상의 K가 존재하는 경우
        min_num += str(10 ** (pow_num - 1)) + "5"
        max_num += str(10**pow_num * 5)
        index += 1
    elif pow_num != 0:  # 입력이 M으로 끝나는 경우
        max_num += "1" * pow_num
        min_num += str(10 ** (pow_num - 1))
        break

    while index < len(input) and input[index] == "K":
        min_num += "5"
        max_num += "5"
        index += 1

print(max_num)
print(min_num)