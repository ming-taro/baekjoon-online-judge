import sys

N = int(sys.stdin.readline().rstrip())
input = sys.stdin.readline().rstrip()

value_list = {}
stack = []
result = 0

for i in range(N):
    value_list[chr(ord("A") + i)] = int(sys.stdin.readline().rstrip())

for value in input:
    if value >= "A" and value <= "Z":
        stack.append(value_list[value])
        continue

    num2 = stack.pop()
    num1 = stack.pop()

    if value == "+":
        stack.append(num1 + num2)
    elif value == "-":
        stack.append(num1 - num2)
    elif value == "*":
        stack.append(num1 * num2)
    elif value == "/":
        stack.append(num1 / num2)

print("%.2f" % stack.pop())
# char -> int : ord(), int - > char : chr()