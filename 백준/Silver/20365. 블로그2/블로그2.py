import sys

N = int(sys.stdin.readline().rstrip())
color_list = sys.stdin.readline().rstrip()
color = color_list[0]

for i in range(1, N):
  if color_list[i] != color_list[i - 1]:
    color += color_list[i]

count_of_R = color.count('R')
count_of_B = color.count('B')

if count_of_R == 0 or count_of_B == 0:
  print(1)
else:
  print(min(count_of_R, count_of_B) + 1)