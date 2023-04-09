import sys

N = int(sys.stdin.readline().rstrip())

file_list = []

for _ in range(N):
    file = sys.stdin.readline().rstrip()
    file_list.append(file)

keyword = list(map(str, file_list[0]))

for i in range(1, N):
    for j in range(len(file_list[i])):
        if keyword[j] != file_list[i][j]:
            keyword[j] = "?"

print("".join(keyword))