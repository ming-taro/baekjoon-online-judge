import sys

input = list(sys.stdin.readline().rstrip())

next_word = {"q": "u", "u": "a", "a": "c", "c": "k", "k": "q"}

if input[0] != "q" or len(input)%5 != 0:
    print(-1)
    sys.exit(0)

duck = 0
is_duck = True

while input:
    pre_word = "k"
    index = 0

    while index < len(input):
        if next_word[pre_word] == input[index]:
            pre_word = input[index]
            input.pop(index)
        else:
            index += 1

    if pre_word == "k":
        duck += 1
    else:
        is_duck = False
        break

if is_duck:
    print(duck)
else:
    print(-1)