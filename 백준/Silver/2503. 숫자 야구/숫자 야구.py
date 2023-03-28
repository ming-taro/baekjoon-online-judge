import sys
from itertools import permutations

N = int(sys.stdin.readline().rstrip())

number_list = list(permutations([1, 2, 3, 4, 5, 6, 7, 8, 9], 3))
remove_count = 0

for _ in range(N):
    input, strike, ball = map(int, sys.stdin.readline().rstrip().split())
    number = list(map(int, str(input)))

    remove_count = 0
    for index in range(len(number_list)):
        strike_count = ball_count = 0
        index -= remove_count
        
        for m in range(3):
            if number[m] == number_list[index][m]:
                strike_count += 1
            elif number[m] in number_list[index]:
                ball_count += 1

        if strike_count != strike or ball_count != ball:
            number_list.remove(number_list[index])
            remove_count += 1

print(len(number_list))
