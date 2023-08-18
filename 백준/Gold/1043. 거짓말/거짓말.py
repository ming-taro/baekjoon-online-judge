import sys
from collections import deque

N, M = map(int, sys.stdin.readline().rstrip().split(" "))

person = sys.stdin.readline().rstrip()
is_possible = True

if person == '0':
  is_possible = False
  for _ in range(M):
    person_list = sys.stdin.readline().rstrip()
  print(M)
  exit(0)

true_person = list(map(int, person.split(" ")))[1:]   #진실을 말해야하는 사람
person_list = []

result = 0

flag_list = [[] for _ in range(N + 1)]

for _ in range(M):
  person_list.append(list(map(int, sys.stdin.readline().rstrip().split(" ")))[1:])
  for index in range(len(person_list[-1])):
    person = person_list[-1]
    flag_list[person[index]] += person[:index]
    flag_list[person[index]] += person[index + 1:]

queue = deque(true_person)
visited = [False for _ in range(N + 1)]

for p in true_person:
  visited[p] = True

while queue:
  node = queue.popleft()
  
  for v in flag_list[node]:
    if visited[v] == False:
      visited[v] = True
      queue.append(v)

for person in person_list:
  flag = True
  for p in person:
    if visited[p]:
      flag = False
      break
  if flag:
    result += 1

print(result)
