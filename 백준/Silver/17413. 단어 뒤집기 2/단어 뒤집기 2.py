import sys
from collections import deque

text = sys.stdin.readline().rstrip()

index = 0

result = []
word = deque()

while index < len(text):
    if text[index] == "<":
        while text[index] != ">":
            result.append(text[index])
            index += 1
        result.append(text[index])  #'>'
        index += 1
    else:
        while index < len(text) and text[index] != "<":
            if text[index] == " ":
                word.appendleft(" ")
                index += 1
                break
            word.append(text[index])
            index += 1
        while word:
            result.append(word.pop())

print("".join(result))