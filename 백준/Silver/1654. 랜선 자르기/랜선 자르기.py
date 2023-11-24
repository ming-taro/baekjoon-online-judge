import sys

input = sys.stdin.readline().split(' ')
K = int(input[0])
N = int(input[1])

lines = []

for _ in range(K):
  lines.append(int(sys.stdin.readline()))

left = 1
right = 2147483647

while left <= right:
  mid = (left + right) // 2   #target = N개를 만들수 있는 랜선의 최대 길이
  count = 0  #만들 수 있는 랜선의 길이

  for line in lines:
    if line // mid > 0:
      count += line // mid  #자를 수 있는 랜선 개수 카운트
    
    if count > N:
      break

  if count >= N:   #많이 자름 -> 더 크게 만들어도 됨
    left = mid + 1
  elif count < N: #적게 자름
    right = mid - 1
  
print(right)