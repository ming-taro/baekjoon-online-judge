import sys

L, R = map(str, sys.stdin.readline().rstrip().split())
text = sys.stdin.readline().rstrip()

keyboard = {
    "q": (0, 0),
    "w": (0, 1),
    "e": (0, 2),
    "r": (0, 3),
    "t": (0, 4),
    "y": (0, 5),
    "u": (0, 6),
    "i": (0, 7),
    "o": (0, 8),
    "p": (0, 9),
    "a": (1, 0),
    "s": (1, 1),
    "d": (1, 2),
    "f": (1, 3),
    "g": (1, 4),
    "h": (1, 5),
    "j": (1, 6),
    "k": (1, 7),
    "l": (1, 8),
    "z": (2, 0),
    "x": (2, 1),
    "c": (2, 2),
    "v": (2, 3),
    "b": (2, 4),
    "n": (2, 5),
    "m": (2, 6),
}

left_set = set(["q", "w", "e", "r", "t", "a", "s", "d", "f", "g", "z", "x", "c", "v"])

left = keyboard[L]
right = keyboard[R]

time = len(text)

for i in range(len(text)):
    if text[i] in left_set:
        key = keyboard[text[i]]
        time += abs(key[0] - left[0]) + abs(key[1] - left[1])
        left = key
    else:
        key = keyboard[text[i]]
        time += abs(key[0] - right[0]) + abs(key[1] - right[1])
        right = key

print(time)