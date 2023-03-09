import sys

N = int(sys.stdin.readline().rstrip())

status = list(map(int, sys.stdin.readline().rstrip().split()))

student = int(sys.stdin.readline().rstrip())

for i in range(student):
    gender, number = map(int, sys.stdin.readline().rstrip().split())

    if gender == 1:  # 남학생 -> 자신이 받은 숫자의 배수에 해당하는 스위치의 상태를 바꿈
        for j in range(number, N + 1, number):
            status[j - 1] = (status[j - 1] + 1) % 2
    else:  # 여학생 -> 좌우대칭
        left = number - 1
        right = number + 1

        status[number - 1] = (status[number - 1] + 1) % 2

        while left >= 1 and right <= N:
            if status[left - 1] != status[right - 1]:
                break

            status[left - 1] = (status[left - 1] + 1) % 2
            status[right - 1] = (status[right - 1] + 1) % 2

            left -= 1
            right += 1

for i in range(len(status)):
    if i != 0 and i % 20 == 0:
        print()
    print(status[i], end=" ")