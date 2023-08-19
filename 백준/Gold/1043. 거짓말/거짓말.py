import sys
from collections import deque

N, M = map(int, sys.stdin.readline().rstrip().split(" "))
person = sys.stdin.readline().rstrip()

if person == '0':      #진실을 말해야하는 사람이 0명인 경우 -> 모든 파티 참석 가능
  for _ in range(M):
    party_attendees = sys.stdin.readline().rstrip()
  print(M)
  exit(0)

true_person = list(map(int, person.split(" ")))[1:]   #진실을 말해야하는 사람

party_attendees = []
total_party = 0

attendees_list = [[] for _ in range(N + 1)]

for _ in range(M):
  attendees = list(map(int, sys.stdin.readline().rstrip().split(" ")))[1:]
  party_attendees.append(attendees)
  
  for index in range(len(attendees)):  #attendees[index]와 같은 파티에 참석하는 사람들 명단 저장
    attendees_list[attendees[index]] += attendees[:index]
    attendees_list[attendees[index]] += attendees[index + 1:]

queue = deque(true_person)
visited = [False for _ in range(N + 1)]

for p in true_person:
  visited[p] = True

#while문을 순회하며 진실을 말해야하는 사람과 같은 파티에 참석한 사람들을 체크함
while queue:
  node = queue.popleft()
  
  for v in attendees_list[node]:
    if visited[v] == False:
      visited[v] = True
      queue.append(v)

for attendee in party_attendees:
  is_possible_to_attend = True
  
  for v in attendee:
    if visited[v]:  #진실을 말해야하는 사람이 있는 경우, 파티에 참가할 수 X
      is_possible_to_attend = False
      break
    
  if is_possible_to_attend:
    total_party += 1

print(total_party)