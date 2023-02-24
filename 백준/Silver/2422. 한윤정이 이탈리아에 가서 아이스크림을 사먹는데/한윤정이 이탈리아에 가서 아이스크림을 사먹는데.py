import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
ice_cream_list = [[True for _ in range(N)] for _ in range(N)]

for i in range(M):
  ice_cream_1, ice_cream_2 = map(int, sys.stdin.readline().rstrip().split())
  ice_cream_list[ice_cream_1 - 1][ice_cream_2 - 1] = False
  ice_cream_list[ice_cream_2 - 1][ice_cream_1 - 1] = False

count = 0

for i in range(N - 2):
  for j in range(i + 1, N - 1):
    for k in range(j + 1, N):
      if ice_cream_list[i][j] and ice_cream_list[i][k] and ice_cream_list[j][k]:
        count += 1

print(count)

# >처음 구현했던 아이디어
# : 아이스크림 조합을 리스트로 묶어 (M, 2)행렬로 저장한 후 3중 for문을 순회하며 not in으로 판별함
# -> in을 사용하는 경우 in 연산 자체가 리스트 전체를 순회하며 검사하기 때문에 시간이 오래 걸림
# -> dictionary는 키값이 중복될 수 없으므로 이 문제에서는 사용할 수 X

# >해결방안
# -> 처음부터 행렬을 true로 초기화하고 입력받은 조합에 해당하는 값을 false로 바꿈
# -> for문을 순회하며 해당 값이 true인 경우만 count