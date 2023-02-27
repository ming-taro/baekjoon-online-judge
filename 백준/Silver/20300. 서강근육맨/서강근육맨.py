import sys

N = int(sys.stdin.readline().rstrip())

M_list = list(map(int, sys.stdin.readline().rstrip().split()))
M_list.sort(reverse = True)

sum = []

range_of_list = (N + 1)//2

for i in range(range_of_list):
  if N%2 == 1:
    if i == 0:
      sum.append(M_list[0])
    else:
      sum.append(M_list[i] + M_list[N - i])
  else:
    sum.append(M_list[i] + M_list[N - 1 - i])

print(max(sum))

#>문제 분석
#-> PT를 한 번 받을 때 운동기구를 최대 두 개까지만 선택할 수 있다.
#-> PT를 받을 때 운동기구를 되도록이면 두 개를 사용
#-> ex) 운동기구가 9개인 경우 PT 5번(마지막은 1개 사용)
#-> PT를 받을 때의 근손실 정도는 두 운동기구의 근손실 정도의 합